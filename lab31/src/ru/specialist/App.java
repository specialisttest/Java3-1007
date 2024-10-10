package ru.specialist;

import static java.lang.System.out;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

record Book(String title, int price) {}

public class App {

	public static void main(String[] args) {
		var books = List.of(
				new Book("Шерлок Холмс", 100),
				new Book("Унесенные ветром", 300),
				new Book("Война и мир", 150),
				new Book("На дне", 200));

		
		OptionalInt minPrice = books.stream().mapToInt( b->b.price()).min();
		OptionalInt maxPrice = books.stream().mapToInt( b->b.price()).max();
		OptionalDouble avgPrice = books.stream().mapToInt( b->b.price()).average();
		
		if (minPrice.isPresent()) 
			out.printf("Минимальная цена книги: %d\n", minPrice.getAsInt());
		
		maxPrice.ifPresent( price -> out.printf("Максимальная цена книги: %d\n", price) );
		
		avgPrice.ifPresentOrElse(
				price -> out.printf("Средняя цена книги: %.2f\n", price),
				()->out.println("Нет цен"));
		
		
		

		books.stream()
			.mapToInt( b->b.price())
			.min()
			.ifPresentOrElse(
					 minP -> out.printf("Минимальная цена книги: %d\n%s\n", minP,
							 books.stream()
							 	.filter(b ->b.price() == minP)
							 	.map(b->b.title())
							 	.collect(Collectors.toList())
							 ),
					()->out.println("Нет цен"));
		
		books.stream()
			.mapToInt( b->b.price())
			.max()
			.ifPresentOrElse(
					 maxP -> out.printf("Максимальная цена книги: %d\n%s\n", maxP,
							 books.stream()
							 	.filter(b ->b.price() == maxP)
							 	.map(b->b.title())
							 	.collect(Collectors.toList())
							 ),
					()->out.println("Нет цен"));		
		
	}

}
