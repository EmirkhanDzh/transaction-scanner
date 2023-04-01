//package com.notiprice
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.notiprice.dto.ProductDto
//import com.notiprice.dto.UserDto
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.Assertions.*
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.HttpStatus
//import org.springframework.http.MediaType
//import org.springframework.test.context.DynamicPropertyRegistry
//import org.springframework.test.context.DynamicPropertySource
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.ResultActionsDsl
//import org.springframework.test.web.servlet.get
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import org.testcontainers.junit.jupiter.Testcontainers
//import com.fasterxml.jackson.module.kotlin.readValue
//
//@Testcontainers
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
//class NotipriceApplicationTests(
//    @Autowired private val mockMvc: MockMvc,
//    @Autowired private val objectMapper: ObjectMapper
//) : TestContainer() {
//
//
//    @Test
//    @Order(1)
//    fun `sign up`() {
//
//
//        val json = mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/auth/sign-up")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userDto))
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk).andReturn().response.contentAsString
//
//
//        val response = objectMapper.readValue(json, UserDto::class.java)
//        val tempPassword = userDto.password
//        userDto.password = ""
//
//        assertEquals(userDto, response)
//
//        userDto.password = tempPassword
//    }
//
//    @Test
//    @Order(2)
//    fun `sign in`() {
//
//        val json = mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/auth/sign-in")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userDto))
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk).andReturn().response.contentAsString
//
//        assertTrue(json.isNotBlank())
//    }
//
//    @Test
//    @Order(3)
//    fun `get user`() {
//
//        val user = mockMvc.get("/users/get?username=${userDto.username}").readResponse<UserDto>(HttpStatus.OK)
//
//        assertEquals(userDto.username, user.username)
//    }
//
//    @Test
//    @Order(4)
//    fun `add product`() {
//
//        val json = mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/products?username=${userDto.username}")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(productDto))
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk).andReturn().response.contentAsString
//
//        val response = objectMapper.readValue(json, ProductDto::class.java)
//
//        assertAll(
//            { assertNotEquals(0, response.id) },
//            { assertEquals(productDto.name, response.name) }
//        )
//
//        productDto = response
//    }
//
//    @Test
//    @Order(5)
//    fun `get product`() {
//        val product = mockMvc
//            .get("/products/${productDto.id}")
//            .readResponse<ProductDto>(HttpStatus.OK)
//
//
//
//        assertEquals(productDto, product)
//    }
//
//    @Test
//    @Order(6)
//    fun `edit product`() {
//        productDto.name += " #1"
//
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .put("/products/${productDto.id}")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(productDto))
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//
//        val updated = mockMvc
//            .get("/products/${productDto.id}")
//            .readResponse<ProductDto>(HttpStatus.OK)
//
//        assertEquals(productDto, updated)
//    }
//
//    @Test
//    @Order(7)
//    fun `get products of user`() {
//        val products = mockMvc
//            .get("/products?username=${userDto.username}")
//            .readResponse<List<ProductDto>>(HttpStatus.OK)
//        assertEquals(1, products.size)
//        assertEquals(productDto, products.first())
//    }
//
//    @Test
//    @Order(8)
//    fun `delete product`() {
//
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .delete("/products/${productDto.id}")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//
//        mockMvc
//            .get("/products/${productDto.id}")
//            .andExpect { MockMvcResultMatchers.status().is4xxClientError }
//    }
//
//
//    private inline fun <reified T> ResultActionsDsl.readResponse(expectedStatus: HttpStatus = HttpStatus.OK): T = this
//        .andExpect { status { isEqualTo(expectedStatus.value()) } }
//        .andReturn().response.getContentAsString(Charsets.UTF_8)
//        .let { if (T::class == String::class) it as T else objectMapper.readValue(it) }
//
//    companion object {
//
//        var productDto = ProductDto(
//            0, "Shirt", 0.0, "",
//            "https://www.avito.ru/odintsovo/odezhda_obuv_aksessuary/rubashka_hm_2366591359",
//            "//*[@id=\"price-value\"]/span/span/span[1]",
//            ""
//        )
//
//        val userDto = UserDto(992338299, "nurzhigitss", "123456")
//
//
//        @JvmStatic
//        @DynamicPropertySource
//        fun datasourceConfig(registry: DynamicPropertyRegistry) {
//            registry.add("spring.datasource.url", container::getJdbcUrl)
//            registry.add("spring.datasource.password", container::getPassword)
//            registry.add("spring.datasource.username", container::getUsername)
//        }
//    }
//}
