//package com.notiprice.controller
//
//import com.notiprice.dto.ProductDto
//import com.notiprice.dto.toEntity
//import com.notiprice.entity.Product
//import com.notiprice.entity.toDto
//import org.springframework.http.MediaType
//import org.springframework.web.bind.annotation.*
//
///**
// * Контроллер отслеживаемых товаров.
// */
//@RestController
//@RequestMapping("/products")
//class ProductController(private val productService: ProductService) {
//    /**
//     * Создание нового товара.
//     */
//    @PostMapping
//    fun addProduct(@RequestBody product: ProductDto, @RequestParam username: String): ProductDto {
//
//        return productService.addProduct(product.toEntity(), username).toDto()
//    }
//
//    /**
//     * Изменение данных о товаре.
//     */
//    @PutMapping("/{id}")
//    fun updateProduct(@PathVariable id: Long, @RequestBody product: ProductDto) {
//
//        productService.updateProduct(product.toEntity())
//    }
//
//    /**
//     * Удаление товара.
//     */
//    @DeleteMapping("/{id}")
//    fun deleteProduct(@PathVariable id: Long) {
//
//        productService.deleteProduct(id)
//    }
//
//    /**
//     * Получение товара по пользовательскому имени.
//     */
//    @GetMapping
//    fun getProducts(@RequestParam username: String): List<ProductDto> {
//
//        return productService.getAllUserProducts(username).map(Product::toDto)
//    }
//
//    /**
//     * Получение товара по идентификатору.
//     */
//    @GetMapping("/{id}")
//    fun getProductById(@PathVariable id: Long): ProductDto {
//
//        return productService.getProductById(id).toDto()
//    }
//
//    /**
//     * Получение xpath продукта по URL. В базе данных ищутся xpath от базового домена URL.
//     * Найденные xpath проверяются можно ли получить значение по этому xpath значение.
//     * Если таких несколько, то выбирается самый популярный.
//     */
//    @GetMapping("/xpath")
//    fun getProductXpathByUrl(@RequestParam url: String): String {
//
//        return productService.getProductXpathByUrl(url)
//    }
//
//    /**
//     * Получает страницу по URL и выделяет элемент по xpath и возвращает страницу.
//     */
//    @GetMapping(value = ["/html"], produces = [MediaType.TEXT_HTML_VALUE])
//    fun getHtmlWithHighlightedElement(@RequestParam url: String, @RequestParam xpath: String): String {
//
//        return productService.getHtmlWithHighlightedElement(url, xpath)
//    }
//}