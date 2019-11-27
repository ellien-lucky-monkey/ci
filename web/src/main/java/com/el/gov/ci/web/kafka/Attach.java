package com.el.gov.ci.web.kafka;

import java.time.LocalDateTime;

/**
 * @author Jiangkui
 * @date 2019年07月22日
 */
public class Attach {


     static class Message {

        private String body;
        private String topic;
        private LocalDateTime createTime;

        public Message() {
        }

        public Message(String body, String topic, LocalDateTime createTime) {
            this.body = body;
            this.topic = topic;
            this.createTime = createTime;
        }


        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public LocalDateTime getCreateTime() {
            return createTime;
        }

        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }
    }
}
