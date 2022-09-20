package controller;

import dto.Customer;
import dto.Product;
import dto.Sale;
import repository.CustomerRepository;
import repository.ProductRepository;
import repository.SaleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void displaySalesByCustomer(Customer customer){
        List<Sale> sales = repository.findSalesByCustomerId(customer);
        if (sales.size() > 0){
            System.out.println(sales.get(0).getCustomer().getName() + " Bought these items: ");
            Map<Product, Integer> map = new HashMap<>();
            sales.forEach(sale -> sale.getProduct()
                    .forEach(product -> {
                if (map.containsKey(product)){
                    map.put(product, map.get(product) + 1);
                } else {
                    map.put(product, 1);
                }
            }));
            map.forEach((product, integer) -> System.out.println(product.getProductName() + " - " + product.getPrice() + "$" + " Amount: " + integer));
    }
    }
}
