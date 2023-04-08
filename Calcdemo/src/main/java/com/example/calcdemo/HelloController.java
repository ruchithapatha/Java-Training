package com.example.calcdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class HelloController {
    @FXML
    private TextField tf;
    String op="";
    long n1;
    long n2;
    public void Number(ActionEvent ae){
        String no=((Button)ae.getSource()).getText();
        tf.setText(tf.getText()+no);
    }

    public void Operation(ActionEvent ae){
        String operation=((Button)ae.getSource()).getText();
        if(operation.equals("C"))
            tf.setText("");
        if(!operation.equals("=")) {
            if (!op.equals("")) {
                return;
            }
            op = operation;
            n1 = Long.parseLong(tf.getText());
            tf.setText("");
        }
        else{
            if(op.equals("")){
                return;
            }
            n2=Long.parseLong(tf.getText());
            calculate(n1,n2,op);

        }
    }
    public void calculate(long n1,long n2,String op){
        switch(op){
            case "+" : tf.setText(n1+n2+"");
                        break;
            case "-" : tf.setText(n1-n2+"");
                        break;
            case "*" : tf.setText(n1*n2+"");
                        break;
            case "/" :
                      if(n2==0){
                          tf.setText("0");
                          break;
                      }
                      tf.setText(n1/n2+"");
                      break;

        }
    }
}