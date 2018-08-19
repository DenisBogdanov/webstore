package ru.bogdanium.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdanium.webstore.model.Product;

import java.math.BigDecimal;

/**
 * Denis, 19.08.2018
 */

@Controller
public class ProductController {

    @RequestMapping("/products")
    public String getProducts(Model model) {
        Product iphone = new Product("P1234", "iPhone 8", new BigDecimal(600));
        iphone.setDescription("iPhone");
        iphone.setCategory("Smartphone");
        iphone.setManufacturer("Apple");
        iphone.setUnitsInStock(1000);
        model.addAttribute("iphone", iphone);
        return "products";
    }
}
