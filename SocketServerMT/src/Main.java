import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;


public class Main {

	//volatile static int req = 0;
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// 127.0.0.1:1111
		ServerSocket ss = new ServerSocket(1111);
		//Object syncReq = new Object();
		
		ExecutorService pool =Executors.newCachedThreadPool(); 
				//Executors.newFixedThreadPool(10);
		
		AtomicInteger req = new AtomicInteger(); // 0
		
		while (true)
		{
			final Socket cs = ss.accept();
			
			Runnable r = 
			()->{
				try {
					out.printf("Accept connection from %s\n",
							cs.getInetAddress().toString());
					BufferedReader reader = new BufferedReader( 
						new InputStreamReader(cs.getInputStream(), 
							Charset.forName("UTF-8")));
					String s = reader.readLine();
					//synchronized (syncReq) {
					//	req++;
					//}
					out.printf("%s . %d\n",s, req.incrementAndGet());
					//Thread.sleep(100);
					
					OutputStreamWriter writer = new OutputStreamWriter(
							cs.getOutputStream(),
							Charset.forName("UTF-8"));
					
					writer.write(s.toUpperCase()+"\n");
					writer.flush();
				}
				catch(IOException ex) {
					System.err.println(ex.getMessage());
				}
			};
			
			pool.submit(r);
			
		}
		
		//pool.shutdown();

	}

}
