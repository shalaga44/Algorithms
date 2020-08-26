plugins {
    kotlin("jvm") version "1.4.0"
}

group = "com.shalaga44"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.5.2")
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", "5.5.2")
    testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.5.2")

}
tasks {
    test {
        useJUnitPlatform()
    }
}