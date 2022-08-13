package com.github.fabriciolfj.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTest {

    static {
        GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(6379);
        redis.start();
        System.setProperty("spring.redis.host", redis.getHost());
        System.setProperty("spring.redis.port", redis.getMappedPort(6379).toString());
    }

    @Autowired
    private ProductService productService;

    @Test
    void testProductCreate_whenGettingProductById_thenProductExistsAndHasSameProperties() {
        Product product = new Product("1", "teste", BigDecimal.valueOf(10));

        productService.create(product);
        var result = productService.getProduct(product.getId());

        assertEquals(product.getName(), result.getName());
        assertTrue(product.getPrice().compareTo(result.getPrice()) == 0);
    }
}
