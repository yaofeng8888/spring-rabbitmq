package test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import utils.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author: yaofeng
 * @create:2019-03-01-16:43
 **/
public class MessagesProducer {
    public static void main(String[] args)throws IOException, TimeoutException{
        Channel channel = ChannelUtils.getChannelInstance("生产者");
        //声明交换机(交换机名、交换机类型、是否持久化、是否自动删除、是否是内部交换机、交换机属性)
        channel.exchangeDeclare("roberto", BuiltinExchangeType.DIRECT,true,false,false,new HashMap<String, Object>());
        // 设置消息属性 发布消息 (交换机名, Routing key, 可靠消息相关属性 后续会介绍, 消息属性, 消息体);
        AMQP.BasicProperties build = new AMQP.BasicProperties().builder().deliveryMode(2).contentType("UTF-8").build();
        channel.basicPublish("roberto","add",false,build,"测试生产者".getBytes());
    }
}
