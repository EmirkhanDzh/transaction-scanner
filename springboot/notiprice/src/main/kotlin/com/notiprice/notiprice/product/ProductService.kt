package com.notiprice.notiprice.product

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ProductService(val productDao: ProductDao) {

    fun addProduct(product: Product): Product {
        return productDao.save(product)
    }

    fun getProductById(id: Long): Product {
        return productDao.findByIdOrNull(id) ?: throw IllegalArgumentException("No such element")//ToDo: write a norm mess
    }

    fun getAllProducts(): List<Product> {
        return productDao.findAll()
    }

    fun updateProduct(product: Product) {
        productDao.update(product) //ToDo: throw ex there
    }

    fun deleteProduct(id: Long) {
        productDao.delete(id)
    }
}