plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("jacoco")
}

group = "com.sharkb"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	//Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	//Databases
	runtimeOnly("org.postgresql:postgresql")

	//Utils
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")

	//Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.testcontainers:rabbitmq")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val jacocoInclude = listOf(
	"**/service/**"
)
jacoco {
	toolVersion = "0.8.9"
	reportsDirectory.set(layout.buildDirectory.dir("${layout.buildDirectory}/reports/jacoco"))
}
tasks.test {
	finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
	dependsOn(tasks.test)

	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}

	classDirectories.setFrom(
		sourceSets.main.get().output.asFileTree.matching {
			include(jacocoInclude)
		}
	)
}
tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			element = "CLASS"
			classDirectories.setFrom(
				sourceSets.main.get().output.asFileTree.matching {
					include(jacocoInclude)
				}
			)
			enabled = true
			limit {
				minimum = BigDecimal(0.8)
			}
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
