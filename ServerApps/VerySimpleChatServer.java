// chatclient server for both clients
import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {

	ArrayList<PrintWriter> clientOutputStreams; //array list of output streams

	public class ClientHandler implements Runnable {
		BufferedReader reader; //create a buffered reader
		Socket sock; // and a socket

		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream()); //inputstream reader for socket
				reader = new BufferedReader(isReader); //chain bufferedreader to input stream
			} catch (Exception ex) {ex.printStackTrace();}
		} // close constructor

		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("Read" + message);
					tellEveryone(message); //send message to every socket connected?
				}
			} catch (Exception ex) {ex.printStackTrace();}
		}
	} //close the inner class

	public static void main(String[] args) {
		new VerySimpleChatServer().go();
	}

	public void go() {
		clientOutputStreams = new ArrayList<PrintWriter>();
		try {
			ServerSocket serverSock = new ServerSocket(5000); //open a serversocket at tcp 5000
			while (true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);

				// threading!
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("Got a connection!");
			}
		} catch (Exception ex) {ex.printStackTrace();}
	}

	public void tellEveryone(String message) {
		Iterator it = clientOutputStreams.iterator(); //whats this?
		while(it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	// end
}
