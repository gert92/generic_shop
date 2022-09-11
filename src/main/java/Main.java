import controller.CustomerController;
import controller.ProductController;
import controller.SaleController;
import dto.Customer;
import dto.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        CustomerController customerController = new CustomerController();
        SaleController saleController = new SaleController();

//        List<Product> products = productController.findAllProducts();
        Customer customer = customerController.findCustomerById(1L);

//        List<Product> shoppingCart = new ArrayList<>();
//        while (true){
//            System.out.println("-----SHOPPING CART-----");
//            shoppingCart.forEach(System.out::println);
//            System.out.println();
//            System.out.println("------AVAILABLE PRODUCTS-------");
//            products.forEach(System.out::println);
//            System.out.println();
//            System.out.println("SELECT PRODUCTS FROM A LIST YOU WANT TO BUY");
//            int option = Integer.parseInt(JOptionPane.showInputDialog("Enter the product id / enter 0 to make a purchase"));
//            if (option == 0){
//                saleController.addSale(customer, shoppingCart);
//                System.out.println("Thank you for your purchase!");
//                break;
//            }
//            Optional<Product> chosenOne = products.stream().filter(product -> product.getId() == option).findFirst();
//            if (chosenOne.isPresent()){
//                Product product = chosenOne.get();
//                customer.setBalance((float) (customer.getBalance() - product.getPrice()));
//                product.setQuantity(product.getQuantity() - 1);
//                shoppingCart.add(product);
//            }
//        }

        saleController.findSalesByCustomer(customer).forEach(System.out::println);
    }
}