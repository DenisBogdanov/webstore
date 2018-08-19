package ru.bogdanium.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdanium.webstore.model.Product;
import ru.bogdanium.webstore.repository.ProductRepository;

import java.math.BigDecimal;

/**
 * Denis, 19.08.2018
 */
@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products";
    }
}
