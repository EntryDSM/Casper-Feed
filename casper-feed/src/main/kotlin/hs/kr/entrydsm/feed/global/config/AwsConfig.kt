package hs.kr.entrydsm.feed.global.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * AWS 서비스 구성을 위한 설정 클래스입니다.
 * 이 클래스는 AWS S3 클라이언트를 생성하고 구성하는 책임을 담당합니다.
 *
 * @property accessKey AWS 액세스 키
 * @property secretKey AWS 시크릿 키
 * @property region AWS 리전
 */
@Configuration
class AwsConfig(
    @Value("\${cloud.aws.credentials.accessKey}")
    private val accessKey: String,
    @Value("\${cloud.aws.credentials.secretKey}")
    private val secretKey: String,
    @Value("\${cloud.aws.region.static}")
    private val region: String
) {
    /**
     * AmazonS3 클라이언트를 생성하는 빈 메서드입니다.
     * AWS 자격 증명과 리전 정보를 사용하여 S3 클라이언트를 구성합니다.
     *
     * @return 구성된 AmazonS3 클라이언트 인스턴스
     */
    @Bean
    fun amazonS3(): AmazonS3 {
        val awsCredentials: AWSCredentials = BasicAWSCredentials(accessKey, secretKey)
        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }
}
