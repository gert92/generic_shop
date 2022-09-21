import dto.Sale;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.SaleRepository;
import repository.SessionManager;

import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TestSaleController {

    private static SessionFactory sessionFactory;
    private static SaleRepository saleRepository;
    private static Sale sale;
    private Session session;

    @BeforeAll
    public static void setUp() {
        sessionFactory = SessionManager.getSessionFactory();
        saleRepository = new SaleRepository();
        sale = new Sale();

        System.out.println("Session Factory created!");
    }

    @AfterAll
    public static void shutDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("Session Factory closed!");
        }
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created!");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) {
            session.close();
            System.out.println("Session closed!");
        }
    }

    @Test
    @Order(1)
    public void testCreateSale() {
        sale = saleRepository.createSale(sale); // Problem \\
        Assertions.assertThat(sale.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testShowingAllSales() {
        List<Sale> sales = saleRepository.findAllSales();
        Assertions.assertThat(sales).isNotEmpty();
    }

    @Test
    @Order(3)
    public void testGetSingleSaleById() {
        Sale dataSale = saleRepository.findSaleById(sale.getId());
        Assertions.assertThat(dataSale.getId()).isEqualTo(sale.getId());
    }


    @Test
    @Order(4)
    public void testRemovingASale() {
        Sale findSale = saleRepository.findSaleById(sale.getId());
        Assertions.assertThat(findSale).isNotNull();
        saleRepository.deleteSale(findSale);
        Sale deletedSale = saleRepository.findSaleById(sale.getId());
        Assertions.assertThat(deletedSale).isNull();
    }
}
