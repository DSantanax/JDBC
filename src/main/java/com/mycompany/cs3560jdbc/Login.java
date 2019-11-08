/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.cs3560jdbc;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Login {

    private final JFrame jf = new JFrame("Login Info");
    private final JTextField jtfUser;
    private final JPasswordField jtfPass;
    private final MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://dbUser:dbUser@cs3560-cgbwz.mongodb.net/test?retryWrites=true&w=majority");
    private final MongoClient mongoClient = new MongoClient(uri);
    private MongoDatabase database;
    private final JRadioButton jrbEmp;
    private final JRadioButton jrbAdmin;
    private JLabel jlab;

    public Login() {

        jf.setSize(450, 150);
        jf.setLayout(new FlowLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);

        jf.getContentPane().setBackground(Color.WHITE);

        jlab = new JLabel("");
        JLabel jUser = new JLabel("User ID: ");
        jUser.setDisplayedMnemonic(KeyEvent.VK_U);
        JLabel jPass = new JLabel("Password: ");
        jPass.setDisplayedMnemonic(KeyEvent.VK_P);

        jtfUser = new JTextField(10);

        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new FlowLayout());

        JButton jbtLogin = new JButton("Login");
        jbtLogin.setMnemonic(KeyEvent.VK_L);
        //jbtLogin.setActionCommand("cancel");

        //use setAction.. to change in actionListener and check in
        //setVisibile
        JButton jbtCancel = new JButton("Cancel");
        jbtCancel.setMnemonic(KeyEvent.VK_C);
        jpButtons.add(jbtLogin);
        jpButtons.add(jbtCancel);

        JPanel jplRb = new JPanel();
        jplRb.setLayout(new FlowLayout());

        jrbEmp = new JRadioButton("Employee:");
        jrbAdmin = new JRadioButton("Administrator");

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbEmp);
        bg.add(jrbAdmin);
        jrbEmp.setSelected(true);
        jplRb.add(jrbEmp);
        jplRb.add(jrbAdmin);

        jtfUser.addActionListener((ae) -> {
            jbtLogin.doClick();
        });

        jtfPass = new JPasswordField(10);

        jtfPass.addActionListener((ae) -> {
            jbtLogin.doClick();
        });

        jbtLogin.addActionListener((ae) -> {
            //check ID must be numeric else throw not!
            jlab.setText("");
            if (checkLogin()) {//code} use jrb to check
                jf.dispose();
                if (jrbAdmin.isSelected()) {
                    SwingUtilities.invokeLater(() -> {
                        Admin admin = new Admin();
                    });
                } else if (jrbEmp.isSelected()) {
                    SwingUtilities.invokeLater(() -> {
                        Employee emp = new Employee(Integer.parseInt(jtfUser.getText()));
                        
                    });
                }
            }
        });

        jbtCancel.addActionListener((ae) -> {
            System.exit(0);
        });

        jUser.setLabelFor(jtfUser);
        jPass.setLabelFor(jtfPass);

        jf.add(jUser);
        jf.add(jtfUser);
        jf.add(jPass);
        jf.add(jtfPass);

        jf.add(jplRb);

        jf.add(jpButtons);
        jf.getRootPane().setDefaultButton(jbtLogin);

        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login log = new Login();
            //code
        });
    }

    private boolean checkLogin() {
        boolean check = false;
        database = mongoClient.getDatabase("EmployeeID");

        if (jrbEmp.isSelected()) {
            char[] password = jtfPass.getPassword();

            String pass = "";
            for (int i = 0; i < password.length; i++) {
                pass += password[i];
            }
            
            MongoCollection<Document> collection = database.getCollection("employeeID");
            try {
                Document find = collection.find(and(eq("empID", Integer.valueOf(jtfUser.getText())),
                         eq("empPass", pass))).first();
                find.toJson();

                check = true;
            } catch (NullPointerException e) {
                jlab.setText("Invalid ID/Password!");
                jf.add(jlab);
                jf.setVisible(true);
                check = false;
            } catch (NumberFormatException ex) {
                jlab.setText("ID must be numeric!");
            }

        } else if (jrbAdmin.isSelected()) {

            MongoCollection<Document> collection = database.getCollection("adminID");

            try {
                char[] password = jtfPass.getPassword();

            String pass = "";
            for (int i = 0; i < password.length; i++) {
                pass += password[i];
            }
                Document find = collection.find(and(eq("adminID", Integer.valueOf(jtfUser.getText())),
                        eq("adminPass", pass))).first();
                find.toJson();

                check = true;

            } catch (NullPointerException ex) {
                jlab.setText("Invalid ID/Password!");
                jf.add(jlab);
                jf.setVisible(true);
                check = false;
            } catch (NumberFormatException ex) {
                jlab.setText("ID must be numeric!");
            }
        }

        return check;
    }
}
