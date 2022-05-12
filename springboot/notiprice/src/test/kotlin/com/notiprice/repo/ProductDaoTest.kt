//package com.notiprice.repo
//
//
//import com.notiprice.notiprice.entity.Product
//import com.notiprice.notiprice.dao.ProductDao
//import org.junit.jupiter.api.*
//
//import org.junit.jupiter.api.Assertions.*
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class ProductDaoTest(@Autowired val productDao: ProductDao) {
//
//    val product = Product(0, "Nike", 99.98, "USD", "nike.com", "i'm xpath")
//
//    @Test
//    @Order(1)
//    fun save() {
//        productDao.save(product)
//
//        assertNotEquals(0, product.id)
//    }
//
//    @Test
//    @Order(2)
//    fun findByIdOrNull() {
//
//        val searchRes = productDao.findByIdOrNull(product.id)
//
//        assertNotNull(searchRes)
//    }
//
//    @Test
//    @Order(3)
//    fun update() {
//        val updated = Product(product.id, "NewNike", 99.98, "USD", "nike.com", "i'm xpath")
//
//        assertDoesNotThrow { productDao.update(updated) }
//        val searchRes = productDao.findByIdOrNull(product.id)!!
//        assertEquals(updated.name, searchRes.name)
//
//        product.name = updated.name
//    }
//
//    @Test
//    @Order(4)
//    fun findAll() {
//        val resList = productDao.findAllUserProducts()
//
//        assertEquals(1, resList.size)
//        assertEquals(product, resList.first())
//    }
//
//    @Test
//    @Order(5)
//    fun delete() {
//        assertDoesNotThrow { productDao.delete(product.id) }
//
//        val resList = productDao.findAllUserProducts()
//        assertEquals(0, resList.size)
//    }
//
//
//}