package ru.specialist;

import ru.specialist.model.Bill;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.Bindings;

public class Program {

	public static void main(String[] args) {
	    /*  
		{
			System.out.println("Using a ChangeListener");
			Bill electricBill = new Bill();
		      
		      electricBill.amountDueProperty().addListener(
		    		(o,oldVal, newVal)->
		    			System.out.printf("Electric bill has changed! Old: %f New: %f\n ",
		    					oldVal,newVal)
		      );
		     
		    electricBill.setAmountDue(100.00);
		}
		
		{

		    System.out.println("High-Level Binding API");
		     
	        IntegerProperty num1 = new SimpleIntegerProperty(1);
	        IntegerProperty num2 = new SimpleIntegerProperty(2);
	        
	        NumberBinding sum = num1.add(num2); // 
	        
	        
	        System.out.println(sum.isValid()); //false
	        
	        
	        System.out.println(sum.getValue());// sum = num1 + num2
	        System.out.println(sum.isValid()); //true
	        System.out.println(sum.getValue());
	        num1.set(2); // isValid = false
	        num2.set(3); // isValid = false
	        System.out.println(sum.isValid()); //false
	        System.out.println(sum.getValue()); // sum = num1 + num2
	        System.out.println(sum.isValid()); //true
	        System.out.println(sum.getValue());
	        
	        
		}
				
		
		{
			System.out.println("Using the Bindings Class");
	        IntegerProperty num1 = new SimpleIntegerProperty(1);
	        IntegerProperty num2 = new SimpleIntegerProperty(2);
	        NumberBinding sum = Bindings.add(num1,num2);
	        System.out.println(sum.getValue());
	        num1.setValue(2);
	        System.out.println(sum.getValue());		
		}
		
		
		

		{
			System.out.println("Combining Both Approaches");
 		    IntegerProperty num1 = new SimpleIntegerProperty(1);
		    IntegerProperty num2 = new SimpleIntegerProperty(2);
		    IntegerProperty num3 = new SimpleIntegerProperty(3);
		    IntegerProperty num4 = new SimpleIntegerProperty(4);
		    
		    // total = num1*num2+num3*num4
		    NumberBinding total =
		    		Bindings.add(num1.multiply(num2),num3.multiply(num4));
		    System.out.println(total.getValue()); // 14
		    num1.setValue(2);
		    System.out.println(total.getValue()); // 16			
			
		}
		*/
		
		{
			System.out.println("Using the Low-Level API");
	        final DoubleProperty a = new SimpleDoubleProperty(1);
	        final DoubleProperty b = new SimpleDoubleProperty(2);
	        final DoubleProperty c = new SimpleDoubleProperty(3);
	        final DoubleProperty d = new SimpleDoubleProperty(4);
	 
	        // db = sqrt(a*b+c*d)
	        DoubleBinding db = new DoubleBinding() {
	 
	            {
	                super.bind(a, b, c, d);
	            }
	 
	            @Override
	            protected double computeValue() {
	            	System.out.println("compute value");
	                return Math.sqrt(a.get() * b.get()) + (c.get() * d.get());
	            }
	        };
	 
	        System.out.println(db.get()); // compute value
	        System.out.println(db.get()); //
	        b.set(3); // isValid = false
	        System.out.println(db.get()); // compute value			
		}
    }

}
