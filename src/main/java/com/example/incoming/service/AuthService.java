package com.example.incoming.service;

import com.example.incoming.dto.request.UserReqDto;
import com.example.incoming.entity.User;
import com.example.incoming.exception.NotUnauthorizedException;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static java.time.Instant.now;

@Service
public class AuthService {
    private final String secret = "U2VjcmV0X2tleV9mb3JfZ2VuZXJhdGVfSldUX3Rva2Vu";
    private final Key hmac = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
    private final UserRepositoryImpl userRepository;

    public AuthService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public String createAuthToken(UserReqDto user) {
        return Jwts.builder()
                .claim("login", user.getLogin())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now()))
                .setExpiration(Date.from(now().plus(2, ChronoUnit.HOURS)))
                .signWith(hmac)
                .compact();
    }

    public Jws<Claims> parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(hmac)
                .build()
                .parseClaimsJws(jwt);
    }

    public String getLoginFromJWT(String jwt) {
        Jws<Claims> jws = parseJWT(jwt);
        Claims claims = jws.getBody();
        return (String) claims.get("login");
    }

    public void checkJwt(String jwt) {
        String login = getLoginFromJWT(jwt);
        User user = userRepository.getUser(login);
        if (!user.getJwt().equals(jwt))
            throw new NotUnauthorizedException("jwt not equals for jwtUser with login: " + user.getLogin());
    }

    public String hashPassword(String password) {
        String hashPassword = null;
        if (null == password) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            hashPassword = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }
}

