package com.notiprice.notiprice.controller

import com.notiprice.notiprice.dto.ProductDto
import com.notiprice.notiprice.dto.toEntity
import com.notiprice.notiprice.entity.Product
import com.notiprice.notiprice.entity.toDto
import com.notiprice.notiprice.service.ProductService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/products")
class ProductController(val productService: ProductService) { //ToDo: Dto

    @PostMapping
    fun addProduct(@RequestBody product: ProductDto): ProductDto {
        return productService.addProduct(product.toEntity()).toDto()
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
}