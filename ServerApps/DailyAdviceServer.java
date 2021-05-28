// DailyAdviceServer code

import java.io.*;
import java.net.*;

public class DailyAdviceServer {

	String[] adviceList = {"Take smaller bites!", "Matthew is a nigga, you ought to call him that",
	"One word: inappropriate", "Just for today be honest, tell your boss what you *Really* think of her", 
	"You might want to rething that haircut!", "Who's the uglisest of them all?", "Don't cry because it's over, smile because it happened.",
"Be yourself; everyone else is already taken", "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe",
"A room without books is like a body without a soul.", "You only live once, but if you do it right, once is enough", 
"If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.", 
"I've learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel"};

	public void go() {
		try {
			ServerSocket serverSock = new ServerSocket(4242); //listen for clientrequests on port 4242

			while (true) {
				// permenant loop waiting for and servicing client requests
				Socket sock = serverSock.accept();
				// accept() just blocks until a request comes in and a socket is returned

				PrintWriter writer = new PrintWriter(sock.getOutputStream()); // use socket connection to make a printwriter
				String advice = getAdvice();
				writer.println(advice);
				writer.close();
				System.out.println(advice);
			}
		}	catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private String getAdvice() {
		int random = (int) (Math.random() * adviceList.length);
		return adviceList[random];
	}

	public static void main(String[] args) {
		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
	}

}