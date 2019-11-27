package com.el.gov.ci.web.token;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiangkui
 * @date 2019年07月25日
 */

@Slf4j
@Service
public class TokenServiceImpl implements ITokenService {

    private static final String TOKEN_NAME = "token";
    private static final String TOKEN_PREFIX = "standard_token";
    private static final int EXPIRE_TIME_MINUTE = 20;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public String createToken() {
        String str = RandomStringUtils.random(32);
        StrBuilder token = new StrBuilder();
        token.append(TOKEN_PREFIX).append(str);
        redisTemplate.opsForValue().set(token.toString(), token.toString(), EXPIRE_TIME_MINUTE);
        return token.toString();
    }



    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        // header中不存在token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
            // parameter中也不存在token
            if (StringUtils.isBlank(token)) {
                throw new RuntimeException("token is empty");
            }
        }
        Boolean exist = redisTemplate.hasKey(token);
        if (exist == null || !exist) {
            throw new RuntimeException("");
        }
        Boolean del = redisTemplate.delete(token);
        if (del == null || !del) {
            throw new RuntimeException("");
        }
    }
}
