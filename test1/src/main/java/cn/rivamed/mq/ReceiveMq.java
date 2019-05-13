package cn.rivamed.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ReceiveMq {

    //监视的队列
    @RabbitListener(queues = "hello")
    //处理方法
    @RabbitHandler
    public void process(@Header(AmqpHeaders.RECEIVED_ROUTING_KEY)String key, String message){
        System.out.println(key+"======="+message);
    }

    @RabbitListener(queues = "hello2")
    @RabbitHandler
    public void process2(@Header(AmqpHeaders.RECEIVED_ROUTING_KEY)String key, String message){
        System.out.println(key+"++++"+message);
    }

    @RabbitListener(queues = "con_que")
    @RabbitHandler
    public void process3(@Header(AmqpHeaders.RECEIVED_ROUTING_KEY)String key, String message){
        System.out.println(key+"***"+message);
    }
}
