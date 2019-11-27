package com.el.gov.ci.web.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jiangkui
 * @date 2019年07月22日
 */
@Component
public class SystemConstantsHelper {

    static String val1;

    @Autowired
    private void setVals(SystemConstantsProperties properties) {
        val1 = properties.val1;
    }
}
