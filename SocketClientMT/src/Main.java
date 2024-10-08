import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

public class Main {

	public static final int CONNECTIONS = 100;
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Runnable task = () -> {
			try {
				Socket cs = new Socket("localhost", 1111);
				OutputStreamWriter writer = new OutputStreamWriter(cs.getOutputStream(), Charset.forName("UTF-8"));
				writer.write("test: "+String.valueOf(Thread.currentThread().getId())+"\n");
				writer.flush();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(cs.getInputStream(), Charset.forName("UTF-8")));
				out.println(reader.readLine());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
		
		
		ExecutorService p = Executors.newFixedThreadPool(CONNECTIONS);
		for(int i=0; i < CONNECTIONS; i++)
			p.submit(task);
		
		p.shutdown();


	}

}
