package com.notiprice.notiprice.service

import com.notiprice.notiprice.entity.Product
import com.notiprice.notiprice.dao.ProductDao
import com.notiprice.notiprice.dao.SubscriptionDao
import com.notiprice.notiprice.entity.Subscription
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(val productDao: ProductDao, val subscriptionDao: SubscriptionDao, val userService: UserService) {

    @Transactional
    fun addProduct(product: Product, username: String): Product {
        product.lastCheck = System.currentTimeMillis()
        val savedProduct = productDao.save(product)

        // throws exception if it doesn't exist
        val user = userService.getUserByUsername(username)

        subscriptionDao.save(Subscription(user.chatId, savedProduct.id))

        return savedProduct
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