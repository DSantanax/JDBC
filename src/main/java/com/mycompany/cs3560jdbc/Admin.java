package com.mycompany.cs3560jdbc;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

public class Admin {
    
    private final JFrame jf = new JFrame("Admin");
    private final MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://dbUser:dbUser@cs3560-cgbwz.mongodb.net/test?retryWrites=true&w=majority");
    private final MongoClient mongoClient = new MongoClient(uri);
    private final MongoDatabase database = mongoClient.getDatabase("EmployeeID");
    MongoCollection<Document> collection = database.getCollection("employeeID");
    
        Admin(){
        jf.setSize(400, 400);
        jf.setLayout(new FlowLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.getContentPane().setBackground(Color.WHITE);

          JButton jbtNewEmp = new JButton("Enter New Employee");
          
        jbtNewEmp.addActionListener((ae)->{
            passDialog();
        });
        jf.add(jbtNewEmp);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true); 
    }                         

    private void passDialog() {
        
        JDialog jdl = new JDialog(jf,"New Employee",true);
        
    // Variables declaration                     
    ButtonGroup buttonGroup1;
    JButton jButton1;
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JPasswordField jPasswordField1;
    JRadioButton jRadioButton1;
    JRadioButton jRadioButton2;
    JTextField jTextField1;
        
        buttonGroup1 = new ButtonGroup();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jButton1 = new JButton();
        jRadioButton1 = new JRadioButton();
        jRadioButton2 = new JRadioButton();
        jLabel3 = new JLabel();
        jPasswordField1 = new JPasswordField();

        jdl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        jLabel1.setLabelFor(jTextField1);
        jLabel1.setText("UserID:");

        jLabel2.setText("Password:");
        jTextField1.addActionListener((java.awt.event.ActionEvent evt) -> {
            
        });

        jButton1.setText("Enter New Employee");
        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            char[] password = jPasswordField1.getPassword();
            String pass = "";
            for (int i = 0; i < password.length; i++) {
                pass += password[i];
            }
            boolean visi = false;
            if(jRadioButton1.isSelected()){
                visi = true;
            }
            else if(jRadioButton2.isSelected()){
                visi = false;
            }
            
            Document employee = new Document("empID", jTextField1.getText())
                    .append("empPass", pass)
                    .append("visi", visi);
            collection.insertOne(employee);
            jdl.dispose();
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Visible");
        jRadioButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Hidden");

        jLabel3.setText("Visibility:");

        GroupLayout layout = new GroupLayout(jdl.getContentPane());
        jdl.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(109, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jRadioButton1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jRadioButton2)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jPasswordField1))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jButton1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jdl.pack();
        jdl.setVisible(true);
    }                    
}
