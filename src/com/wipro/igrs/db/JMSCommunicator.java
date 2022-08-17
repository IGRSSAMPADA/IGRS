package com.wipro.igrs.db;

import java.io.BufferedReader;

 
import java.io.IOException;
 
import java.io.InputStreamReader;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeMap;
 
import javax.jms.*;
 
import javax.naming.Context;
 
import javax.naming.InitialContext;
 
import javax.naming.NamingException;
 
public class JMSCommunicator
 
{
 
public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
 
public final static String JMS_FACTORY="CF1";
 
public final static String QUEUE="Queue1";
 
private QueueConnectionFactory qconFactory;
 
private QueueConnection qcon;
 
private QueueSession qsession;
 
private QueueSender qsender;
 
private Queue queue;
 
private TextMessage msg;
 
private ObjectMessage objMessage; 

public void init(Context ctx, String queueName,String connectionFactoryName)
 
throws NamingException, JMSException
 
{
 
qconFactory = (QueueConnectionFactory) ctx.lookup(connectionFactoryName);
 
qcon = qconFactory.createQueueConnection();
 
qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
 
queue = (Queue) ctx.lookup(queueName);
 
qsender = qsession.createSender(queue);
 
//msg = qsession.createTextMessage();

objMessage = qsession.createObjectMessage(); 


qcon.start();
 
}
 
public void send(String message) throws JMSException {
 
msg.setText(message);
 


qsender.send(msg);
 
}
 

public void sendObject(OTPDTO OTPDto) throws JMSException {
	 
	objMessage.setObject(OTPDto);
	qsender.send(objMessage);


	
	 
	}

public void close() throws JMSException {
 
qsender.close();
 
qsession.close();
 
qcon.close();
 
}
 

 
static void readAndSend(JMSCommunicator qs,OTPDTO otpDto)     throws IOException, JMSException
 
{

 
qs.sendObject(otpDto);
 
}
 
public InitialContext getInitialContext(String url)
 
throws NamingException
 
{
 
Hashtable<String,String> env = new Hashtable<String,String>();
 
env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
 
env.put(Context.PROVIDER_URL, url);
 
return new InitialContext(env);
 
}
 
}