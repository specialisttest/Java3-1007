package ru.specialist;

import static java.lang.System.out;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new Thread(() ->{
			for(int i=1; i <= 1000; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
		});		
		Thread t1 = new Thread(() ->{
			try {
				while(t0.getState() == Thread.State.NEW)
					Thread.sleep(10); // Thread.yield()
				
				t0.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i=1; i <= 1000; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
		});	
		
		t1.start();
		Thread.sleep(300);
		t0.start();
		
		
		//Thread.currentThread().join(); //deadlock
		
		

	}

}
