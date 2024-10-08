package ru.specialist;

import static java.lang.System.out;

public class App {
	
	static void test(int k) {
		if (k < 0 )throw new IllegalArgumentException("k < 0");
	}
	

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(()->{
			out.println("Sleeping....");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				out.printf("Thread %s interrupted.", Thread.currentThread().getName());
			}
			
			out.printf("Throwing exception from thread %s...\n", Thread.currentThread().getName());
			throw new RuntimeException("My exception message");
			
		});
		
		t.setUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler() {
					public void uncaughtException(Thread th, Throwable ex) {
				        out.printf("Uncaught exception from thread %s handled by thread %s (specific): %s\n",
			        			th.getName(), Thread.currentThread().getName(), ex);
					}
				}				
		);
		
		Thread.setDefaultUncaughtExceptionHandler((th, ex)->{
			out.printf("Uncaught exception from thread %s handled by thread %s (unspecific): %s\n",
        			th.getName(), Thread.currentThread().getName(), ex);
		});
		
		t.start();
		Thread.sleep(200);
		t.interrupt();
		
		test(-50);
		
		Thread.sleep(2000);
		out.println("main");
		
		
	}

}
