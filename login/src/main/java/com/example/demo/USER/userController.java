package com.example.demo.USER;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

@RestController
public class userController {
    public Statement getStmtConnection(){
        Statement stmt = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "manoj@01");
            stmt = con.createStatement();
            return stmt;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return stmt;
    }
    public PreparedStatement getPreparedStmtConnection(String sql){
        PreparedStatement prepStmt = null;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "manoj@01");
            prepStmt = con.prepareStatement(sql);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return prepStmt;
    }
    @PostMapping("/formData")
    public List Retreive(@RequestBody LoginForm loginForm){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NAME,EMAIL,PASSWORD FROM USERDETAILS");
            System.out.println("done done done !!!");
            while(rs.next()) {
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                System.out.println(email+"\n"+password);
                if(email.equals(loginForm.getEmail()) && password.equals(loginForm.getPassword())){
                    return List.of("You are allowed");
                }
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return List.of("You are not allowed");
    }
    @PostMapping("/addUser")
    public List addUser(@RequestBody LoginForm loginForm){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO userdetails" +
                    "  (name, email, password) VALUES " +
                    " (?, ?, ?);");
            stmt.setString(1,loginForm.getName());
            stmt.setString(2,loginForm.getPassword());
            stmt.setString(3, loginForm.getPassword());
            stmt.executeUpdate();
            return List.of("Data has been added successfully");
        }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of("Data is not added!!");
    }
}
