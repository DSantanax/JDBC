package com.mycompany.cs3560jdbc;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Filters.eq;

import java.awt.*;
import javax.swing.*;
import org.bson.Document;

public class Employee {

    private final JFrame jf = new JFrame("Employee");
    boolean isAdmin = false;
    boolean visible = true;
    private final int empID;
    boolean[] timeSlot = new boolean[24]; //24 for the ours in the day
    String password;
    private MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://dbUser:dbUser@cs3560-cgbwz.mongodb.net/test?retryWrites=true&w=majority");
    private final MongoClient mongoClient = new MongoClient(uri);
    private final MongoDatabase database = mongoClient.getDatabase("EmployeeID");
    MongoCollection<Document> collection = database.getCollection("employeeID");

    public Employee(int empID) {
        this.empID = empID;
        jf.setSize(400, 400);
        jf.setLayout(new FlowLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.getContentPane().setBackground(Color.WHITE);

        uri = new MongoClientURI(
                "mongodb+srv://dbUser:dbUser@cs3560-cgbwz.mongodb.net/test?retryWrites=true&w=majority");
        
        JButton jbtPass = new JButton("Change Password");
        jbtPass.addActionListener((ae)->{
            String newPass = JOptionPane.showInputDialog(jf, "Enter new password:", "Password", JOptionPane.PLAIN_MESSAGE);
            changePass(newPass);
        });
        
        JPanel jplRad= new JPanel();
        jplRad.setLayout(new FlowLayout());
        JRadioButton jrbT = new JRadioButton("Visible");
        JRadioButton jrbF = new JRadioButton("Offline");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbT);
        bg.add(jrbF);
        jplRad.add(jrbT);
        jplRad.add(jrbF);
        
        
        jrbT.addActionListener((ae)->{
            updateVisible(true);
        });
        jrbF.addActionListener((ae)-> {updateVisible(false);});
        
        jf.add(jbtPass);
        jf.add(jplRad);
        
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
    //Method for keeeping track of the time slots of each employee
    public void addTimeSlot(int startTime, int endTime) {
        // for cases where they start at 23 and end at 1 in the morning
        if (startTime > 24 || endTime > 24 || startTime < 0 || endTime < 0) {
            System.out.println("Error not a valid Time Slot");
        } else if (endTime < startTime) {
            int i = 0;
            while (endTime < 24) {
                timeSlot[endTime] = true;
                endTime++;
            }
            while (i < startTime) {
                timeSlot[i] = true;
                i++;
            }
        } else {
            while (startTime < endTime) {
                timeSlot[startTime] = true;
                startTime++;
            }
        }
    }
    
    private void changePass(String pass){
      collection.updateOne(eq("empID", getID()), set("empPass", pass));
     
    }
    
    private void updateVisible(boolean bool){
        this.visible = bool;  
        collection.updateOne(eq("empID", getID()), set("visi", bool));
    }
    public int getID(){
        return empID;
    }
}
