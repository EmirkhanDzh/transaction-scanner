//package com.notiprice.security
//
//import com.notiprice.dao.AdminRepository
//import com.notiprice.dao.OperatorRepository
//import com.notiprice.service.AdminService
//import com.notiprice.service.OperatorService
//import com.notiprice.service.UserService
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.stereotype.Component
//
///**
// * Сервис для класса CustomUserDetails.
// */
//@Component
//class CustomUserDetailsService(
//    private val operatorService: OperatorRepository,
//    private val adminService: AdminRepository
//) : UserDetailsService {
//    /**
//     * Получение экземпляра класса CustomUserDetails по пользовательскому имени.
//     */
//    override fun loadUserByUsername(username: String?): CustomUserDetails {
//
//        adminService.findByUsername(username!!)?.let {
//            return toCustomUserDetails(it.username, it.password)
//        }
//
//
//        operatorService.findByUsername(username)?.let {
//            return toCustomUserDetails(it.username, it.password)
//        }
//        throw IllegalArgumentException("There is no user: $username")
//    }
//}