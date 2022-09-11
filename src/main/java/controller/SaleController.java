package controller;

import dto.Customer;
import dto.Product;
import dto.Sale;
import repository.CustomerRepository;
import repository.ProductRepository;
import repository.SaleRepository;

import javax.swing.*;
import java.util.List;

public class SaleController {
    SaleRepository repository = new SaleRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    ProductRepository productRepository = new ProductRepository();

    public void addSale(Customer customer, List<Product> products){
        Sale sale = Sale.builder().customer(customer).product(products).build();
        repository.createSale(sale);
        customerRepository.updateCustomer(customer);
        products.forEach(productRepository::updateProduct);
    }

    public List<Sale> findSalesByCustomer(Customer customer){
        return repository.findSalesByCustomerId(customer);
    }
}
