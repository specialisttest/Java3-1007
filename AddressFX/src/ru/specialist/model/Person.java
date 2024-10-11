package ru.specialist.model;

import java.time.LocalDate;
import javafx.beans.property.*;

public class Person {
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty<LocalDate> birthday;
	
	
	public Person()
	{
		this(null, null, null);
	}
	public Person(String firstName, String lastName, LocalDate birthday) {
		super();
		this.firstName = new SimpleStringProperty(firstName); // StringProperty Property<String>
		this.lastName = new SimpleStringProperty(lastName);
		this.street = new SimpleStringProperty("Воронцовская");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("Москва");
		this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
		
	}
	

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }	
	
	
	

}
