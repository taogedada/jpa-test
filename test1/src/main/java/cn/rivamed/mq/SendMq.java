package cn.rivamed.mq;

import cn.rivamed.conf.MqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class SendMq {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    private Exchange exchange;

    @RequestMapping("sendMq")
    public String sendMq(){
        //第一种写法：手动创建队列(服务端已存在此队列和交换机并绑定了routingkey)
//        String routingKey = "hello";
//        String exchang = "test";
//        String context = "hello"+new Date();
//        amqpTemplate.convertAndSend(exchang,routingKey,context);
        //第二种写法：动态创建队列
        String context = "张三"+new Date();
        System.out.println(MqConfig.PRODUCE_ROUTINGKEY);
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");//时间单位是毫秒
                return message;
            }
        };
        //发送延时消息，此消息发送10秒后会被生产者队列转发到死信队列交换机再转发消费者队列，最后被消费
        amqpTemplate.convertAndSend(exchange.getName(),MqConfig.PRODUCE_ROUTINGKEY,context,messagePostProcessor);
        return "seuuess";
    }
}
