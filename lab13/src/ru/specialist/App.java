package ru.specialist;

public class App {

	public static void main(String[] args) {
		class Sync
		{
			volatile double x = 1d;
			volatile boolean phase = true;  // phase == true (t1, t2) phase == false (t2, t1)
		}
		
		final Sync s = new Sync();
		
		Thread t1 = new Thread(()->{
			for(int i=0; i < 10; i++)
			{
				synchronized(s)
				{
					while(!s.phase)
						try {
							s.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
					s.x = Math.sin(s.x);
					System.out.println(s.x);
					s.phase = !s.phase;
					s.notify(); // s.notifyAll();
				}
				
			}
			
			
		});
		Thread t2 = new Thread(()->{
			for(int i=0; i < 10; i++)
			{
				synchronized(s)
				{
					while(s.phase)
						try {
							s.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
					s.x = Math.asin(s.x);
					System.out.println(s.x);
					s.phase = !s.phase;
					s.notify();
				}
				
			}
			
		});
		
		t1.start();
		t2.start();
		

	}

}
