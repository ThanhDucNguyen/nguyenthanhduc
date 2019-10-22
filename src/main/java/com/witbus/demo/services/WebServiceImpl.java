package com.witbus.demo.services;


import com.witbus.demo.dao.models.Product;
import com.witbus.demo.dao.models.User;
import com.witbus.demo.dao.repository.ProductRepository;
import com.witbus.demo.dao.repository.UserRepository;
import com.witbus.demo.dto.ProductDTO;
import com.witbus.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

@Service
public class WebServiceImpl implements WebService{
    @Autowired
    private JavaMailSender sender;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public WebServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    static List<ProductDTO> products(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setImage(product.getImage());
            productDTO.setOrigin(product.getOrigin());
            productDTO.setColor(product.getColor());
            productDTO.setType(product.getType());
            productDTO.setMainten(product.getMainten());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> listProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products(products);
        return productDTOS;
    }

    @Override
    public Product detailProduct(Long id) {
        Product product = productRepository.findProductById(id);
        return product;
    }

    @Override
    public List<ProductDTO> listAllProductByValue( String type) {
        List<Product> products = productRepository.listProductByType(type);
        List<ProductDTO> productDTOS = products(products);
        return productDTOS;
    }

    @Override
    public List<ProductDTO> listProductByValue(String type, String origin) {
        List<Product> products = productRepository.listProductByTypeAndOrigin(type, origin);
        List<ProductDTO> productDTOS = products(products);
        return productDTOS;
    }

    @Override
    public String sendMail(String phone, String product) {
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        try {
//
//            helper.setTo("ntduc2812@gmail.com");
//            helper.setText("\nXin kinh chào nhà xe: " +
//                    "\nMật khẩu truy cập quản lý của bạn: "+
//                    "\n-------------------------------------------" +
//                    "\nCHÚC NHÀ XE LUÔN THÀNH CÔNG"
//            );
//            helper.setSubject("Mail From Spring Boot");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        sender.send(message);
        return "OK";
    }
    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.login(userDTO.getUserName(),userDTO.getPassword());
        if (user != null){
            userDTO.setId(user.getId());
        }
        return userDTO;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setType(productDTO.getType());
        product.setOrigin(productDTO.getOrigin());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        String image = productDTO.getImage();
        BufferedImage bImage = null;
        try {
            File initialImage = new File("C://fakepath/Skype/Picture/2019/06/22T20/12/58/435Z.jpeg");
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "jpg", new File("C://Users/PC/Desktop/image1.jpg"));
            ImageIO.write(bImage, "png", new File("C://Users/PC/Desktop/image1.png"));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        System.out.println("Images were written succesfully.");
        product.setMainten(productDTO.getMainten());
        product.setColor(productDTO.getColor());
        productRepository.save(product);
        return null;
    }
}
