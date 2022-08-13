package com.github.fabriciolfj.productservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(final String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(final Product product) {
        return productRepository.save(product);
    }

    public void delete(final String id) {
        productRepository.deleteById(id);
    }

    public Product update(final Product product, final String id) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    return productRepository.save(product);
                }).orElse(null);
    }
}
