package hs.kr.entrydsm.feed.infrastructure.grpc.client

import hs.kr.entrydsm.casper.admin.proto.AdminServiceGrpc
import hs.kr.entrydsm.casper.admin.proto.AdminServiceProto
import hs.kr.entrydsm.feed.infrastructure.grpc.client.dto.response.InternalAdminResponse
import io.grpc.Channel
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * 관리자 서비스와의 gRPC 통신을 담당하는 클라이언트 클래스입니다.
 *
 * @property channel gRPC 통신을 위한 채널 (user-service로 자동 주입됨)
 */
@Component
class AdminGrpcClient {

    @GrpcClient("user-service")
    lateinit var channel: Channel

    /**
     * 관리자 ID를 기반으로 관리자 정보를 조회합니다.
     *
     * @param adminId 조회할 관리자의 고유 식별자(UUID)
     * @return 조회된 관리자 정보를 담은 [InternalAdminResponse] 객체
     * @throws io.grpc.StatusRuntimeException gRPC 통신 중 오류가 발생한 경우
     */
    suspend fun getAdminInfoByAdminId(adminId: UUID): InternalAdminResponse {
        val adminStub = AdminServiceGrpc.newBlockingStub(channel)

        val request = AdminServiceProto.GetAdminIdRequest.newBuilder()
            .setAdminId(adminId.toString())
            .build()
        val response = adminStub.getAdminByUUID(request)

        return InternalAdminResponse(id = UUID.fromString(response.adminId))
    }
}
