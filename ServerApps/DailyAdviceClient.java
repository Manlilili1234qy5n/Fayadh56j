//Daily Advice Client Code - Fayadh A- Chapter 15 HFJ
/* this program makes a socket, makes a Buffered Reader, and reads
a single line from the Server application (port: 4242) */

import java.io.*;
import java.net.*; //class socket is in java.net

public class DailyAdviceClient {
	public void go() {
		try {
			Socket s = new Socket("192.168.1.110", 4242); //make a socket connection to localhost

			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader); //chain a buffered reader to the input stream

			String advice = reader.readLine();
			System.out.println("Today you should: " + advice);
			reader.close(); //closes all streams
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DailyAdviceClient client = new DailyAdviceClient();
		client.go();
	}
}