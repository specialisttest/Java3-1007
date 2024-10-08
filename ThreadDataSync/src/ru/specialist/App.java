package ru.specialist;

import static java.lang.System.out;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

class Sync {
	public ThreadLocal<Integer> local;
	
	
	private volatile int counter = 0;
	
	public synchronized void increment() {
		counter++;
		
//		synchronized (this) {
//			counter++;	
//		}
		
	}
	
	public int getCounter() {
		return counter;
	}
}
class Sync2 {
	private static volatile int counter = 0;
	
	public static synchronized void increment() {
		//synchronized (Sync2.class) {
			counter++;
			
		//}
	}
	
	public static int getCounter() {
		return counter;
	}
}

public class App {
	

	public static void main(String[] args) throws InterruptedException {
		// StringBuilder	non Thread-safe
		// StringBuffer		Thread-safe
		
		// ArrayList		non Thread-safe
		// Vector			Thread-safe
		
		// Collections.synchronizedList(null)
		
		
		//Object sync = new Object();
		Sync sync = new Sync();
		AtomicInteger counter = new AtomicInteger();
		sync.local = new ThreadLocal<Integer>(); 
		sync.local.set(0);
		
		Thread t0 = new Thread(() ->{
			sync.local = new ThreadLocal<Integer>();
			sync.local.set(0);
			for(int i=1; i <= 1000000; i++) {
				sync.increment();
				counter.incrementAndGet();
				//sync.local.set(sync.local.get()+1);
//				synchronized(sync)	{
//					sync.counter++; //counter = counter + 1;
//				}
				
				
				//counter++;
				//out.printf("%s %d\n", Thread.currentThread().getName(), i);
			}
			out.printf("After for %s %d\n", Thread.currentThread().getName(), sync.local.get());
			
		});		
		Thread t1 = new Thread(() ->{
			sync.local = new ThreadLocal<Integer>();
			sync.local.set(0);
			for(int i=1; i <= 1000000; i++) {
				sync.increment();
				counter.incrementAndGet();
				sync.local.set(sync.local.get()+1);
//				synchronized(sync)
//				{
//					sync.counter++; //counter++; 
//				}
				
				//out.printf("%s %d\n", Thread.currentThread().getName(), i);
			}
			out.printf("After for %s %d\n", Thread.currentThread().getName(), sync.local.get());
		});	
		
		t0.start();
		t1.start();
		
		t0.join();
		t1.join();
		
//		out.println(sync.counter);
		out.println(sync.getCounter());
		out.println(counter.get());
		out.printf("Main %s %d\n", Thread.currentThread().getName(), sync.local.get());

	}

}
