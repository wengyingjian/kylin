package com.wengyingjian.kylin.rabbitmq;

/**
 * Created by wengyingjian on 16/1/23.
 */
@Componment
public class RabbitTemplateClient {
    private Logger logger = LoggerFactory.getLogger(RabbitTemplateClient.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.event.entry}")
    private String queueName;

    public void sendMessgeToMq(MessageBean messageBean){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("target", "PUSH");
        map.put("param", messageBean);
        String str = JsonUtils.toJson(map);
        logger.info("send message to MQ:" + str);
        rabbitTemplate.convertAndSend(queueName, str);
    }
}
