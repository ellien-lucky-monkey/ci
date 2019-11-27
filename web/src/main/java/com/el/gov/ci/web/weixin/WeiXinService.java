package com.el.gov.ci.web.weixin;

import com.el.gov.ci.web.utils.RestTemplateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jiangkui
 * @date 2019年07月09日
 */
@Service
public class WeiXinService {

    private static final String appId = "abcs4154545d5sadsadsadsa";

    private static final String redirect_uri = "www.baidu.com";
    private static final String response_type = "code";
    private static final String scope = "snsapi_login";


    private static final String secret = "secret";
    private static final String grant_type = "authorization_code";


    /**
     * ?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
     */
    private static final String weixin_code_url = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * ?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     */
    private static final String weixin_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=%s";


    public String getCode() {
        return "";
    }


    public String getAccessToken(String code) {

        String url = String.format(weixin_access_token_url, appId, secret, code, grant_type);
        RestTemplate restTemplate = RestTemplateUtils.buildRestTemplate(5000,5000,"10.59.74.70",80);
//        restTemplate.setMessageConverters();
        ResponseEntity<WeiXinResponseDTO> responseEntity = restTemplate.getForEntity(url, WeiXinResponseDTO.class);
        return "";
    }


    public String refreshToken(){
        return "";
    }

}
