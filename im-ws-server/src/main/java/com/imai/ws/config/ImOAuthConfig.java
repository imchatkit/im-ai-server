package com.imai.ws.config;

import cn.hutool.crypto.PemUtil;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class ImOAuthConfig {

    @Bean
    @SneakyThrows
    public Algorithm jwtAlgorithm(ResourceLoader resourceLoader) {
        Resource pubKeyResource = resourceLoader.getResource("classpath:keys/public.key");
        Resource priKeyResource = resourceLoader.getResource("classpath:keys/private.key");
        RSAPublicKey rsaPublicKey = (RSAPublicKey) PemUtil.readPemPublicKey(pubKeyResource.getInputStream());
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) PemUtil.readPemPrivateKey(priKeyResource.getInputStream());
        return Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
    }

}
