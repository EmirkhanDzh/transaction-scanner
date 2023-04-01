package com.transaction.scanner.service

import com.transaction.scanner.dao.AdminRepository
import com.transaction.scanner.dao.OperatorRepository
import com.transaction.scanner.entity.Admin
import com.transaction.scanner.entity.AuthUser
import com.transaction.scanner.entity.Operator
import com.transaction.scanner.security.JwtTokenUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val operatorService: OperatorService,
    private val authManager: AuthenticationManager,
    private val jwtUtil: JwtTokenUtil,
    private val operatorRepository: OperatorRepository,
    private val adminRepository: AdminRepository,
) {

    fun addUser(user: Operator): Operator {
        val savedUser = operatorService.addUser(user)
        savedUser.operatorPassword = ""
        return savedUser
    }


    fun login(user: Operator): Map<String, String> {

        val token = UsernamePasswordAuthenticationToken(user.username, user.password)

        val authentication: Authentication = authManager.authenticate(
            token
        )

        val accessToken = jwtUtil.generateAccessToken(authentication.principal as AuthUser)

        val entity = operatorRepository.findByOperatorUsername(user.username)
        checkNotNull(entity)

        return mapOf(
            "token" to accessToken,
            "operatorId" to entity.id.toString(),
            "operatorUsername" to checkNotNull(entity.username)
        )
    }

    fun adminLogin(user: Admin): Map<String, String> {

        val token = UsernamePasswordAuthenticationToken(user.username, user.password)

        val authentication: Authentication = authManager.authenticate(
            token
        )

        val accessToken = jwtUtil.generateAccessToken(authentication.principal as AuthUser)

        val entity = adminRepository.findByAdminUsername(user.username)
        checkNotNull(entity)

        return mapOf(
            "token" to accessToken,
            "operatorId" to entity.id.toString(),
            "operatorUsername" to checkNotNull(entity.username)
        )
    }
}