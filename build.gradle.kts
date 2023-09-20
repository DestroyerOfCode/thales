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
    implementation(libs.spring.boot.starter.data.elasticsearch)
    implementation(libs.mysql.connector.j)
    implementation(libs.modelmapper)
    implementation(libs.slf4j.api)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

    testImplementation(libs.junit.jupiter)
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.3")
    testImplementation("org.springframework:spring-test:6.0.12")

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