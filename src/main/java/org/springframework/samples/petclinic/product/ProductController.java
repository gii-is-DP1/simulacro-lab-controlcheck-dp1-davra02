package org.springframework.samples.petclinic.product;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    private static final String PRODUCT_FORM = "products/createOrUpdateProductForm";

    @ModelAttribute("types")
	public Collection<ProductType> populatePetTypes() {
		return this.productService.findAllProductTypes();
	}

    @GetMapping(value = "/create")
	public String initCreationForm(ModelMap model) {
		Product p = new Product();
		model.put("product", p);
		return PRODUCT_FORM;
	}

	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Product p, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("product", p);
			return PRODUCT_FORM;
		}
		else {
                    try{
                    	this.productService.save(p);
                    }catch(Exception ex){
                        result.rejectValue("name", "duplicate", "already exists");
                        return PRODUCT_FORM;
                    }
                    return "welcome";
		}
	}
    
}
