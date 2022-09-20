import dto.Product;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.ProductRepository;
import repository.SessionManager;

import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TestProductController {
    private static SessionFactory sessionFactory;
    private static ProductRepository productRepository;
    private static Product product;
    private Session session;

    @BeforeAll
    public static void setUp(){
        sessionFactory = SessionManager.getSessionFactory();
        productRepository = new ProductRepository();
        product = Product.builder().productName("Kana").price(5D).quantity(10L).build();

        System.out.println("SessionFactory created!");
    }

    @AfterAll
    public static void tearDown(){
        if (sessionFactory != null){
            sessionFactory.close();
            System.out.println("SessionFactory closed!");
        }
    }

    @BeforeEach
    public void openSession(){
        session = sessionFactory.openSession();
        System.out.println("Session created");
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
    public void testCreateProduct(){
        product = productRepository.createProduct(product);
        Assertions.assertThat(product.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testGetSingleProduct(){
        Product dbProduct = productRepository.findProductById(product.getId());
        Assertions.assertThat(dbProduct.getProductName()).isEqualTo(product.getProductName());
        Assertions.assertThat(dbProduct.getPrice()).isEqualTo(product.getPrice());
        Assertions.assertThat(dbProduct.getQuantity()).isEqualTo(product.getQuantity());
        Assertions.assertThat(dbProduct.getId()).isEqualTo(product.getId());
    }

    @Test
    @Order(3)
    public void testUpdateSingleProduct(){
        product.setProductName("TestUpdate");
        product.setQuantity(99L);
        product.setPrice(99D);
        Product updatedProduct = productRepository.updateProduct(product);
        Assertions.assertThat(updatedProduct.getProductName()).isEqualTo(product.getProductName());
        Assertions.assertThat(updatedProduct.getQuantity()).isEqualTo(product.getQuantity());
        Assertions.assertThat(updatedProduct.getPrice()).isEqualTo(product.getPrice());
    }

    @Test
    @Order(4)
    public void testRetrievingAllProducts(){
        List<Product> products = productRepository.findAllProducts();
        Assertions.assertThat(products).isNotEmpty();
    }

    @Test
    @Order(5)
    public void testRetrievingAllProductsWithQuantityOverZero(){
        List<Product> products = productRepository.findAllAvailableProducts();
        products.forEach(product1 -> Assertions.assertThat(product1.getQuantity()).isGreaterThan(0));
    }

    @Test
    @Order(6)
    public void testRemovingProduct(){
        Product findProduct = productRepository.findProductById(product.getId());
        Assertions.assertThat(findProduct).isNotNull();
        productRepository.deleteProduct(findProduct);
        Product deletedProduct = productRepository.findProductById(product.getId());
        Assertions.assertThat(deletedProduct).isNull();
    }

}
