package ru.specialist.hello;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hello {
	
	private StringProperty userName = new SimpleStringProperty();

	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String userName) {
		this.userName.set(userName);
	}
	
	public StringProperty userNameProperty() {
		return userName;
	}
	
	public String getHello() {
		return helloProperty().get();
	}
	
	public StringBinding helloProperty() {
		return new StringBinding() {
			
			{
				super.bind(userName);
			}
			
			@Override
			protected String computeValue() {
				return (getUserName() == null || getUserName().isBlank()) ? "Привет!" :
					String.format("Привет, %s!", getUserName());
			}
		}; 
		
		
		
	}

}

/*public class Hello {
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = (userName != null) ? userName.trim() : userName;
	}
	
	
	public String getHello() {
		return (getUserName() == null || getUserName().isBlank()) ? "Привет!" :
			String.format("Привет, %s!", getUserName());
	}

}*/
