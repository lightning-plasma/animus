import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

extra["kotlin.version"] = findProperty("kotlinVersion") as String

plugins {
	id("org.springframework.boot") version "2.4.2"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.serialization")
}

group = "com.archetype"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webflux")  {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-json")
	}
	// implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	// for blockhound
	implementation("io.projectreactor.tools:blockhound:1.0.4.RELEASE")
	runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-debug")

	runtimeOnly("io.r2dbc:r2dbc-postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
