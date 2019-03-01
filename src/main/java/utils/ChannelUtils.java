package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;

/**
 * @author: yaofeng
 * @create:2019-03-01-16:33
 **/
public class ChannelUtils {
    public static Channel getChannelInstance(String conectionDescription){
        try {
            ConnectionFactory connectionFactory = getConnectionFactory();
            System.out.println(connectionFactory==null);
            Connection connection = connectionFactory.newConnection(conectionDescription);
            Channel channel = connection.createChannel();
            return channel;
        }catch (Exception e){
            throw new RuntimeException("获取连接失败");
        }
    }

    private static ConnectionFactory getConnectionFactory(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //配置连接信息
        connectionFactory.setHost("192.168.1.68");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("user");
        connectionFactory.setPassword("root");
        //网络异常自动连接恢复
        connectionFactory.setAutomaticRecoveryEnabled(true);
        //每10秒尝试重试连接一次
        connectionFactory.setNetworkRecoveryInterval(10000);
        //设置ConnectionFactory属性信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("principal","RobertoHuang");
        map.put("decription","rabbitmqTest");
        map.put("email","123456789@136.com");
        connectionFactory.setClientProperties(map);
        return connectionFactory;
    }
}
