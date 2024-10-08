package ru.specialist;

import static java.lang.System.out;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
/*
 * R R - 
 * R W -
 * W W -
 * 
 */

class MyThread extends Thread
{
	private static Lock lock = 
		new ReentrantLock();
	
	@Override
	public void run() {
		try {
			while (!lock.tryLock(1, TimeUnit.MILLISECONDS))
				System.out.printf("%s waiting........\n", this.getName());
			try
			{
				for(int i=1; i <=100; i++)
					System.out.printf("%s  - %d\n",
							this.getName(), i);
			}
			finally {
				lock.unlock();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
public class App {
	
	static void test() {
		Lock l = new ReentrantLock();
		Condition greater50 = l.newCondition();
		Condition greater70 = l.newCondition();
		
		Thread t0 = new Thread(
				() -> {
					for(int i = 1; i <= 100; i++) {
						out.printf("%s : %d\n", Thread.currentThread().getName(), i);
						l.lock();
						try {
							if (i >= 50) greater50.signalAll();
							if (i >= 70) greater70.signalAll();
						} finally {
							l.unlock();
						}
						
					}
				});	

		Thread t1 = new Thread(
				() -> {
					l.lock();
					try {
						greater50.awaitUninterruptibly();
					} finally {
						l.unlock();
					}
					
					for(int i = 1; i <= 100; i++) {
						out.printf("%s : %d\n", Thread.currentThread().getName(), i);
					}
				});	
		
		Thread t2 = new Thread(
				() -> {
					
					l.lock();
					try {
						greater70.awaitUninterruptibly();
					} finally {
						l.unlock();
					}
					
					for(int i = 1; i <= 100; i++) {
						out.printf("%s : %d\n", Thread.currentThread().getName(), i);
					}
				});	
		t0.start();
		//Thread.sleep(500);
		t1.start();
		t2.start();
		
	}
	
	

	public static void main(String[] args) {
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		t1.start();
		t2.start();
		test();
	}

}
