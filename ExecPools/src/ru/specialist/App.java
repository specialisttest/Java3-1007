package ru.specialist;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class MyCallTask implements Callable<Long>{
	@Override
	public Long call() throws Exception {
		
		System.out.println(Thread.currentThread().getName());
		
		long summa = 0;
		for(long i=1; i <= 100000000L; i++)
			if (Thread.interrupted()) return -1L;
			else 
				summa += i;
		
		System.out.printf("%s finished\n", Thread.currentThread().getName());
		
		return summa;
	}
	

}

public class App {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		ExecutorService pool = Executors.newFixedThreadPool(10, r->{
//			Thread t = new Thread(r);
//			t.setDaemon(true);
//			t.setPriority(Thread.MAX_PRIORITY);
//			return t;
//		});
		
		ExecutorService pool = Executors.newWorkStealingPool(
					Runtime.getRuntime().availableProcessors() * 2);  
				
				//Executors.newSingleThreadExecutor();
				//Executors.newFixedThreadPool(10);
				//Executors.newCachedThreadPool();
		
		long t1 = System.currentTimeMillis();
		
		Future<Long>[] tasks = new Future[10];
		for(int i = 0; i < 10 ; i++)
			tasks[i] = pool.submit(new MyCallTask());
		
		tasks[4].cancel(true);
		
		//Thread.sleep(60500);
		Future<Long> f11 = pool.submit(new MyCallTask());
		
		for(Future<Long> t : tasks)
			if (t.isCancelled())
				System.out.println("canceled");
			else
				System.out.println(t.get());
		
		System.out.println(f11.get());
		
		long t2 = System.currentTimeMillis();
		
		System.out.printf("Time (ms): %d\n", t2-t1);
		
		System.out.println(pool.isShutdown());
		pool.shutdown();
		System.out.println(pool.isShutdown());
		pool.awaitTermination(1, TimeUnit.SECONDS);
		
		System.out.println(pool.isTerminated());
		

	}

}
