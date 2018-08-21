/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


val repositoryMirrors = findMirrorUrls()

project.allprojects {
    buildscript.repositories {
        maven(url = repositoryMirrors.getOrDefault("gradleplugins", "https://plugins.gradle.org/m2"))
        maven(url = repositoryMirrors.getOrDefault("gradle", "https://repo.gradle.org/gradle/repo"))
        maven(url = repositoryMirrors.getOrDefault("gradle-libs", "https://repo.gradle.org/gradle/libs"))
        maven(url = repositoryMirrors.getOrDefault("kotlindev", "https://dl.bintray.com/kotlin/kotlin-dev"))
    }
    repositories {
        maven(url = repositoryMirrors.getOrDefault("gradleplugins", "https://plugins.gradle.org/m2"))
        maven(url = repositoryMirrors.getOrDefault("gradle", "https://repo.gradle.org/gradle/repo"))
        maven(url = repositoryMirrors.getOrDefault("gradle-libs", "https://repo.gradle.org/gradle/libs"))
        maven(url = repositoryMirrors.getOrDefault("kotlindev", "https://dl.bintray.com/kotlin/kotlin-dev"))
    }
}

fun findMirrorUrls(): Map<String, String> =
    if ("CI" in java.lang.System.getenv()) {
        System.getenv("REPO_MIRROR_URLS")?.split(',')?.associate { nameToUrl ->
            val (name, url) = nameToUrl.split(':', limit = 2)
            name to url
        } ?: emptyMap()
    } else {
        emptyMap()
    }
