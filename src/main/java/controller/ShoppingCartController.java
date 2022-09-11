package controller;

import dto.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartController {



    public void addProductToTheCart() {
        String existingCustomer = JOptionPane.showInputDialog("Are you a new customer? Yes or No");
        // create a list that contains record of the product in the shopping cart
        if (existingCustomer.toLowerCase() == "no") {
            CustomerController customerController = new CustomerController();
            customerController.addCustomer();
        } else if (existingCustomer.toLowerCase() == "yes") {
            String customerName = JOptionPane.showInputDialog("Enter your name: ");
        } else {
            System.out.println("Please choose Yes or No.");
        }


    }

    public void removeProductFromTheCart() {

    }

    public void buyProduct() {

    }

}
