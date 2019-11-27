package com.el.gov.ci.web;

import com.el.gov.ci.web.weixin.WeiXinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jiangkui
 * @date 2019年07月09日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeiXinServiceTests {

    @Autowired
    private WeiXinService weiXinService;

    @Test
    public void getAccessToken(){
        String token = weiXinService.getAccessToken("code");
        System.out.println(token);
    }

}
