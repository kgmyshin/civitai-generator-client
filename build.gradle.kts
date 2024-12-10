import cl.franciscosolis.sonatypecentralupload.SonatypeCentralUploadTask
import java.util.*

plugins {
    id("java")
    id("com.google.devtools.ksp") version "2.0.21-1.0.27"
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    `maven-publish`
    id("cl.franciscosolis.sonatype-central-upload") version "1.0.2"
    id("co.uzzu.dotenv.gradle") version "2.0.0"
}

group = "io.github.kgmyshin"
version = "0.0.1"

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")

    testImplementation("com.squareup.okhttp3:logging-interceptor:3.8.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}


publishing {
    publications {
        register<MavenPublication>("maven") {
            pom {
                name.set(project.name)
                description.set("civitai generator client")
                url.set("https://github.com/kgmyshin/civitai-generator-client")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/kgmyshin/civitiai-generator-client/blob/master/LICENSE")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("kgmyshin")
                        name.set("Shinnosuke Kugimiya")
                        email.set("kgmyshin82@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/kgmyshin/civitai-generator-client")
                }
            }
        }
    }
}

tasks.named<SonatypeCentralUploadTask>("sonatypeCentralUpload") {
    // 公開するファイルを生成するタスクに依存する。
    dependsOn("jar", "sourcesJar", "javadocJar", "generatePomFileForMavenPublication")

    // Central Portalで生成したトークンを指定する。
    username = env.fetch("SONATYPE_CENTRAL_USERNAME")
    password = env.fetch("SONATYPE_CENTRAL_PASSWORD")


    // タスク名から成果物を取得する。
    archives = files(
        tasks.named("jar"),
        tasks.named("sourcesJar"),
        tasks.named("javadocJar"),
    )
    // POMファイルをタスクの成果物から取得する。
    pom = file(
        tasks.named("generatePomFileForMavenPublication").get().outputs.files.single()
    )

    // PGPの秘密鍵を指定する。
    signingKey = String(Base64.getDecoder().decode(env.fetch("PGP_SIGNING_KEY")))
    // PGPの秘密鍵のパスフレーズを指定する。
    signingKeyPassphrase = env.fetch("PGP_SIGNING_KEY_PASSPHRASE")
}
