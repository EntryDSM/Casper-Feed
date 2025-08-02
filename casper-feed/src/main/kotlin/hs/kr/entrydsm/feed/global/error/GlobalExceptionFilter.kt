package hs.kr.entrydsm.feed.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.entrydsm.feed.global.error.exception.CasperException
import hs.kr.entrydsm.feed.global.error.exception.ErrorCode
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * 전역 예외 처리를 담당하는 서블릿 필터입니다.
 * 컨트롤러 밖에서 발생하는 예외를 잡아 적절한 에러 응답으로 변환합니다.
 *
 * @property objectMapper JSON 직렬화를 위한 ObjectMapper
 */
class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    /**
     * 필터 체인을 실행하고 발생한 예외를 처리합니다.
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param filterChain 필터 체인
     * @throws IOException 입출력 예외 발생 시
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: CasperException) {
            println(e.errorCode)
            writerErrorCode(response, e.errorCode)
        } catch (e: Exception) {
            e.printStackTrace()
            writerErrorCode(response, ErrorCode.INTERNAL_SERVER_ERROR)
        }
    }

    /**
     * 에러 코드에 해당하는 HTTP 응답을 작성합니다.
     *
     * @param response HTTP 응답 객체
     * @param errorCode 에러 코드
     * @throws IOException 입출력 예외 발생 시
     */
    @Throws(IOException::class)
    private fun writerErrorCode(response: HttpServletResponse, errorCode: ErrorCode) {
        val errorResponse = ErrorResponse(errorCode.status, errorCode.message)
        response.status = errorCode.status
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}
