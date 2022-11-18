package br.atelieartessimoes.controller;

import br.atelieartessimoes.dtos.ProductDTO;
import br.atelieartessimoes.model.Product;
import br.atelieartessimoes.service.ProductService;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> findAll(@PageableDefault Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);

        Page<ProductDTO> productDTOPage = products.map(product -> modelMapper.map(product, ProductDTO.class));

        for (ProductDTO productDTO : productDTOPage) {
            productDTO.getClass();
        }

        return ResponseEntity.ok(productDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable UUID id) {
        Product product = productService.findById(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insertProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.insertProduct(modelMapper.map(productDTO, Product.class));

        ProductDTO newProduct = modelMapper.map(product, ProductDTO.class);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(id, modelMapper.map(productDTO, Product.class));

        return ResponseEntity.ok(modelMapper.map(product, ProductDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable UUID id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
