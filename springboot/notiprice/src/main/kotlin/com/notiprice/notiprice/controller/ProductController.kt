package com.notiprice.notiprice.controller

import com.notiprice.notiprice.dto.ProductDto
import com.notiprice.notiprice.dto.toEntity
import com.notiprice.notiprice.entity.Product
import com.notiprice.notiprice.entity.toDto
import com.notiprice.notiprice.service.ProductService
import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import us.codecraft.xsoup.Xsoup


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

        require(url.isNotBlank() && url.isNotEmpty())
        return "//*[@id=\"price-value\"]/span/span/span[1]"
    }
//
//    @GetMapping(value = ["/img"], produces = [MediaType.IMAGE_PNG_VALUE])
//    fun getScreenshotByXpath(@RequestParam url: String, @RequestParam xpath: String): ByteArray? {
//
//        println(xpath)
//
//
//        val imgFile = ClassPathResource("img.png")
//        return IOUtils.toByteArray(imgFile.inputStream)
//    }

    @GetMapping(value = ["/html"], produces = [MediaType.TEXT_HTML_VALUE])
    fun getHtmlWithHighlightedElement(@RequestParam url: String, @RequestParam xpath: String): String {
//        val tempUrl = "https://www.avito.ru/odintsovo/odezhda_obuv_aksessuary/rubashka_hm_2366591359"
//        val tempXpath = "//*[@id=\"price-value\"]/span/span/span[1]"

        val doc = Jsoup.connect(url).get()

        Xsoup.compile(xpath)
            .evaluate(doc)
            .elements.first()
            ?.attr("style", "background-color: #FFFF00")

        return doc.toString()
    }
}