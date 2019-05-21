import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Map;

/**
 * created by Jay on 2019/5/20
 */
public class Producer
{
    private final static String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.30");
        factory.setPort(5672);
        // 虚拟机
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection conn = factory.newConnection();
        // 创建消息通道
        Channel channel = conn.createChannel();
        String msg = "Hello world, Rabbit MQ";
        // 声明队列
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息（发送到默认交换机AMQP Default，Direct）
        // 如果有一个队列名称跟Routing Key相等，那么消息会路由到这个队列
        // String exchange, String routingKey, BasicProperties props, byte[] body
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println(456);
        channel.close();
        conn.close();
    }
}
