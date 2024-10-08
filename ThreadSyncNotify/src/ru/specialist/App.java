package ru.specialist;

import static java.lang.System.out;

public class App {

	public static void main(String[] args) throws InterruptedException {
		class Sync
		{
			volatile int counter = 0;
		}
		
		Sync sync = new Sync();
		
		Thread t0 = new Thread(() ->{
			for(int i=1; i <= 100; i++) {
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
				synchronized(sync) {
					sync.counter = i;
					//sync.notify();
					sync.notifyAll();
				}
			}
		});		
		Thread t1 = new Thread(() ->{
			
			try {
				synchronized(sync) {
					while(sync.counter < 50)
						sync.wait();
						//sync.wait(timeout);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i=1; i <= 100; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
		});		
		Thread t2 = new Thread(() ->{
			
			try {
				synchronized(sync) {
					while(sync.counter < 70)
						sync.wait();
						//sync.wait(timeout);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i=1; i <= 100; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
		});		
		
		t0.start();		
		t1.start();
		t2.start();

	}

}
