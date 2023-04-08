package com.example.trannsformations;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    private Ellipse ellipse;
    private Translate translate;
    private Rotate rotate;
    private Scale scale;
    private Shear shear;
    private Button translateButton,shearButton,scaleButton,rotateButton;
    private double x,y,angle,pivotx,pivoty;

    public void start(Stage stage) throws IOException {

//        ellipse1 = new Ellipse(200.0f, 120.0f, 150.0f, 80.f);
//        ellipse1.setFill(Color.RED);
//        ellipse1.setStroke(Color.BLACK);
        ellipse=new Ellipse(200.0f, 120.0f, 150.0f, 80.f);
        ellipse.setFill(Color.BLUE);
        ellipse.setStroke(Color.BLACK);

        rotate =new Rotate();
        scale=new Scale();
        shear=new Shear();
        translate=new Translate();

        translateButton = new Button("Translate");
        rotateButton = new Button("Rotate");
        scaleButton=new Button("Scale");
        shearButton = new Button("Shear");


        Transformations t=new Transformations();
        shearButton.setOnAction(event -> {
                TextInputDialog t1=new TextInputDialog();
                t1.setTitle("X input dialog");
                t1.setHeaderText("Enter X value: ");
                t1.setContentText("Enter x:");
                Optional<String> r1=t1.showAndWait();
                TextInputDialog t2=new TextInputDialog();
                t2.setTitle("Y input dialog");
                t2.setHeaderText("Enter Y value: ");
                t2.setContentText("Enter y:");
                Optional<String> r2=t2.showAndWait();
                if(r1.isPresent() && r2.isPresent()){
                    t.shear( Double.parseDouble(r1.get()),Double.parseDouble(r2.get()));
             }
        });
        translateButton.setOnAction(event -> {
            TextInputDialog t3= new TextInputDialog();
            t3.setTitle("X input dialog");
            t3.setHeaderText("Enter X value: ");
            t3.setContentText("Enter x:");
            Optional<String> r3=t3.showAndWait();
            TextInputDialog t4=new TextInputDialog();
            t4.setTitle("Y input dialog");
            t4.setHeaderText("Enter Y value: ");
            t4.setContentText("Enter y:");
            Optional<String> r4=t4.showAndWait();
            if(r3.isPresent() && r4.isPresent()){
                t.translate( Double.parseDouble(r3.get()),Double.parseDouble(r4.get()));
            }

        });
        scaleButton.setOnAction(event -> {
            TextInputDialog t5=new TextInputDialog();
            t5.setTitle("X input dialog");
            t5.setHeaderText("Enter X value: ");
            t5.setContentText("Enter x:");
            Optional<String> r5=t5.showAndWait();
            TextInputDialog t6=new TextInputDialog();
            t6.setTitle("Y input dialog");
            t6.setHeaderText("Enter Y value: ");
            t6.setContentText("Enter y:");
            Optional<String> r6=t6.showAndWait();
            if(r5.isPresent() && r6.isPresent()) {
                t.scale(Double.parseDouble(r5.get()), Double.parseDouble(r6.get()));
            }
        });

        rotateButton.setOnAction(event ->{
            TextInputDialog t7=new TextInputDialog();
            t7.setTitle("angle input dialog");
            t7.setHeaderText("Enter angle value: ");
            t7.setContentText("Enter angle:");
            Optional<String> r7=t7.showAndWait();
            TextInputDialog t8=new TextInputDialog();
            t8.setTitle("X input dialog");
            t8.setHeaderText("Enter X value: ");
            t8.setContentText("Enter x:");
            Optional<String> r8=t8.showAndWait();
            TextInputDialog t9=new TextInputDialog();
            t9.setTitle("Y input dialog");
            t9.setHeaderText("Enter Y value: ");
            t9.setContentText("Enter y:");
            Optional<String> r9=t9.showAndWait();
            if(r8.isPresent() && r8.isPresent() && r9.isPresent()) {
                t.rotate(Double.parseDouble(r7.get()),Double.parseDouble(r8.get()), Double.parseDouble(r9.get()));
            }
        });

        HBox buttonBar = new HBox(rotateButton, scaleButton, shearButton, translateButton);
        buttonBar.setAlignment(Pos.TOP_CENTER);
        buttonBar.setSpacing(10);


        StackPane root=new StackPane(ellipse);
        root.setPrefSize(300,200);
        StackPane layout = new StackPane(root, buttonBar);
        Scene scene = new Scene(layout, 800, 800);
        stage.setTitle("Transformations!!!");
        stage.setScene(scene);
        stage.show();
    }
    class Transformations{
        public void shear(double x,double y){
            shear.setX(shear.getX()+x);
            shear.setY(shear.getY()+y);
            ellipse.getTransforms().setAll(shear);
        }
        public void rotate(double angle,double pivotx,double pivoty){
                rotate.setAngle(rotate.getAngle()+angle);
                rotate.setPivotX(rotate.getPivotX()+pivotx);
                rotate.setPivotY(rotate.getPivotY()+pivoty);
                ellipse.getTransforms().setAll(rotate);
        }
        public void translate(double x,double y){
                translate.setX(translate.getX() + x);
                translate.setY(translate.getY() + y);
                ellipse.getTransforms().setAll(translate);
        }
        public void scale(double x,double y){
                scale.setX(scale.getX() * x);
                scale.setY(scale.getY() * y);
                ellipse.getTransforms().setAll(scale);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}