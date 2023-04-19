package org.example;

import database.ConnectionManager;

import java.sql.*;
import java.util.Scanner;

public class SupermarketManagement {

    private static final String url = "jdbc:mysql://localhost:3306/supermarket_java";
    private static final String username = "root";
    private static final String password = "Spiegoshana";

    private ConnectionManager connectionManager;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;



    public void registerItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter item name");
        String itemName = scanner.nextLine();

        System.out.println("Please enter amount available");
        String itemAmount = scanner.nextLine();

        System.out.println("Please enter item price for the customers");
        String itemPrice = scanner.nextLine();

        System.out.println("Please enter item cost to the supermarket");
        String itemCost = scanner.nextLine();

        String registerItem = "INSERT INTO productDatabase (productName, amountInStock, productPrice, productCost ) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(registerItem)) {

            statement.setString(1, itemName);
            statement.setString(2, itemAmount);
            statement.setString(3, itemPrice);
            statement.setString(4, itemCost);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Item registered successfully!");
            } else {
                System.out.println("Sorry, something went wrong..");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void searchItem() {

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("enter the name of the item you want to find");
            String itemName = scanner.nextLine().toLowerCase().trim();

            String query = "SELECT * FROM items WHERE item_name=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, itemName);

            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("item_name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                System.out.println(name + "\t" + description + "\t" + price + "\t" + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewItems(Connection conn) throws SQLException {

        String sql = "SELECT * FROM productDatabase";

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String productName = resultSet.getString("productName");
            int amountInStock = resultSet.getInt("amountInStock");
            double  productPrice = resultSet.getDouble("productPrice");
            double  productCost = resultSet.getDouble("productCost");
            int  totalProductSold = resultSet.getInt(" totalProductSold");

            System.out.println("ID: "+ id+ ", Name: " + productName + ", Quantity: " + amountInStock+", Price: "
                    +  productPrice+ "Cost: "+productCost+ "amount sold: "+ totalProductSold);
        }

        resultSet.close();
        statement.close();
    }

    public void buyItem(String productName, Integer quantity) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM productList WHERE name = ?");
            stmt.setString(1, productName);

            if (resultSet.next()) {
                int currentQuantity = resultSet.getInt("amountInStock");
                if (currentQuantity >= quantity) {
                    PreparedStatement updateStmt = conn.prepareStatement("UPDATE productList SET amountInStock = ?" +
                            " WHERE productName = ?");
                    updateStmt.setInt(1, currentQuantity - quantity);
                    updateStmt.setString(2, productName);
                    updateStmt.executeUpdate();
                    System.out.println("Successfully bought " + quantity + " " + productName);
                } else {
                    System.out.println("Not enough " + productName + " in stock.");
                }
            } else {
                System.out.println("Item not found.");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
