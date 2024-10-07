package ru.specialist;

import static java.lang.System.out;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new Thread(() ->{
			for(int i=1; i <= 100; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
		});		
		Thread t1 = new Thread(() ->{
			for(int i=1; i <= 100; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
		});	
		
		t0.start();
		t1.start();
		
		
		// Thread.sleep(1000);
		t0.join();
		t1.join(10000);
		
		out.println(Thread.currentThread().getName()); // main

	}

}
