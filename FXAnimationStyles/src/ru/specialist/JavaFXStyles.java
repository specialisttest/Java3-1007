
package ru.specialist;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class JavaFXStyles extends Application {
    
    ObservableList<PieChart.Data> data;
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        Button btn2 = new Button("Add");
        Button btn3 = new Button("Rotate");
        Label lbl = new Label("This is CSS App!");
        lbl.setId("text");
        btn.setText("Say CSS");
        
        data = FXCollections.observableArrayList(new PieChart.Data("1",10),
                new PieChart.Data("2", 30),new PieChart.Data("3", 60));
        PieChart pchart = new PieChart(data);
        
        
        FlowPane root = new FlowPane();
        root.getChildren().add(btn);
        root.getChildren().add(lbl);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        root.getChildren().add(pchart);
        
        
        btn.setOnAction((exent)->{lbl.setText("CSS is cool!");});
        btn2.setOnAction((event) -> {
            data.add(new PieChart.Data("4", 10));
        });
        btn3.setOnAction((event) -> {
            //pchart.setRotate(pchart.getRotate()+5);
            Animation anim = new Transition() {
                {
                    setCycleDuration(Duration.seconds(10));
                }
                @Override
                protected void interpolate(double frac) {
                    pchart.setRotate(50*frac);
                    //System.out.println(frac);
                }
            };
            anim.play();
        });
        
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(JavaFXStyles.class.getResource("CascadeSS.css").toExternalForm());
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
