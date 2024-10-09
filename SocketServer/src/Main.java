import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

import static java.lang.System.out;


public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// 127.0.0.1:1111
		// localhost:1111
		
		ServerSocket ss = new ServerSocket(1111);
		int req = 0; // AtomicInteger
		// create pool
		
		while (true)
		{
			Socket cs = ss.accept();
			
			// create task
			out.printf("Accept connection from %s\n",
					cs.getInetAddress().toString());
			
			BufferedReader reader = new BufferedReader( 
				new InputStreamReader(cs.getInputStream(), 
					Charset.forName("UTF-8")));
			
			String s = reader.readLine();
			
			out.printf("%s . %d\n",s,++req);
			//Thread.sleep(100);
			
			OutputStreamWriter writer = new OutputStreamWriter(
					cs.getOutputStream(),
					Charset.forName("UTF-8"));
			
			writer.write(s.toUpperCase()+"\n");
			writer.flush();
			
			
			
			
		}

	}

}
