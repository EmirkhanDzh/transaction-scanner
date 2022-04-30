package com.notiprice.notiprice.service

import com.notiprice.notiprice.entity.Product
import com.notiprice.notiprice.dao.ProductDao
import org.springframework.stereotype.Service

@Service
class ProductService(val productDao: ProductDao) {

    fun addProduct(product: Product): Product {
        product.lastCheck = System.currentTimeMillis()
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