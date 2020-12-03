package com.example.choyxona.service;

import com.example.choyxona.entity.Product;
import com.example.choyxona.entity.ProductKg;
import com.example.choyxona.repository.ProductKgRepository;
import com.example.choyxona.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductKgRepository productKgRepository;

    public void saveEdit(Product product) {
        productRepository.save(product);
    }

    public List<Product> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }



    public void saveEditKg(ProductKg productKg) {
        productKgRepository.save(productKg);
    }

    public List<ProductKg> listKg() {
        return productKgRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void deleteKg(long id) {
        productKgRepository.deleteById(id);
    }
}
