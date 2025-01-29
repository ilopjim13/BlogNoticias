
plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.mongodb:mongodb-driver-kotlin-sync:5.3.0")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.3.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}