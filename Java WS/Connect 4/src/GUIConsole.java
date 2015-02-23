import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;


public class GUIConsole extends JFrame implements ChatIF {

	private JButton closeB = new JButton("Close");
	private JButton openB = new JButton("Open");
	private JButton sendB = new JButton("Send");
	private JButton quitB = new JButton("Quit");
		
	private JTextField portTxF = new JTextField("5556");
	private JTextField hostTxF = new JTextField("127.0.0.1");
	private JTextField messageTxF = new JTextField("");
		
	private JLabel portLB = new JLabel("Port: ", JLabel.RIGHT);
	private JLabel hostLB = new JLabel("Host: ", JLabel.RIGHT);
	private JLabel messageLB = new JLabel("Message: ", JLabel.RIGHT);
		
	private JTextArea messageList = new JTextArea();
	
	private ChatClient client;
	
	final public static int DEFAULT_PORT = 5556;
	  

	public  GUIConsole ( String host, int port, String userName)
	{
			
			super("Simple Chat GUI");
			setSize(300, 400);
			
			setLayout( new BorderLayout(5,5));
			Panel bottom = new Panel();
			add( "Center", messageList );
			add( "South" , bottom);
			//add( "East" , messageList);
			
			bottom.setLayout( new GridLayout(5,2,5,5));
			bottom.add(hostLB); 		bottom.add(hostTxF);
			bottom.add(portLB); 		bottom.add(portTxF);
			bottom.add(messageLB); 	bottom.add(messageTxF);
			bottom.add(openB); 		bottom.add(sendB);
			bottom.add(closeB); 		bottom.add(quitB);
			  	 
			
			
			
			
			sendB.addActionListener( new ActionListener() 
			{
			public void actionPerformed(ActionEvent e)
				{
					send();
					//display(messageTxF.getText()+"\n" );
				}
			});	
			
			try 
		    {
		      client= new ChatClient(host, port, userName, this);
		    } 
		    catch(IOException exception) 
		    {
		      System.out.println("Error: Can't setup connection!"
		                + " Terminating client.");
		      System.exit(1);
		    }
			
			quitB.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					client.handleMessageFromClientUI("Good Bye!");
					try {
						client.closeConnection();
					} catch (IOException e1) {
						
						display(e1.toString());
					}
					System.exit(0);
					
				}
				
			});
			
			closeB.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					client.handleMessageFromClientUI("Good Bye!");
					try {
						client.closeConnection();
					} catch (IOException e1) {
						
						display(e1.toString());
						
					}	
				}});
			
			
			openB.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
				
					
					client.setHost(hostTxF.getText());
					client.setPort(Integer.parseInt(portTxF.getText()));
					try {
						
						client.openConnection();
						client.sendToServer("#Login Dude");
					} catch (IOException e1) {
						
						display("Could not connect, trying default settings\n> " + e1.toString());
						client.setPort(DEFAULT_PORT);
						try {
							client.openConnection();
														
						} catch (IOException e2) {
							
							display("Could not connect.\n> " + e2.toString());
						}
					}
					
					display("Succesfully Conencted");
				}});
			
			
			
			setVisible(true);
	}

	

	@Override
	public void display(String message) {
		// add the message to the message list
		messageList.insert(message+"\n", 0);
		

	}
	
	public void displayBoard(String message) {
		// add the message to the message list
		messageList.insert(message, 0);
		
	}
	

	
 public void send()
 {
	 String message = messageTxF.getText();
	 if(message!=null && message.length()>0){
     client.handleMessageFromClientUI(message);
	 }
	 
	 messageTxF.setText("");
 }


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String host = "";
	    int port = 0;  //The port number
	    String userName = "";
	    
	    try
	    {
	      port = Integer.parseInt(args[1]);
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      port = DEFAULT_PORT;
	    }
	    
	    
	    try
	    {
	      host = args[0];
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      host = "localhost";
	    }
	    
	    try
	    {
	    	userName = args[2];
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	    	userName ="doug";
	    }
		
		GUIConsole clientConsole = new GUIConsole(host, port, userName);
		
		
		
	}

}
