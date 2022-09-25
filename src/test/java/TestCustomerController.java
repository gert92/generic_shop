import controller.CustomerController;
import dto.Customer;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import repository.CustomerRepository;
import repository.SessionManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TestCustomerController {


    private static SessionFactory sessionFactory;
    @Mock
    private static CustomerRepository customerRepository;
    private AutoCloseable autoCloseable;
    private CustomerController underTest;
    private Session session;

//    @BeforeAll
//    public static void setUp() {
//        sessionFactory = SessionManager.getSessionFactory();
//        customerRepository = new CustomerRepository();
//        customer = Customer.builder().customerName("Gert").balance(2000F).build();
//
//        System.out.println("Session Factory created!");
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        if (sessionFactory != null) {
//            sessionFactory.close();
//            System.out.println("Session Factory closed!");
//        }
//    }

    @BeforeEach
    public void openSession() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerController(customerRepository);
//        session = sessionFactory.openSession();
//        System.out.println("Session created!");
    }

    @AfterEach
    public void closeSession() throws Exception {
        autoCloseable.close();
//        if (session != null) {
//            session.close();
//            System.out.println("Session closed!");
//        }
    }

    @Test
    @Order(1)
    public void testAddCustomer() {
//        underTest.addCustomer();
        Customer customer1 = new Customer(1L, "Someone",200F);
        customerRepository.createCustomer(customer1);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).createCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer =  customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer1);
//        customer = customerRepository.createCustomer(customer);
//        org.assertj.core.api.Assertions.assertThat(customer.getId()).isGreaterThan(0);
    }
//
//    @Test
//    @Order(2)
//    public void testGetSingleCustomerById() {
//        Customer dataCustomer = customerRepository.findCustomerById(customer.getId());
//        org.assertj.core.api.Assertions.assertThat(dataCustomer.getCustomerName()).isEqualTo(dataCustomer.getCustomerName());
//        org.assertj.core.api.Assertions.assertThat(dataCustomer.getBalance()).isEqualTo(customer.getBalance());
//        org.assertj.core.api.Assertions.assertThat(dataCustomer.getId()).isEqualTo(customer.getId());
//    }

    @Test
    @Order(2)
    public void testShowingAllCustomers() {
        underTest.displayAllCustomers();
        verify(customerRepository).showAllCustomer();
//        List<Customer> customers = customerRepository.showAllCustomer();
//        org.assertj.core.api.Assertions.assertThat(customers).isNotEmpty();
    }
//
    @Test
    @Order(3)
    public void testUpdateASingleCustomer() {
        Customer customer1 = new Customer(1L, "Someone",200F);
        customer1.setCustomerName("Newone");
        customer1.setBalance(159F);
        underTest.updateFields(customer1);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer =  customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer1);
//        customer.setCustomerName("TestingUpdate");
//        customer.setBalance(8000F);
//        Customer updatedCustomer = customerRepository.updateCustomer(customer);
//        org.assertj.core.api.Assertions.assertThat(updatedCustomer.getCustomerName()).isEqualTo(customer.getCustomerName());
//        org.assertj.core.api.Assertions.assertThat(updatedCustomer.getBalance()).isEqualTo(customer.getBalance());
    }
//
//    @Test
//    @Order(5)
//    public void testRemovingACustomer() {
//        Customer findCustomer = customerRepository.findCustomerById(customer.getId());
//        org.assertj.core.api.Assertions.assertThat(findCustomer).isNotNull();
//        customerRepository.removeCustomer(findCustomer);
//        Customer removedCustomer = customerRepository.findCustomerById(customer.getId());
//        Assertions.assertThat(removedCustomer).isNull();
//    }
}
