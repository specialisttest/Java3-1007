package ru.specialist.model;

public class Person {
	private String name;
	private int age;
	
	
	// name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// age
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	// result
	public String getResult()
	{
		return this.toString();
	}
	
	@Override
	public String toString() {
		return String.format("%s - %d", name, age);
	}

}
