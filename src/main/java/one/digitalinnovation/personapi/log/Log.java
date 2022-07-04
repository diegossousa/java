package one.digitalinnovation.personapi.log;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


//@Service
public class Log {

    private static final Logger logger = LogManager.getLogger(Log.class);

    @JmsListener(destination = "queue.log")
    public void onReceiverQueueLog(String str) {
        logger.atInfo().log(str);
    }

    @JmsListener(destination = "topic.sendEmail", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopicSendEmail(String str) {
        logger.atInfo().log(str);
    }

    @JmsListener(destination = "ActiveMQ.Advisory.Connection", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopic(ActiveMQMessage str) {
        logger.atInfo().log(str);
    }
}
