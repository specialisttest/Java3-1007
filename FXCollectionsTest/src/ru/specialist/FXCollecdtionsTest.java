package ru.specialist;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.*;
import static java.lang.System.out;

class Course
{
    public String title;
    public int length;
    
    public Course(String title, int length)
    {
        this.title = title;
        this.length = length;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", title, length);
    }
    
    
}

public class FXCollecdtionsTest {

    public static void main(String[] args) {
        List<Course> courses = new ArrayList<Course>();
        
        //courses.add(new Course("Java 1", 40));

        
        //courses.remove(0);
        
        
        ObservableList<Course> p2 = 
                FXCollections.observableList(courses);
        p2.addListener((Observable o) -> {
            out.println("invalidated");
        });
        
        p2.add(new Course("Java 1", 40));
        
        out.print("\nObservable Courses : ");
        p2.stream().forEach(out::println);
        
        /*
        
        p2.add(new Course("Java 2", 40));        
        
        
        
        
        p2.addListener((ListChangeListener.Change<? extends Course> c) -> {
            out.println(c.getList());
        });
        
        p2.add(new Course("Java 3", 40));
        
        
        
        p2.get(0).length++;
        
       
        
        //out.print("Courses : ");
        courses.stream().forEach(out::println);
        */
       /* 
       // out.print("\nObservable Courses : ");
        p2.stream().forEach(out::print);
        */
    }
    
}
