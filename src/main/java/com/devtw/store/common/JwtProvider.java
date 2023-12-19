package com.devtw.store.common;

import com.devtw.store.domain.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Setter
//@PropertySource("/WEB-INF/config/api.properties")
@Component
public class JwtProvider {
    /* 서명에 사용할 secretKey 설정은 xml에서 property로 직접등록 */
    @Value(value = "${jwt.secret}")
    private String secretKey;

    public boolean validateToken(String token) {
        try {
            // 토큰 파싱 및 유효성 검사
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            // 여기에서 필요한 추가 검증 로직을 추가할 수 있습니다.
            // 예를 들어, 토큰의 만료 여부, 사용자 권한 등을 확인할 수 있습니다.

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String createToken(User user) {
        Date now = new Date(System.currentTimeMillis());
        long expiration = 1000 * 60 * 60L;

        return Jwts.builder().setHeaderParam("typ", "JWT") // 토큰 타입 지정
                .setSubject("accessToken") // 토큰 제목
                .setIssuedAt(now) // 발급시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료시간
                .claim("user", user) // 회원 아이디 저장
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    /* 토큰 해석 메소드 */
    public Claims getUserInfo(String token) throws Exception {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token);
        return claims.getBody();
    }

}
