package com.movingimage24.cdi.jms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.XAConnection;
import javax.jms.XAConnectionFactory;
import javax.jms.XASession;

public class JmsApp {
    @Resource(mappedName = "java:/JmsXA")
    private XAConnectionFactory xaConnectionFactory; // we want to deliver JMS messages withing an XA transaction

    // use our JMS queue. Note that messages must be persistent in order for them to survive an AS restart
    @Resource(mappedName = "java:/jms/queue/MovieQueue")
    private Queue queue;

    public void send(String msg)
    {
        XAConnection connection = null;

        try {
            connection = xaConnectionFactory.createXAConnection();
            XASession session = connection.createXASession();
            MessageProducer messageProducer = session.createProducer(queue);

            connection.start();
            TextMessage message = session.createTextMessage();
            message.setText(msg);

            messageProducer.send(message);
            messageProducer.close();
        } 
        catch(JMSException e)
        {
        	throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                	throw new RuntimeException(e);
                }
            }
        }
    }
}
