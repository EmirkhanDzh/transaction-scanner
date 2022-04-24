package com.notiprice.notiprice.product

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/products")
class ProductController(val productService: ProductService) { //ToDo: Dto

    @PostMapping
    fun addProduct(@RequestBody product: Product): Product {
        return productService.addProduct(product)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long,@RequestBody product: Product) {
        productService.updateProduct(product)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long) {
        productService.deleteProduct(id)
    }

    @GetMapping
    fun getProducts(): List<Product> {
        return productService.getAllProducts()
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): Product {
        return productService.getProductById(id)
    }
}