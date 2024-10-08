package ru.specialist;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.DoubleFunction;

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
	}

}
