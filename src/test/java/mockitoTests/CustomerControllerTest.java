package mockitoTests;

import org.junit.jupiter.api.Test;
import dto.Customer;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.CustomerRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Mock
    private static CustomerRepository customerRepository;
    private AutoCloseable autoCloseable;
    private CustomerController underTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerController(customerRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Order(1)
    void testAddCustomer() {
            Customer customer1 = new Customer(15L, "Someone",200F);
            customerRepository.createCustomer(customer1);

            ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

            verify(customerRepository).createCustomer(customerArgumentCaptor.capture());

            Customer capturedCustomer =  customerArgumentCaptor.getValue();

            assertThat(capturedCustomer).isEqualTo(customer1);
    }

    @Test
    @Order(2)
    void testFindAllCustomers() {
        underTest.displayAllCustomers();
        verify(customerRepository).showAllCustomer();
    }

    @Test
    @Order(3)
    void testFindCustomerById() {
        Customer customer1 = new Customer(16L, "Someone",200F);
        underTest.findCustomerById(16L);
        verify(customerRepository).searchCustomerById(16L);
    }

    @Test
    @Order(4)
    void testUpdateCustomer() {
        Customer customer1 = new Customer(17L, "Someone",200F);
        customer1.setCustomerName("Newone");
        customer1.setBalance(159F);
        underTest.updateFields(customer1);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer =  customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer1);
    }

    @Test
    @Order(5)
    void testRemoveCustomer() {
        Customer customer1 = new Customer(18L, "Someone",200F);
        customerRepository.removeCustomer(customer1);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).removeCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer =  customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer1);
    }
}