package controller;

import dto.Customer;
import dto.Product;

import javax.swing.*;

public class MenuController {

    CustomerController customerController = new CustomerController();
    SaleController saleController = new SaleController();
    ProductController productController = new ProductController();
    ShoppingCartController shoppingCartController = new ShoppingCartController();

    public void startMenu() {
        String startMenuOptions = """
                0. Exit\s
                1. Shopping\s
                2. Service Menu\s
                """;
        switch (getUserInput(startMenuOptions)) {
            case 0 -> System.exit(0);
            case 1 -> shoppingMenu();
            case 2 -> serviceMenu();
        }
    }

    public void shoppingMenu() {
        String shoppingMenuOptions = """
                0. Back\s
                1. New customer?\s
                2. Existing customer?\s
                """;
        switch (getUserInput(shoppingMenuOptions)) {
            case 0 -> startMenu();
            case 1 -> customerController.addCustomer();
            case 2 -> shoppingCartController.addToShoppingCart(customerController.chooseCustomer());
        }
    }

    public void serviceMenu() {
        String serviceMenuOptions = """
                0. Back\s
                1. Products\s
                2. Customers\s
                3. Sales
                """;
        switch (getUserInput(serviceMenuOptions)) {
            case 0 -> startMenu();
            case 1 -> productsMenu();
            case 2 -> customersMenu();
            case 3 -> salesMenu();
        }
    }

    private void salesMenu() {
        String saleMenuOptions = """
                0. Back\s
                1. View all sales\s
                2. View sales by customer\s
                """;
        switch (getUserInput(saleMenuOptions)) {
            case 0 -> serviceMenu();
            case 1 -> {
                saleController.displayAllSales();
                salesMenu();
            }
            case 2 -> {
                saleController.displaySalesByCustomer(customerController.findCustomerById(customerController.chooseCustomer().getId()));
                salesMenu();
            }
        }
    }

    private void customersMenu() {
        Customer customer;
        String customerMenuOptions = """
                0. Back\s
                1. Add customer\s
                2. View customers\s
                3. Update/Remove customer
                """;
        switch (getUserInput(customerMenuOptions)) {
            case 0 -> serviceMenu();
            case 1 -> {
                customerController.addCustomer();
                customersMenu();
            }
            case 2 -> {
                customerController.displayAllCustomers();
                customersMenu();
            }
            case 3 -> {
                customer = customerController.chooseCustomer();
                updateCustomerMenu(customer);
            }
        }
    }

    private void updateCustomerMenu(Customer customer) {
        String updateCustomerOptions = """
                0. Back\s
                1. Update name\s
                2. Update balance\s
                3. SAVE CHANGES\s
                9. Remove customer
                """;
        switch (getUserInput(updateCustomerOptions)) {
            case 0 -> customersMenu();
            case 1 -> {
                customer.setCustomerName(JOptionPane.showInputDialog("Enter new customer name."));
                updateCustomerMenu(customer);
            }
            case 2 -> {
                customer.setBalance(Float.parseFloat(JOptionPane.showInputDialog("Enter the new balance of customer")));
                updateCustomerMenu(customer);
            }
            case 3 -> customerController.updateFields(customer);
            case 9 -> customerController.removeCustomer(customer);

        }
    }

    private void productsMenu() {
        Product product;
        String productsMenuOptions = """
                0. Back\s
                1. Add product\s
                2. View products\s
                3. Update/Remove product
                """;
        switch (getUserInput(productsMenuOptions)) {
            case 0 -> serviceMenu();
            case 1 -> {
                productController.addProduct();
                productsMenu();
            }
            case 2 -> {
                productController.displayAllProducts();
                productsMenu();
            }
            case 3 -> {
                product = productController.chooseProduct();
                updateProductMenu(product);
            }
        }
    }

    private void updateProductMenu(Product product) {
        String updateProductOptions = """
                0. Back\s
                1. Update product name\s
                2. Update quantity\s
                3. Update price\s
                4. SAVE CHANGES\s
                9. Remove product
                """;
        switch (getUserInput(updateProductOptions)) {
            case 0 -> productsMenu();
            case 1 -> {
                product.setProductName(JOptionPane.showInputDialog("Enter new product name."));
                updateProductMenu(product);
            }
            case 2 -> {
                product.setQuantity(getUserInput("Enter new quantity").longValue());
                updateProductMenu(product);
            }
            case 3 -> {
                product.setPrice(getUserInput("Enter new price for product").doubleValue());
                updateProductMenu(product);
            }
            case 4 -> productController.updateProduct(product);
            case 9 -> productController.deleteProduct(product);
        }
    }


    private Integer getUserInput(String message) {
        return Integer.parseInt(JOptionPane.showInputDialog(message));
    }
}
