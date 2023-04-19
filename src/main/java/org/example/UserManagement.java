package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class UserManagement {

    private static final String url = "jdbc:mysql://localhost:3306/supermarket_java";
    private static final String username = "root";
    private static final String password = "Spiegoshana";
    private static final String registerUserQuery = "INSERT INTO users (userName, email, userPassword) VALUES (?, ?, ?)";



    public static void registerCustomer()  {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name");
        String userName = scanner.nextLine();

        System.out.println("Enter your email");
        String email = scanner.nextLine();

        System.out.println("Enter your password");
        String userPassword = scanner.nextLine();

        String hashedPassword = hashPassword(userPassword);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(registerUserQuery)) {

            statement.setString(1, userName);
            statement.setString(2, email);
            statement.setString(3, hashedPassword);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Sorry, something went wrong..");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean loginUser(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();


        String sql = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("Login successful!");

        } else {
            System.out.println("Invalid username or password.");

        }

        rs.close();
        stmt.close();
        return false;
    }

    public void viewItem(Connection conn) throws SQLException {

        String sql = "SELECT * FROM productList";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String productName = rs.getString("productName");
            int amountInStock = rs.getInt("amountInStock");
            double  productPrice = rs.getDouble("productPrice");

            System.out.println("ID: "+ id+ ", Name: " + productName + ", Quantity: " + amountInStock+", Price: "
                    +  productPrice);
        }

        rs.close();
        stmt.close();
    }


}
