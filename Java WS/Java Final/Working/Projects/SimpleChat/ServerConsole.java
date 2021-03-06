import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ServerConsole implements ChatIF {

	private EchoServer server;
	final public static int DEFAULT_PORT = 5556;
	
	
	
	public ServerConsole(int port) {
		
		try 
		  {
			  server= new EchoServer(port, this); 
			  server.listen();
		  } 
		  catch(IOException exception) 
		  {
			  System.out.println("Error: Can't setup connection!"
	                + " Terminating server.");
			  System.exit(1);
		  }
	}

	public static void main(String[] args) {
		
	    int port = 0;  //The port number

	    try
	    {
	      port = Integer.parseInt(args[1]);
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	    	port = DEFAULT_PORT;
	    }
	    
	    ServerConsole chat= new ServerConsole(port); //create new client console pass in the host and default port.
	    chat.accept();  //Wait for console data
	  }

	
	
	
	
	public void display(String message) 
	{
		System.out.println("> " + message);
	}
	
	public void accept() 
	  {
	    try
	    {
	      BufferedReader fromConsole = 
	        new BufferedReader(new InputStreamReader(System.in));
	      String message;

	      while (true) 
	      {
	        message = fromConsole.readLine() + "\n";
	        server.handleMessageFromServerUI(message);
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }

}