package br.atelieartessimoes.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.atelieartessimoes.model.Product;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product findById(UUID id);

    Product insertProduct(Product product);

    Product updateProduct(UUID id, Product product);

    void deleteProduct(UUID id);

}
