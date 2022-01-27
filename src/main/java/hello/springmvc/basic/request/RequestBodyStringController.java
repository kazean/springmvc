package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody v1={}", messageBody);

        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader) : HTTP 요청 메세지 바디의 내용을 직접조회
     * OutputStream(Writer) : HTTP 응답 메세지 바디에 직접 결과 출력 java.io.Writer
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody v2={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     *  HttpEntity : Http Header, body 정보를 편리하게 조회
     *  - 메세지 바디 정보를 직접 조회( @RequestParam, @ModelAttribute가 아니다!)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     *  응답에서도 HttpEntity 사용가능
     *  - 메세지 바디 정보 직접 반환(view 조회X)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     *  RequestEntity
     *  ResponseEntity
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){
        String messageBody = httpEntity.getBody();
        log.info("messageBody v3={}", messageBody);
        return new HttpEntity<>("ok");
    }

    /**
     * @RequsetBody
     * - 메세지 바디 정보를 직접조회(@RequestParam, @ModelAttribute 와 다르다)
     * - HttpMessageConver 사용 -> StringHttpMessageConver 적용
     *
     * @ResponseBody
     * - 메세지 바디 정보 직접 반환(view X)
     * - HttpMessageConver 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){
        log.info("messageBody v4={}", messageBody);
        return "ok";
    }

}
