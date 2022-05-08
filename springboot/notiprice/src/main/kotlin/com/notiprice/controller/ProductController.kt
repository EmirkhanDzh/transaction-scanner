package com.notiprice.controller

import com.notiprice.dto.ProductDto
import com.notiprice.dto.toEntity
import com.notiprice.entity.Product
import com.notiprice.entity.toDto
import com.notiprice.service.ProductService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

//@CrossOrigin(origins = ["http://localhost:3000"])
//@CrossOrigin(origins = ["*"])
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
    fun getProducts(@RequestParam username: String): List<ProductDto> {

        return productService.getAllUserProducts(username).map(Product::toDto)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ProductDto {

        return productService.getProductById(id).toDto()
    }

    @GetMapping("/xpath")
    fun getProductXpathByUrl(@RequestParam url: String): String {

        return productService.getProductXpathByUrl(url)
    }

    @GetMapping(value = ["/html"], produces = [MediaType.TEXT_HTML_VALUE])
    fun getHtmlWithHighlightedElement(@RequestParam url: String, @RequestParam xpath: String): String {

        return productService.getHtmlWithHighlightedElement(url, xpath)
    }
}