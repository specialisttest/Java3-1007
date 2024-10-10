package ru.specialist;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

record Course(String title, int length) {}

class Борщ {}

public class App {

	public static void main(String[] args) {
		
		var борщ = new Борщ();
		
		
		// y = f(x)
		// y = sin(x)
		// y = log(x)
		// y = sin(pi*log(x)); 
		
		/*Course c = new Course("Java 1", 40);
		out.println(c);
		out.println(c.title());*/
		
		List<Course> courses = List.of(
				new Course("Java 1. Intro", 40),
				new Course("Java 2. OOP", 40),
				new Course("GIT", 16),
				new Course("Pattern OOP", 24),
				new Course("JavaScript Framework", 32)
				);
		
//		for(Course c : courses)
//			out.println(c);
		
//		courses.stream()
//			.forEach( c -> out.println(c) );
//		courses.stream()
//			.forEach( out::println );
		
//		for(Course c : courses)
//			if (c.length() >=30)
//				out.println(c);
//		
//		courses.stream()
//			.filter( c-> c.length() >=30 )
//			.forEach( out::println );
		
//		List<Course> result = new ArrayList<>();
//		for(Course c : courses)
//			if (c.length() >=30)
//				result.add(c);
//		
//		Collections.sort(result, (c1,c2)->c1.title().compareTo(c2.title()) );
//		
//		List<String> titles = new ArrayList<String>();
//		
//		for(Course c : result)
//			titles.add(c.title());
//		
//		for(String title : titles)
//			out.println(title);
		
//		courses.stream()
//			.parallel()
		courses.parallelStream()
			.filter( c -> c.length() >=30 )
			.sequential()
			.sorted( (c1,c2)->c1.title().compareTo(c2.title()) )
			.map( c -> c.title() )
			.forEach( out::println );		
		
		List<String> result = courses.stream()
			.filter( c -> c.length() >=30 )
			.sorted( (c1,c2)->c1.title().compareTo(c2.title()) )
			.map( c -> c.title() )
			.collect(Collectors.toList());
			
		out.println(result);
		
		double avg_length = courses.stream()
			.filter( c-> c.title().contains("Java") )
			.mapToInt( c -> c.length())
			.average().getAsDouble();
		
		out.println(avg_length);

	}

}
