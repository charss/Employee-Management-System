// Employee Management System
// operators , control structures and arrays , object oriented programming , encapsulation and inheritance , polymorphism and abstraction , exception , collections, input and output stream , swing api , threads

import javax.swing.*;   
import java.util.*;

public class sample {
    JFrame f;  
    sample() {
        JFrame f = new JFrame();//creating instance of JFrame  
        JButton b = new JButton("click");//creating instance of JButton  
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height  

        f.add(b); //adding button in JFrame  

        f.setSize(400,500); //400 width and 500 height  
        f.setLayout(null); //using no layout managers  
        f.setVisible(true); //making the frame visible
    }
      
    
    
    public static void main(String[] args) {
        new sample();
        System.out.println("Hello World!");
    }
}
