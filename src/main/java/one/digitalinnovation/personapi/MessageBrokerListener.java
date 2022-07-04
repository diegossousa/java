package one.digitalinnovation.personapi;

import org.apache.activemq.command.ActiveMQMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageBrokerListener implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(MessageBrokerListener.class);

    @JmsListener(destination = "queue.log")
    public void onReceiverQueueLog(String str) {
        System.out.println("Fila log = " + str);
    }

    @JmsListener(destination = "topic.sendEmail", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopicSendEmail(String str) {
        System.out.println("Topic sendEmail = " + str);
    }

//    @JmsListener(destination = "ActiveMQ.Advisory.Producer.Queue.queue.log", containerFactory = "jmsFactoryTopic")
//    public void onReceiverTopic(ActiveMQMessage str) {
//        System.out.println(str);
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Iniciando Message Broker");
    }
}
