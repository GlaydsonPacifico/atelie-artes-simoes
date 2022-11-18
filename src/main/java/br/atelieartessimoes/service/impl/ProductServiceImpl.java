package br.atelieartessimoes.service.impl;

import br.atelieartessimoes.model.Product;
import br.atelieartessimoes.repository.ProductRepository;
import br.atelieartessimoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amazonaws.services.chimesdkmessaging.model.NotFoundException;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(null));
        return product;
    }

    @Override
    public Product insertProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    @Override
    public Product updateProduct(UUID id, Product product) {
        Product productSrc = this.findById(id);
        product.setId(productSrc.getId());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
