package cn.rivamed.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


//当项目启动时会自动创建下面的队列和交换机，并进行绑定
//下面配置了延时队列，用作发送延时消息
@Configuration
public class MqConfig {

    private static final String PRODUCE_QUEUE = "test";

    private static final String CONSUMER_QUEUE = "con_que";

    private static final String PRODUCE_EXCHANGE= "hello";

    private static final String DLX_EXCHANGE= "dlx";

    public static final String DLX_ROUTINGKEY= "dlx";

    public static final String PRODUCE_ROUTINGKEY= "hello";

    //生产者队列，所有发送的消息都会放到这个队列上，此队列不会有消费者
    //第一个参数：队列名，第二个参数：队列持久化，第三个参数：是否排他，如果排他则只能有一个消费者来消费此队列，第四个参数：当消费者为0时自动删除此队列，第五个参数：队列参数配置
    @Bean
    public Queue buildProducerQuere(){
        Map<String, Object> arguments = new HashMap<String, Object>();
        //绑定死信队列，当消息过期后转发到死信队列上
        arguments.put("x-dead-letter-exchange","dlx");//绑定死信队列的交换机
        arguments.put("x-dead-letter-routing-key","dlx");//绑定死信队列的routingkey
        return new Queue(PRODUCE_QUEUE,true,false,false,arguments);
    }

    //消费者队列，所有的消息都是生产者队列通过死信队列交换机转发过来的
    @Bean
    public Queue buildConsumerQuere(){
        return new Queue(CONSUMER_QUEUE,true);
    }

    //生产者交换机
    @Bean("exchange")
    public DirectExchange buildProdruceExchange(){
        return new DirectExchange(PRODUCE_EXCHANGE);
    }

    //死信队列交换机，主要是将消息转发到对应的消费者队列上
    @Bean
    public DirectExchange buildDLXExchange(){
        return new DirectExchange(DLX_EXCHANGE);
    }

    //将生产者队列和生产者交换机进行绑定
    @Bean
    public Binding bindingExchange() {
        return BindingBuilder.bind(buildProducerQuere()).to(buildProdruceExchange()).with(PRODUCE_ROUTINGKEY);
    }

    //将死信队列交换机和消费者队列进行绑定
    @Bean
    public Binding bindingConsumerExchange() {
        return BindingBuilder.bind(buildConsumerQuere()).to(buildDLXExchange()).with(DLX_ROUTINGKEY);
    }

}
