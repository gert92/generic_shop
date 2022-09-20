package controller;

import javax.swing.*;

public class MenuController {

    CustomerController customerController = new CustomerController();
    SaleController saleController = new SaleController();
    ProductController productController = new ProductController();
    ShoppingCartController shoppingCartController  = new ShoppingCartController();
    private final String startMenuOptions = """
            0. Exit\s
            1. Shopping\s
            2. Service Menu\s
            """;

    private final String serviceMenuOptions = """
            0. Back\s
            1. Products\s
            2. Customers\s
            3. Sales
            """;

    private final String productsMenuOptions = """
            0. Back\s
            1. Add product\s
            2. View products\s
            3. Update product\s
            4. Delete product\s
            """;

    private final String customerMenuOptions = """
            0. Back\s
            1. Add customer\s
            2. View customers\s
            3. Update customer\s
            4. Delete customer\s
            """;

    private final String saleMenuOptions = """
            0. Back\s
            1. View all sales\s
            2. View sales by customer\s
            """;

    private final String shoppingMenuOptions = """
            0. Back\s
            1. New customer?\s
            2. Existing customer?\s
            """;

    public void startMenu(){
        switch (getUserInput(startMenuOptions)){
            case 0 -> System.exit(0);
            case 1 -> shoppingMenu();
            case 2 -> serviceMenu();
        }
    }

    public void shoppingMenu(){
        switch (getUserInput(shoppingMenuOptions)){
            case 0 -> startMenu();
            case 1 -> customerController.addCustomer();
            case 2 -> shoppingCartController.addToShoppingCart(customerController.chooseCustomer());
        }
    }

    public void serviceMenu(){
        switch (getUserInput(serviceMenuOptions)){
            case 0 -> startMenu();
            case 1 -> productsMenu();
            case 2 -> customersMenu();
            case 3 -> salesMenu();
        }
    }

    private void salesMenu() {
        switch (getUserInput(saleMenuOptions)){
            case 0 -> serviceMenu();
            case 1 -> System.out.println("not yet implemented");
            case 2 -> {
                customerController.displayAllCustomers();
                saleController.displaySalesByCustomer(customerController.findCustomerById(getUserInput("Enter user id.").longValue()));
            }
        }
    }

    private void customersMenu() {
        switch (getUserInput(customerMenuOptions)){
            case 0 -> serviceMenu();
            case 1 -> customerController.addCustomer();
            case 2 -> customerController.displayAllCustomers();
            case 3 -> System.out.println("update customer not implemented.");
            case 4 -> System.out.println("remove customer not implemented.");
        }
    }

    private void productsMenu() {
        switch (getUserInput(productsMenuOptions)){
            case 0 -> serviceMenu();
            case 1 -> productController.addProduct();
            case 2 -> productController.displayAllProducts();
            case 3 -> System.out.println("Update product not implemented");
            case 4 -> productController.deleteProduct(productController.findProduct(getUserInput("Enter the id of a product you want to delete").longValue()));
        }
    }


    private Integer getUserInput(String message) {
        return Integer.parseInt(JOptionPane.showInputDialog(message));
    }
}
