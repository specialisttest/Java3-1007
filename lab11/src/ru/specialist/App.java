package ru.specialist;

import static java.lang.System.out;

class MySuperThread implements Runnable {
	private int a,b;
	
	public MySuperThread(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		for(int i=a; i <= b; i++)
			out.printf("%s %d\n", Thread.currentThread().getName(), i);
	}
}

public class App {

	public static void main(String[] args) {
		Thread t0 = new Thread(new MySuperThread(1, 100));
		Thread t1 = new Thread(new MySuperThread(101, 200));
		
		final int a = 201, b = 300;
		Thread t2 = new Thread(()->{
			for(int i=a; i <= b; i++)
				out.printf("%s %d\n", Thread.currentThread().getName(), i);
			
		});
		
		t0.start();
		t1.start();
		t2.start();

	}

}
