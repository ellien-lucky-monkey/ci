package com.el.gov.ci.web.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Jiangkui
 * @date 2019年07月22日
 */
@Component
public class SystemConstantsProperties {

    @Value("system.static.val1:val1")
    public String val1;
}
