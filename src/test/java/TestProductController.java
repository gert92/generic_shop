import controller.ProductController;
import dto.Customer;
import dto.Product;
import dto.Sale;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.ProductRepository;
import repository.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestProductController {
    @Mock private SessionFactory mockSessionFactory = SessionManager.getSessionFactory();
    @InjectMocks private ProductRepository productRepository;
    @Mock Session session;

    @BeforeEach
    public void setUp(){
        Mockito.when(mockSessionFactory.openSession()).thenReturn(session);
    }

    @Test
    public void testRetrievingAllProducts(){
//        List<Product> products = new ArrayList<>();
//        Mockito.when(productController.findAllProducts()).thenReturn(products);
//        System.out.println(products);
//        Mockito.verify(productController,Mockito.times(1));
    }

}
