package com.devtw.store.domain.interceptor;

import com.devtw.store.common.JwtProvider;
import com.devtw.store.domain.user.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1].trim();
            // 토큰이 없거나 유효하지 않으면 401 Unauthorized 응답 전송
            if (!jwtProvider.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            // Payload 내 Member 객체 정보 추출
            String user = objectMapper.writeValueAsString(jwtProvider.getUserInfo(token).get("user"));
            User accessUser = objectMapper.readValue(user, User.class);

            // 추출한 정보 request 객체에 적재
            request.setAttribute("user", accessUser);
            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.error("Error in JwtInterceptor: {}", e.getMessage(), e);
            throw new Exception("Authentication failed"); // 예외를 다시 던지거나, 적절한 방식으로 처리
        }
    }

}
