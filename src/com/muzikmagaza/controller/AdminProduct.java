package com.muzikmagaza.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.muzikmagaza.model.Product;
import com.muzikmagaza.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminProduct {

	
	private Path path;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/product/addProduct")
	public String addProduct(Model model,HttpServletRequest request)
	{
		String rootDirectory = request.getSession().getServletContext().getContextPath();
		path = Paths.get(rootDirectory+"\\WebContent\\WEB-INF\\resources\\images\\aa.png");
		
		
		Product product = new Product();
		product.setProductCategory("instrument");
		product.setProductCondition("new");
		product.setProductStatus("active");
		
		model.addAttribute("product",product);
		
		model.addAttribute("dizin",path.toString());
		return "addProduct";			
	}
	
	@RequestMapping(value="/product/addProduct",method=RequestMethod.POST)
	public String addProductPost(@Valid @ModelAttribute("product")Product product,BindingResult result,HttpServletRequest request)
	{
		if(result.hasErrors())
		{
			return "addProduct";
		}
		
		productService.addProduct(product);
		
		/*MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath(request.getContextPath());
		path = Paths.get(rootDirectory+"\\WEB-INF\\resources\\images\\aata");
		
		if(productImage != null && !productImage.isEmpty())
		{
			try {
				productImage.transferTo(new File(path.toString()));
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product image saving failed. TTT",e);
			}
		}
		*/
		
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping("product/editProduct/{id}")
	public String editProduct(@PathVariable("id")int id,Model model)
	{
		Product product = productService.getProductById(id);
		
		model.addAttribute("product",product);
		
		return "editProduct";
	}
	
	@RequestMapping(value="/product/editProduct",method=RequestMethod.POST)
	public String editProductPost(@Valid @ModelAttribute("product") Product product,BindingResult result,HttpServletRequest request) {
			
		if(result.hasErrors())
		{
			return "editProduct";
		}
		
		/*
		  MultipartFile productImage = product.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\"+product.getProductId()+".png");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed.", e);
            }
        }
		 */
		
		productService.editProduct(product);
		
		return "redirect:/admin/productInventory";
	}
	@RequestMapping("/product/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") int id,Model model,HttpServletRequest request)
	{
		/*String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + id + ".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
		
		Product product = productService.getProductById(id);
		productService.deleteProduct(product);
		
		return "redirect:/admin/productInventory";
	}
	
}
