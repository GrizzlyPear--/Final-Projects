




import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *

 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  public ChatClient(String host, int port, String userName, ChatIF clientUI) 
		    throws IOException 
		  {
		    super(host, port); //Call the superclass constructor
		    this.clientUI = clientUI;
		    openConnection();
		    sendToServer("#Login " + userName);
		  }
  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  if(msg instanceof Envolope)
	  {
		  Envolope e = (Envolope)msg;
		  int[][] board = (int[][])(e.getCommand());
		  
		  for(int i=0; i<3; i++){
			  for(int j=0; j<3; j++)
				  ((GUIConsole)clientUI).displayBoard(""+board[i][j]);
		  ((GUIConsole)clientUI).displayBoard("\n");
	  }
	  }
	  else{
		  clientUI.display(msg.toString());
	  }
  }
	  

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
	  if(message.indexOf("#")==0){
		  handleClientCommand(message);
	  }
	  else{
		  try
		  {    	
			  sendToServer(message);
		  }
		  catch(IOException e)
		  {
			  clientUI.display
			  ("Could not send message to server.  Terminating client.");
			  quit();
		  }
	  }
  }
  
 public void handleClientCommand(String message){
	 
	 if(message.indexOf("#setPort")==0){
		 
		 int space = message.indexOf(" ");
		 int end = message.length();		 
		 String newPort = message.substring(space, end);
		 newPort = newPort.trim();
		 int portNum = Integer.parseInt(newPort);		 
		 setPort(portNum);		 
		 clientUI.display("Port now set to "+ getPort());
		 
	 }
	 	 	
	 else if(message.indexOf("#ping")==0){
		 clientUI.display("I said ping");
		 tryToSendToServer(message);
	 }
	 
	 else if(message.indexOf("#testEnvolope")==0)
	 {
		 Envolope e = new Envolope(message, "#ping");
		 clientUI.display("Sending envolope");
		 tryToSendToServer(e);
		 
	 }
	 
	 else if(message.indexOf("#TicTacToe")==0){
		 int[][] board = new int[3][3];
		 Envolope e = new Envolope("#TicTacToe", board, "bob", "doug");
		 tryToSendToServer(e);
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 else if(message.indexOf("#Connect4")==0){
		 
		 
		  
		
		String[][] board = new String[7][15];
	
		    for (int i =0;i<board.length;i++)
		    {
		       		   
		       for (int j =0;j<board[i].length;j++)
		      {
		        if (j% 2 == 0) board[i][j] ="|";
		        else board[i][j] = " ";
		         
		  
		        if (i==6) board[i][j]= "-";
		      }
		       
		    }
		Envolope e = new Envolope("#Connect4", board, "bob", "doug");  
		tryToSendToServer(e); 
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 else if (message.indexOf("#TTT")==0){
		 tryToSendToServer(message);
	 }
	 
	 else {
		 tryToSendToServer(message);
	 }
 
	 
	/** else{
		 try{
		 sendToServer(message);
		 }
		 catch(IOException ioe)
		 {
			 ioe.printStackTrace();
	 }
	 }*/
 }
 
 public void tryToSendToServer(Object message){
	 try{
		 sendToServer(message);		 
	 }
	 catch(IOException ioe)
	 {
		 ioe.printStackTrace();
	 }
 }
  
    //This method terminates the client.
   
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
  
  protected void connectionException( Exception exception){
	  System.out.println("The server has terminated the connection");
  }
}
//End of ChatClient class
