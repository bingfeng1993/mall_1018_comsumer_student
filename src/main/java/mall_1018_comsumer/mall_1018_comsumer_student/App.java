package mall_1018_comsumer.mall_1018_comsumer_student;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 队列模式
 *
 */
public class App {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("office");
		MessageConsumer consumer = session.createConsumer(queue);
		// 接收消息
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				// 打印结果
				TextMessage textMessage = (TextMessage) message;
				String text = "";
				try {
					text = textMessage.getText();
				} catch (JMSException e) {
					e.printStackTrace();
				}
				System.out.println("====a===="+text);
			}
		});
		// 等待接收消息
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
