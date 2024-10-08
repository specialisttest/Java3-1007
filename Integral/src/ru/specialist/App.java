package ru.specialist;

import java.util.function.DoubleFunction;

public class App {
	
	public static final int STEPS = 10000000;
	
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

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		double r1 = singleThread(Math::sin, 0d, Math.PI/2, STEPS);
		long t2 = System.currentTimeMillis();
		
		System.out.printf("Single Thread: %f Time: %d\n", r1, t2-t1);
	}

}
