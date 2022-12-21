package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsCheaperThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public ProductType getProductType(String typeName) {
        return productRepository.findPtByName(typeName);
    }

    @Transactional
    public Product save(Product p){
        return productRepository.save(p);       
    }

    public List<ProductType> findAllProductTypes(){
        return productRepository.findAllProductTypes();
    }

    
}
