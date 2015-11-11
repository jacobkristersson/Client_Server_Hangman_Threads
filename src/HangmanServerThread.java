//
//	HangmanServerThread.java
//	This is the server
//	It creates a new server with port PORT
//	Then when the socket is accepted it hands the socket to a new thread
//


import java.io.*;
import java.net.*;



public class HangmanServerThread {

	public final static int PORT = 12345;

	public static void main (String[] args) {
		      
		        try {
		            ServerSocket server = new ServerSocket(PORT);
		            Socket connection = server.accept();
						Thread task = new HangmanProtocolThread(connection);
						task.start();
		     
		        
	
		            
		            
	
		        } catch (IOException e) {
		            System.out.println("Exception caught when trying to listen on port "
		                + PORT + " or listening for a connection");
		            System.out.println(e.getMessage());
		        }
		    }

}