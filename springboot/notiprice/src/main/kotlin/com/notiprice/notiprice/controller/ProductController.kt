package com.notiprice.notiprice.controller

import com.notiprice.notiprice.dto.ProductDto
import com.notiprice.notiprice.dto.toEntity
import com.notiprice.notiprice.entity.Product
import com.notiprice.notiprice.entity.toDto
import com.notiprice.notiprice.service.ProductService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/products")
class ProductController(val productService: ProductService) { //ToDo: Dto

    @PostMapping
    fun addProduct(@RequestBody product: ProductDto, @RequestParam username: String): ProductDto {
        return productService.addProduct(product.toEntity(), username).toDto()
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: ProductDto) {
        productService.updateProduct(product.toEntity())
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long) {
        productService.deleteProduct(id)
    }

    @GetMapping
    fun getProducts(): List<ProductDto> {
        return productService.getAllProducts().map(Product::toDto)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ProductDto {
        return productService.getProductById(id).toDto()
    }

    @GetMapping("/xpath")
    fun getProductXpathByUrl(@RequestParam url: String): String {


        //return "//*[@id=\"price-value\"]/span/span/span[1]"
        return productService.getProductXpathByUrl(url)
    }

    @GetMapping(value = ["/html"], produces = [MediaType.TEXT_HTML_VALUE])
    fun getHtmlWithHighlightedElement(@RequestParam url: String, @RequestParam xpath: String): String {

        return productService.getHtmlWithHighlightedElement(url, xpath)
    }
}