package com.notiprice.notiprice.service

import com.notiprice.notiprice.entity.Product
import com.notiprice.notiprice.dao.ProductDao
import com.notiprice.notiprice.dao.SubscriptionDao
import com.notiprice.notiprice.entity.Subscription
import com.notiprice.scarper.getValueByXpath
import org.jsoup.Jsoup
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import us.codecraft.xsoup.Xsoup
import java.net.URL

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

    fun getHtmlWithHighlightedElement(url: String, xpath: String): String {
        val doc = Jsoup.connect(url).get()

        Xsoup.compile(xpath)
            .evaluate(doc)
            .elements.first()
            ?.attr("style", "background-color: #FFFF00")

        return doc.toString()
    }

    fun getProductXpathByUrl(urlString: String): String {

        require(urlString.isNotBlank() && urlString.isNotEmpty())
        val url = URL(urlString)
        val baseUrl = url.host
        val candidates = productDao.getXpathByUrl(baseUrl)

        for(xpath in candidates) {

            if(getValueByXpath(urlString, xpath) != null) {
                return xpath
            }
        }
        return ""
        return "//*[@id=\"price-value\"]/span/span/span[1]"
    }
}