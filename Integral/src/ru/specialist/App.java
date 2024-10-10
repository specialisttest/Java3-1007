package ru.specialist;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleFunction;
import java.util.stream.Stream;

public class App {
	
	public static final int STEPS = 10000000;
	public static final int TASKS = 8;
	
	public static double singleThread(DoubleFunction<Double> f, double a, double b, int steps) {
		double w = (b - a) / steps;
		double summa = 0d;
		
		for(int i = 0; i < steps; i++) {
			double x = a + w * i + w / 2;
			double y = f.apply(x);
			summa += w * y;
		}
		
		return summa;
		
	}
	
	public static double multiThread(DoubleFunction<Double> f, double a, double b) throws InterruptedException, ExecutionException {
		ExecutorService p = Executors.newWorkStealingPool();
				//Executors.newFixedThreadPool(TASKS);
		double h = (b-a) / TASKS;
		
		Future<Double>[] tasks = new Future[TASKS];
		for(int i=0; i < TASKS; i++) {
			final double ax = a + h*i;
			final double bx = ax + h;
			tasks[i] = p.submit( ()-> singleThread(f, ax, bx, STEPS/TASKS) );
		}
		
		double summa =0d;
		for(Future<Double> task : tasks) 
			summa += task.get();
		
		p.shutdown();
		
		return summa;
	}
	
	public static double multiThread2(DoubleFunction<Double> f, double a, double b) throws InterruptedException, ExecutionException {
		double h = (b-a) / TASKS;
		
		ForkJoinTask<Double>[] tasks = new ForkJoinTask[TASKS];
		
		var p = new ForkJoinPool();
		p.execute(()->{
			for(int i=0; i < TASKS; i++) {
				double ax = a + h*i;
				double bx = ax + h;
				var task = new ForkJoinTask<Double>() {
					double r;
					@Override
					public Double getRawResult() {return r;}
					@Override
					protected void setRawResult(Double value) {r=value;}
					@Override
					protected boolean exec() {
						r = singleThread(f, ax, bx, STEPS/TASKS);
						System.out.println(Thread.currentThread().getName());
						return true;
					}
				};
				task.fork();
				tasks[i] = task;
			}
			
		});
		p.shutdown();
		p.awaitTermination(1000, TimeUnit.MILLISECONDS);
		double sum=0d; 
		for(var t : tasks)
			sum += t.join();
		
		return sum;
	}	
	
	public static double multiThread3(DoubleFunction<Double> f, double a, double b) {
		record Pair(double ax, double bx) {}
		
		double w = (b-a) / TASKS;
		//Stream.Builder<Pair> sb = Stream.<Pair>builder();
		var sb = Stream.<Pair>builder();
		for(int i = 0; i < TASKS; i++)
			sb.add(new Pair(a + w * i, a + w *(i+1)));
		
		return sb.build()
				.parallel()
				.mapToDouble( p->singleThread(f, p.ax(), p.bx(), STEPS/TASKS) )
				.sum();
		
		
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int a = 5;
		
		int b = 7;
		
		a = 10;
		
		//while (a == 5 && b ==7)
		
		
		long t1 = System.currentTimeMillis();
		double r1 = singleThread(Math::sin, 0d, Math.PI/2, STEPS);
		long t2 = System.currentTimeMillis();
		
		System.out.printf("Single Thread: %f Time: %d\n", r1, t2-t1);
		
		
		long t3 = System.currentTimeMillis();
		double r2 = multiThread(Math::sin, 0, Math.PI/2);
		long t4 = System.currentTimeMillis();
		System.out.printf("Multi  Thread: %f Time: %d\n", r2, t4-t3);
		
		long t5 = System.currentTimeMillis();
		double r3 = multiThread2(Math::sin, 0, Math.PI/2);
		long t6 = System.currentTimeMillis();
		System.out.printf("Multi  Thread: %f Time: %d\n", r3, t6-t5);
		
		long t7 = System.currentTimeMillis();
		double r4 = multiThread3(Math::sin, 0, Math.PI/2);
		long t8 = System.currentTimeMillis();
		System.out.printf("Multi  Thread: %f Time: %d\n", r4, t8-t7);			
	}

}
