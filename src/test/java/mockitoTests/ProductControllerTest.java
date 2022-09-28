package mockitoTests;

import dto.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ProductRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {
    @Mock
    private static ProductRepository productRepository;
    private AutoCloseable autoCloseable;
    private ProductController underTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProductController(productRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Order(1)
    void testFindAllProducts() {
        underTest.displayAllProducts();
        verify(productRepository).findAllProducts();
    }

    @Test
    @Order(2)
    void testFindAllAvailableProducts() {
        underTest.displayAllAvailableProducts();
        verify(productRepository).findAllAvailableProducts();
    }

    @Test
    @Order(3)
    void testAddProduct() {
        Product product1 = new Product(20L, "Bag of D", 150L, 12.22);
        productRepository.createProduct(product1);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).createProduct(productArgumentCaptor.capture());

        Product capturedProduct =  productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product1);
    }

    @Test
    @Order(4)
    void testFindProductById() {
        Product product1 = new Product(21L, "Bag of D", 150L, 12.22);
        underTest.findProduct(21L);
        verify(productRepository).findProductById(21L);
    }

    @Test
    @Order(5)
    void testUpdateProduct() {
        Product product1 = new Product(22L, "Bag of D", 150L, 12.22);
        product1.setProductName("Box of D");
        product1.setPrice(15.95);
        productRepository.updateProduct(product1);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).updateProduct(productArgumentCaptor.capture());

        Product capturedProduct =  productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product1);
    }

    @Test
    @Order(6)
    void testDeleteProduct() {
        Product product1 = new Product(22L, "Bag of D", 150L, 12.22);
        productRepository.deleteProduct(product1);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).deleteProduct(productArgumentCaptor.capture());

        Product capturedProduct =  productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product1);
    }
}