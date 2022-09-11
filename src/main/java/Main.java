import controller.CustomerController;
import controller.ProductController;
import controller.SaleController;
import controller.ShoppingCartController;
import dto.Customer;
import dto.Product;
import dto.Sale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        SaleController saleController = new SaleController();
        ShoppingCartController shoppingCartController = new ShoppingCartController();

        Customer customer = customerController.checkIfNewOrExistingCustomer();
//        shoppingCartController.addToShoppingCart(customer);

//        ProductController productController = new ProductController();

//        productController.addProduct();


        List<Sale> sales = saleController.findSalesByCustomer(customer);
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
//            sales
//                    .forEach(sale -> sale.getProduct()
//                            .forEach(product -> System.out.println(product.getProductName() + " - " + product.getPrice() + "$")));
        }
    }
}