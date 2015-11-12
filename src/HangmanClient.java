//	TEMPORARY solution for client
//	HangmanClient.java
//	This is a temporary solution for the client to connect to the server
//	We need a client that have a GUI and is multithreaded

import java.io.*;
import java.net.*;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class HangmanClient {
	public static void main(String[] args) throws IOException {
		JTextField ip = new JTextField(5);
		JTextField port = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Ip:"));
		myPanel.add(ip);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Port:"));
		myPanel.add(port);

		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "Connect");

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Enter Ip adress and Port", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}

		if (result == JOptionPane.OK_OPTION) {
			System.out.println("Ip value: " + ip.getText());
			System.out.println("Port value: " + port.getText());

			String hostName =  ip.getText();
			int portNumber = Integer.parseInt(port.getText());


	
			try (
					Socket kkSocket = new Socket(hostName, portNumber);
					PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(kkSocket.getInputStream()));
					) {
				BufferedReader stdIn =
						new BufferedReader(new InputStreamReader(System.in));
				String fromServer;
				String fromUser;

				while ((fromServer = in.readLine()) != null) {
					System.out.println("Server: " + fromServer);
					if (fromServer.equals("Bye."))
						break;

					fromUser = stdIn.readLine();
					if (fromUser != null) {
						System.out.println("Client: " + fromUser);
						out.println(fromUser);
					}
				}
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + hostName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " +
						hostName);
				System.exit(1);
			}
		}
	}
}