package hs.kr.entrydsm.feed.infrastructure.grpc.client

import hs.kr.entrydsm.casper.admin.proto.AdminServiceGrpc
import hs.kr.entrydsm.casper.admin.proto.AdminServiceProto
import hs.kr.entrydsm.feed.infrastructure.grpc.client.dto.response.InternalAdminResponse
import io.grpc.Channel
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.suspendCancellableCoroutine
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

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
     * 관리자 ID를 기반으로 관리자 정보를 비동기적으로 조회합니다.
     * gRPC 비동기 스트리밍을 사용하여 관리자 서비스로부터 정보를 가져옵니다.
     *
     * @param adminId 조회할 관리자의 고유 식별자(UUID)
     * @return 조회된 관리자 정보를 담은 [InternalAdminResponse] 객체
     * @throws io.grpc.StatusRuntimeException gRPC 서버에서 오류가 발생한 경우
     * @throws java.util.concurrent.CancellationException 코루틴이 취소된 경우
     */
    suspend fun getAdminInfoByAdminId(adminId: UUID): InternalAdminResponse {
        val adminStub = AdminServiceGrpc.newStub(channel)

        val request = AdminServiceProto.GetAdminIdRequest.newBuilder()
            .setAdminId(adminId.toString())
            .build()

        val response = suspendCancellableCoroutine { continuation ->
            adminStub.getAdminByUUID(request, object : StreamObserver<AdminServiceProto.GetAdminIdResponse> {
                override fun onNext(value: AdminServiceProto.GetAdminIdResponse) {
                    continuation.resume(value)
                }
                override fun onError(t: Throwable) {
                    continuation.resumeWithException(t)
                }
                override fun onCompleted() {}
            })
        }

        return InternalAdminResponse(id = UUID.fromString(response.adminId))
    }
}
