package mockitoTests;

import dto.Customer;
import dto.Product;
import dto.Sale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.CustomerRepository;
import repository.ProductRepository;
import repository.SaleRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class SaleControllerTest {

    @Mock
    private static SaleRepository saleRepository;
    @Mock
    private static ProductRepository productRepository;
    @Mock
    private static CustomerRepository customerRepository;
    private AutoCloseable autoCloseable;
    private SaleController underTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SaleController(saleRepository, customerRepository, productRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Order(1)
    void testAddSale() {
//        Customer customer1 = new Customer(51L, "Name", 3000F);
//        Product product1 = new Product(67L, "Bag of dead flies", 100L, 9.99);
//        List<Product> list1 = new ArrayList<>((Collection) product1);
//        Sale sale1 = new Sale(30L, customer1, list1);
//        saleRepository.createSale(sale1);
//
//        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
//
//        verify(saleRepository).createSale(productArgumentCaptor.capture());
//
//        Product capturedProduct =  productArgumentCaptor.getValue();
//
//        assertThat(capturedProduct).isEqualTo(product1);
    }

    @Test
    @Order(2)
    void testDisplayAllSales() {
        underTest.displayAllSales();
        verify(saleRepository).findAllSales();
    }

    @Test
    @Order(3)
    void testDisplaySalesByCustomer() {
//        Customer customer1 = new Customer(51L, "Name", 3000F);
//        Product product1 = new Product(67L, "Bag of dead flies", 100L, 9.99);
//        List<Product> list1 = new ArrayList<>((Collection) product1);
//        Sale sale1 = new Sale(30L, customer1, list1);
//        underTest.displaySalesByCustomer(customer1);
//        verify(saleRepository).findSalesByCustomerId(customer1);
    }

    @Test
    @Order(4)
    void testDisplayProductsByQuantity() {
//        underTest.displayProductsByQuantity();
//        verify(saleRepository).findAllSales();

    }
}