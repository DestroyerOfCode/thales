plugins {
    id("java")
    id("org.springframework.boot") version ("3.1.3")
}

apply {
    plugin("io.spring.dependency-management")
}

group = "org.thales"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.mysql.connector.j)
    implementation(libs.modelmapper)
    implementation(libs.slf4j.api)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation(libs.guava)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.spring.test)

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