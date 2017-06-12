package cs544.bank.jms;


public class JMSSender implements IJMSSender{
	
	public JMSSender(){}
	
	public void sendJMSMessage (String text){
		System.out.println("JMSSender: sending JMS message ="+text);
	}
}
