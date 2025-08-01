object Dependencies {
    // Spring Boot
    const val SPRING_BOOT_STARTER = "org.springframework.boot:spring-boot-starter"
    const val SPRING_BOOT_STARTER_WEB = "org.springframework.boot:spring-boot-starter-web"
    const val SPRING_BOOT_STARTER_DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val SPRING_BOOT_STARTER_DATA_REDIS = "org.springframework.boot:spring-boot-starter-data-redis"
    const val SPRING_BOOT_STARTER_SECURITY = "org.springframework.boot:spring-boot-starter-security"
    const val SPRING_BOOT_STARTER_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
    const val SPRING_BOOT_STARTER_TEST = "org.springframework.boot:spring-boot-starter-test"

    // Kotlin
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_TEST_JUNIT5 = "org.jetbrains.kotlin:kotlin-test-junit5"

    // Database
    const val MYSQL_CONNECTOR = "com.mysql:mysql-connector-j"

    // JSON
    const val JACKSON_MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin"
    const val ORG_JSON = "org.json:json:${DependencyVersion.ORG_JSON}"

    // JWT
    const val JWT_API = "io.jsonwebtoken:jjwt-api:${DependencyVersion.JWT}"
    const val JWT_IMPL = "io.jsonwebtoken:jjwt-impl:${DependencyVersion.JWT}"
    const val JWT_JACKSON = "io.jsonwebtoken:jjwt-jackson:${DependencyVersion.JWT}"

    // MapStruct
    const val MAPSTRUCT = "org.mapstruct:mapstruct:${DependencyVersion.MAPSTRUCT}"
    const val MAPSTRUCT_PROCESSOR = "org.mapstruct:mapstruct-processor:${DependencyVersion.MAPSTRUCT}"

    // Test
    const val JUNIT_PLATFORM_LAUNCHER = "org.junit.platform:junit-platform-launcher"

    // gRPC
    const val GRPC_NETTY_SHADED = "io.grpc:grpc-netty-shaded:${DependencyVersion.GRPC}"
    const val GRPC_PROTOBUF = "io.grpc:grpc-protobuf:${DependencyVersion.GRPC}"
    const val GRPC_STUB = "io.grpc:grpc-stub:${DependencyVersion.GRPC}"
    const val GRPC_KOTLIN_STUB = "io.grpc:grpc-kotlin-stub:${DependencyVersion.GRPC_KOTLIN}"
    const val PROTOBUF_KOTLIN = "com.google.protobuf:protobuf-kotlin:${DependencyVersion.PROTOBUF}"
    const val GRPC_TESTING = "io.grpc:grpc-testing:${DependencyVersion.GRPC}"


    // swagger
    const val SWAGGER = "org.springdoc:springdoc-openapi-starter-webmvc-ui:${DependencyVersion.SWAGGER_VERSION}"

    // AWS
    const val AWS = "com.amazonaws:aws-java-sdk-s3:${DependencyVersion.AWS}"

    // open feign
    const val OPEN_FEIGN = "org.springframework.cloud:spring-cloud-starter-openfeign:${DependencyVersion.OPEN_FEIGN_VERSION}"

    // Kafka
    const val KAFKA = "org.springframework.kafka:spring-kafka"
}
