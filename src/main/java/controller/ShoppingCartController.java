package controller;

import dto.Customer;
import dto.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartController {
    ProductController productController = new ProductController();
    SaleController saleController = new SaleController();

    List<Product> products = productController.findAllAvailableProducts();

    public void addToShoppingCart(Customer customer){
        List<Product> shoppingCart = new ArrayList<>();
        while (true){
            System.out.println("-----SHOPPING CART-----");
            shoppingCart.forEach(System.out::println);
            System.out.println();
            System.out.println("------AVAILABLE PRODUCTS-------");
            products.forEach(System.out::println);
            System.out.println();
            System.out.println("SELECT PRODUCTS FROM A LIST YOU WANT TO BUY");
            int option = Integer.parseInt(getUserInput("Enter the product id / enter 0 to make a purchase"));
            if (option == 0){
                saleController.addSale(customer, shoppingCart);
                System.out.println("Thank you for your purchase!");
                saleController.displaySalesByCustomer(customer);
                break;
            }
            Optional<Product> chosenOne = products.stream().filter(product -> product.getId() == option).findFirst();
            if (chosenOne.isPresent()){
                if (!isAvailable(chosenOne.get())){
                    System.out.println("Sorry, no " + chosenOne.get().getProductName() + " left in the store");
                    continue;
                }
                if (!canAfford(customer, chosenOne.get())){
                    System.out.println("Out of balance, Cannot buy it");
                    continue;
                }
                Product product = chosenOne.get();
                customer.setBalance((float) (customer.getBalance() - product.getPrice()));
                product.setQuantity(product.getQuantity() - 1);
                shoppingCart.add(product);
            }
        }
    }


    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    private boolean canAfford(Customer customer, Product product){
        return customer.getBalance() - product.getPrice() > 0;
    }

    private boolean isAvailable(Product product){
        return product.getQuantity() > 0;
    }


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
