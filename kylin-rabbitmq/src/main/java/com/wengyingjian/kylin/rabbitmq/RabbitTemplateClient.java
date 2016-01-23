package com.wengyingjian.kylin.rabbitmq;

import com.wengyingjian.kylin.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wengyingjian on 16/1/23.
 */
@Component
public class RabbitTemplateClient {
    private Logger logger = LoggerFactory.getLogger(RabbitTemplateClient.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.event.entry}")
    private String queueName;

    public void sendMessgeToMq(MessageBean messageBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("target", "PUSH");
        map.put("param", messageBean);
        String str = JsonUtil.getJsonFromObject(map);
        logger.info("send message to MQ:" + str);
        rabbitTemplate.convertAndSend(queueName, str);
    }
}
