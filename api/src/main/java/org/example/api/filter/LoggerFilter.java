package org.example.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest)request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("INIT {}", req.getRequestURI());

        chain.doFilter(req, res);//------ doFilter 기준으로 실행 전과 후로 나뉨------
        //실행 후 로그 남기기
        //request 정보

        //Header 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = req.getHeader(headerKey);

            //헤더에 authorization-token : ???, user-agent : ???
            headerValues
                    .append("[")
                    .append(headerKey)
                    .append(":")
                    .append(headerValue)
                    .append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI(); //어떤 주소로 요청했는지
        var method = req.getMethod(); //호출된 메서드

        log.info(">>>>> uri:{}, method:{} , header:{}, body:{}", uri, method, headerValues, requestBody);

        //response 정보
        var responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey ->{
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(":")
                    .append(headerValue)
                    .append("]");
        });

        var responseBody = new String(res.getContentAsByteArray());
        log.info("<<<<<< header:{}, body:{}", responseHeaderValues, responseBody );

        //responseBody 읽은 후 다시 초기화--> 초기화하지 않으면 responseBody가 빈 상태로 전달
        res.copyBodyToResponse();

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
