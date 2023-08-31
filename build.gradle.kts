plugins {
	id("org.jetbrains.kotlin.jvm") version "1.8.22"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.8.22"

//	id("com.google.devtools.ksp") version "1.8.22-1.0.11"
	id("org.jetbrains.kotlin.kapt") version "1.8.22"

	id("com.github.johnrengelman.shadow") version "8.1.1"
	id("io.micronaut.application") version "4.0.2"
	id("io.micronaut.aot") version "4.0.2"
}

repositories {
	mavenCentral()
}

val kotlinVersion: String by properties

dependencies {
//	ksp("io.micronaut.serde:micronaut-serde-processor")
	kapt("io.micronaut.serde:micronaut-serde-processor")

	implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
	implementation("io.micronaut.serde:micronaut-serde-jackson")
	implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
	implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")

	runtimeOnly("ch.qos.logback:logback-classic")
	runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
	runtimeOnly("org.yaml:snakeyaml")

	testImplementation("io.micronaut:micronaut-http-client")
}

application {
	mainClass.set("com.micronaut.test.ApplicationKt")
}

java {
	sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
	compileKotlin {
		compilerOptions {
			jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
		}
	}
	compileTestKotlin {
		compilerOptions {
			jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
		}
	}
}
graalvmNative.toolchainDetection.set(false)
micronaut {
	runtime("netty")
	testRuntime("junit5")
	processing {
		incremental(true)
		annotations("com.example.*")
	}
	aot {
		// Please review carefully the optimizations enabled below
		// Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
		optimizeServiceLoading.set(false)
		convertYamlToJava.set(false)
		precomputeOperations.set(true)
		cacheEnvironment.set(true)
		optimizeClassLoading.set(true)
		deduceEnvironment.set(true)
		optimizeNetty.set(true)
	}
}