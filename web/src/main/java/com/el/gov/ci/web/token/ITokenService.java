package com.el.gov.ci.web.token;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiangkui
 * @date 2019年07月25日
 */
public interface ITokenService {


    void checkToken(HttpServletRequest request);

    String createToken();

}
