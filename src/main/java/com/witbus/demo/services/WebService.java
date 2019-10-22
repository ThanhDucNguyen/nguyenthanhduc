package com.witbus.demo.services;

import com.witbus.demo.dao.models.Product;
import com.witbus.demo.dto.ProductDTO;
import com.witbus.demo.dto.UserDTO;

import java.util.List;

public interface WebService {
    List<ProductDTO> listProduct();

    Product detailProduct(Long id);

    List<ProductDTO> listAllProductByValue(String type);

    List<ProductDTO> listProductByValue(String type, String origin);

    String sendMail(String phone, String product);

    UserDTO login(UserDTO userDTO);

    ProductDTO addProduct(ProductDTO productDTO);
}
