package ru.bogdanium.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.bogdanium.webstore.exception.NoProductsFoundUnderCategoryException;
import ru.bogdanium.webstore.exception.ProductNotFoundException;
import ru.bogdanium.webstore.model.Product;
import ru.bogdanium.webstore.service.ProductService;
import ru.bogdanium.webstore.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
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

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/products/{category}")
    public String getProductsByCategory(Model model, @PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/products/filter/{params}")
    public String getProductsByFilter(Model model,
                                      @MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(Model model, @RequestParam String id) {
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getForm(Model model) {
        Product productToAdd = new Product();
        model.addAttribute("productToAdd", productToAdd);
        return "addProduct";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("productToAdd") @Valid Product productToAdd,
                             BindingResult result,
                             HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addProduct";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length != 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: " +
                    StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        MultipartFile productImage = productToAdd.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(
                        rootDirectory + "resources\\img\\" + productToAdd.getProductId() + ".png"));

            } catch (Exception e) {
                throw new RuntimeException("Saving Product Image failed", e);
            }
        }

        productService.addProduct(productToAdd);
        return "redirect:/market/products";
    }

    @RequestMapping("/update/stock")
    public String updateStock(Model model) {
        productService.updateAllStock();
        return "redirect:/market/products";
    }

    @RequestMapping("/products/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }


    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "productId",
                "name",
                "unitPrice",
                "description",
                "manufacturer",
                "category",
                "unitsInStock",
                "condition",
                "productImage",
                "language"
        );
        binder.setValidator(productValidator);
    }
}
