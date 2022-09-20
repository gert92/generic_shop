package controller;

import dto.Product;
import repository.ProductRepository;

import javax.swing.*;
import java.util.List;

public class ProductController {

    ProductRepository repository = new ProductRepository();

    public List<Product> findAllProducts(){
        return repository.findAllProducts();
    }

    public void displayAllProducts(){
        List<Product> products = findAllProducts();
        products.forEach(System.out::println);
    }

    public List<Product> findAllAvailableProducts(){
        return repository.findAllAvailableProducts();
    }

    public void addProduct(){
        String productName = JOptionPane.showInputDialog("Enter the product name");
        String productQuantity = JOptionPane.showInputDialog("Enter the product quantity");
        String productPrice = JOptionPane.showInputDialog("Enter the product price, ex: 29.99");

        Product product = Product.builder()
                .productName(productName)
                .quantity(Long.valueOf(productQuantity))
                .price(Double.valueOf(productPrice))
                .build();

        repository.createProduct(product);

    }

    public Product findProduct(Long id){
        return repository.findProductById(id);
    }

    public void updateProduct(Product product){
        repository.updateProduct(product);
        System.out.println("Product Updated.");
    }

    public void deleteProduct(Product product){
        repository.deleteProduct(product);
    }

    public Product chooseProduct(){
        StringBuilder stringBuilder = new StringBuilder("Select the id of the product \n");
        List<Product> products = findAllProducts();
        products.forEach(m -> stringBuilder
                .append(m.getId())
                .append(" - ")
                .append(m.getProductName())
                .append(" - ")
                .append(m.getQuantity())
                .append(" - ")
                .append(m.getPrice())
                .append("$")
                .append("\n"));
        return findProduct(Long.parseLong(JOptionPane.showInputDialog(stringBuilder)));

    }

}
