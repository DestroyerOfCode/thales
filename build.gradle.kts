plugins {
    id("java")
    id ("org.springframework.boot") version ("3.1.3")
}

apply {
    plugin("io.spring.dependency-management")
}

group = "org.thales"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.data.elasticsearch)
    implementation(libs.mysql.connector.j)
    implementation(libs.modelmapper)
    implementation(libs.slf4j.api)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

task("run") {
    dependsOn("classes")
    doLast {
        javaexec {
            classpath(sourceSets.main.get().runtimeClasspath)
            mainClass.set("org.thales.Application")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}