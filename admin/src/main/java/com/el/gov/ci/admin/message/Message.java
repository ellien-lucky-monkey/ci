package com.el.gov.ci.admin.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jiangkui
 * @date 2019年08月01日
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long bizId;
    private Long createBy;
    private Integer bizObj;
    private Integer bizType;
    private String diffData;
    private Date createTime;
}
