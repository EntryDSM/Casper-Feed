import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.collections.plus

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("kapt") version PluginVersion.KOTLIN_VERSION
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jlleitschuh.gradle.ktlint").version("12.1.1")
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
    id("casper.documentation-convention")
    id("com.google.protobuf") version "0.9.4"
}

// 서브프로젝트 설정
subprojects {
    // 서브프로젝트에 공통 설정 적용
    repositories {
        mavenCentral()
    }
}

tasks.register("checkAll") {
    group = "verification"
    description = "모든 모듈(includeBuild 포함)에 대해 check 태스크를 실행합니다"

    // 루트 프로젝트의 check 태스크에 의존
    dependsOn(tasks.named("check"))

    // 모든 서브프로젝트의 check 태스크에 의존
    subprojects.forEach { subproject ->
        dependsOn(subproject.tasks.matching { it.name.startsWith("check") })
    }

    // build-logic, convention 등 includeBuild 모듈의 check 태스크에 의존
    dependsOn(gradle.includedBuilds.map { it.task(":check") })
}

group = "hs.kr.entrydsm"
version = "0.0.1-SNAPSHOT"

dependencies {
    // 스프링 부트 기본 기능
    implementation(Dependencies.SPRING_BOOT_STARTER)

    // 코틀린 리플렉션
    implementation(Dependencies.KOTLIN_REFLECT)

    // 스프링 부트 테스트 도구
    testImplementation(Dependencies.SPRING_BOOT_STARTER_TEST)

    // 코틀린 + JUnit5 테스트
    testImplementation(Dependencies.KOTLIN_TEST_JUNIT5)

    // JUnit5 실행 런처
    testRuntimeOnly(Dependencies.JUNIT_PLATFORM_LAUNCHER)

    // 웹 관련
    implementation(Dependencies.SPRING_BOOT_STARTER_WEB)

    // 데이터베이스
    implementation(Dependencies.SPRING_BOOT_STARTER_DATA_JPA)
    implementation(Dependencies.SPRING_BOOT_STARTER_DATA_REDIS)
    runtimeOnly(Dependencies.MYSQL_CONNECTOR)

    // 보안
    implementation(Dependencies.SPRING_BOOT_STARTER_SECURITY)

    // 검증
    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)

    // JSON 처리
    implementation(Dependencies.JACKSON_MODULE_KOTLIN)
    implementation(Dependencies.ORG_JSON)

    // JWT
    implementation(Dependencies.JWT_API)
    implementation(Dependencies.JWT_IMPL)
    runtimeOnly(Dependencies.JWT_JACKSON)

    implementation(Dependencies.MAPSTRUCT)
    kapt(Dependencies.MAPSTRUCT_PROCESSOR)

    // grpc
    implementation(Dependencies.GRPC_NETTY_SHADED)
    implementation(Dependencies.GRPC_PROTOBUF)
    implementation(Dependencies.GRPC_STUB)
    implementation(Dependencies.GRPC_KOTLIN_STUB)
    implementation(Dependencies.PROTOBUF_KOTLIN)
    testImplementation(Dependencies.GRPC_TESTING)
    implementation("net.devh:grpc-server-spring-boot-starter:2.12.0.RELEASE")


    // swagger
    implementation(Dependencies.SWAGGER)

    // aws
    implementation(Dependencies.AWS)

    // feign
    implementation(Dependencies.OPEN_FEIGN)

    // Kafka
    implementation(Dependencies.KAFKA)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${DependencyVersion.PROTOBUF}"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${DependencyVersion.GRPC}"
        }
        create("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${DependencyVersion.GRPC_KOTLIN}:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
                create("grpckt")
            }
        }

    }
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

detekt {
    config.setFrom(files("detekt.yml"))
    buildUponDefaultConfig = false // yml에서 설정한 룰만 허용
    parallel = true // 병렬 실행으로 성능 최적화
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        xml.required.set(false)
        txt.required.set(false)
    }

    jvmTarget = ("17") // Detekt가 사용하는 JVM 타겟을 Java 17로 지정
}
