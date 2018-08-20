package ru.bogdanium.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdanium.webstore.service.ProductService;

import java.util.List;
import java.util.Map;

/**
 * Denis, 19.08.2018
 */
@Controller
@RequestMapping("market")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/products/{category}")
    public String getProductsByCategory(Model model, @PathVariable String category) {
        model.addAttribute("products", productService.getProductsByCategory(category));
        return "products";
    }

    @RequestMapping("/products/filter/{params}")
    public String getProductsByFilter(Model model,
            @MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/update/stock")
    public String updateStock(Model model) {
        productService.updateAllStock();
        return "redirect:/market/products";
    }
}
