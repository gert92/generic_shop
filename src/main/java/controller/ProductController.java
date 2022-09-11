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

    public void findProduct(Long id){
        repository.findProductById(id);

    }

}
