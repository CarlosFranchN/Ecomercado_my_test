package com.example.gateway_service.service;

import  java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Product;

@Service
public class ProductService {
    private Map<Integer,Product> products = new HashMap<>();
    private int nextId = 1;

    public Product createProduct(Product product){
        product.setId(nextId++);
        products.put(product.getId(), product);
        return  product;
    }

    public Product getProductById(int id){
        return products.get(id);
    }

    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    

    public Product updateProduct(int id, Product newProduct){
        if(products.containsKey(id)){
            newProduct.setId(id);
            products.put(id, newProduct);
            return  newProduct;
        }
        return  null;
    }
    public boolean purchaseProduct(int id,int quantityPurchase){
        Product item = products.get(id);
        if (item != null && item.getQuantity() >= quantityPurchase){
            item.setQuantity(item.getQuantity() - quantityPurchase);
            return  true;
        }
        return false;
    }
}
