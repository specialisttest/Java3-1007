package ru.specialist;

import static java.lang.System.out;

class MyThread extends Thread {
	
	public MyThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for(int i=1; i <= 100; i++) {
			// this.isInterrupted()
			if (Thread.interrupted()) {
				out.printf("Interrupting %s\n", getName());
				return;
			}
			out.printf("%s %d\n", getName(), i);
		}
			
	}
}

class MySuperThread implements Runnable {
	@Override
	public void run() {
		for(int i=1; i <= 100; i++) {
			out.printf("%s %d\n", Thread.currentThread().getName(), i);
			Thread.yield();
		}
	}
}

public class App {

	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new MyThread("A");
		Thread t1 = new MyThread("B");
		Thread t2 = new Thread(new MySuperThread(), "C");
		
		Thread t3 = new Thread(
			new Runnable() {
				@Override
				public void run() {
					for(int i=1; i <= 100; i++)
						out.printf("%s %d\n", Thread.currentThread().getName(), i);
				}
			}, "D"
		);
		
		Thread t4 = new Thread(() ->{
			for(int i=1; i <= 100; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
		}, "E");		
		
		
		t1.setDaemon(true);
		t2.setPriority(Thread.MAX_PRIORITY);
		
		
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		// t0.stop(); // depricated, throw exception always
		t0.interrupt();
		
		out.printf("Is Alive for t4: %s\n", t4.isAlive());
		out.printf("State    for t4: %s\n", t4.getState());	
		
		Thread.sleep(1000);
		
		
		
		out.println(Thread.currentThread().getName()); // main
		

	}

}
