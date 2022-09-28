package mockitoTests;

import dto.Customer;
import dto.Product;
import dto.Sale;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import repository.CustomerRepository;
import repository.ProductRepository;
import repository.SaleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class SaleController {
    SaleRepository repository = new SaleRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    ProductRepository productRepository = new ProductRepository();

    public void addSale(Customer customer, List<Product> products){
        Sale sale = Sale.builder().customer(customer).product(products).build();
        repository.createSale(sale);
        customerRepository.updateCustomer(customer);
        products.forEach(productRepository::updateProduct);
        System.out.println("Sale successful!");
    }

    public void displayAllSales(){
        List<Sale> sales = repository.findAllSales();
        sales.forEach(sale -> {
            System.out.println("Sale id: " + sale.getId());
            System.out.println("Customer Name: " + sale.getCustomer().getCustomerName());
            displayProductsByQuantity(sale);
            System.out.println();
        });
    }

    public void displaySalesByCustomer(Customer customer){
        List<Sale> sales = repository.findSalesByCustomerId(customer);
        System.out.println(sales.get(0).getCustomer().getCustomerName() + " Bought these items: ");
        sales.forEach(this::displayProductsByQuantity);
    }

    public void displayProductsByQuantity(Sale sale){
        Map<Product, Integer> map = new HashMap<>();
        sale.getProduct()
                .forEach(product -> {
                    if (map.containsKey(product)){
                        map.put(product, map.get(product) + 1);
                    } else {
                        map.put(product, 1);
                    }
                });
        System.out.println("---------------------");
        map.forEach((product, integer) -> System.out.println(product.getProductName() + " - " + product.getPrice() + "$" + " Amount: " + integer));
    }
}
