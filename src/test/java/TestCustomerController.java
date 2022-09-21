import dto.Customer;
import dto.Product;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.CustomerRepository;
import repository.SessionManager;

import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TestCustomerController {
    private static SessionFactory sessionFactory;
    private static CustomerRepository customerRepository;
    private static Customer customer;
    private Session session;

    @BeforeAll
    public static void setUp(){
        sessionFactory = SessionManager.getSessionFactory();
        customerRepository = new CustomerRepository();
        customer = Customer.builder().customerName("Gert").balance(2000F).build();

        System.out.println("Session Factory created!");
    }

    @AfterAll
    public static void shutDown(){
        if (sessionFactory != null){
            sessionFactory.close();
            System.out.println("Session Factory closed!");
        }
    }

    @BeforeEach
    public void openSession(){
        session = sessionFactory.openSession();
        System.out.println("Session created!");
    }

    @AfterEach
    public void closeSession(){
        if (session != null){
            session.close();
            System.out.println("Session closed!");
        }
    }

    @Test
    @Order(1)
    public void testCreateCustomer(){
        customer = customerRepository.createCustomer(customer);
        org.assertj.core.api.Assertions.assertThat(customer.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testGetSingleCustomer(){
        Customer dataCustomer = customerRepository.findCustomerById(customer.getId());
        org.assertj.core.api.Assertions.assertThat(dataCustomer.getCustomerName()).isEqualTo(dataCustomer.getCustomerName());
        org.assertj.core.api.Assertions.assertThat(dataCustomer.getBalance()).isEqualTo(customer.getBalance());
        org.assertj.core.api.Assertions.assertThat(dataCustomer.getId()).isEqualTo(customer.getId());
    }

    @Test
    @Order(3)
    public void testUpdateASingleCustomer(){
        customer.setCustomerName("TestingUpdate");
        customer.setBalance(8000F);
        Customer updatedCustomer = customerRepository.updateCustomer(customer);
        org.assertj.core.api.Assertions.assertThat(updatedCustomer.getCustomerName()).isEqualTo(customer.getCustomerName());
        org.assertj.core.api.Assertions.assertThat(updatedCustomer.getBalance()).isEqualTo(customer.getBalance());
    }

    @Test
    @Order(4)
    public void testShowingAllCustomers(){
        List<Customer> customers = customerRepository.showAllCustomer();
        org.assertj.core.api.Assertions.assertThat(customers).isNotEmpty();
    }

    @Test
    @Order(5)
    public void testRemovingACustomer(){
        Customer findCustomer = customerRepository.findCustomerById(customer.getId());
        org.assertj.core.api.Assertions.assertThat(findCustomer).isNotNull();
        customerRepository.removeCustomer(findCustomer);
        Customer removedCustomer = customerRepository.findCustomerById(customer.getId());
        Assertions.assertThat(removedCustomer).isNull();
    }
}
