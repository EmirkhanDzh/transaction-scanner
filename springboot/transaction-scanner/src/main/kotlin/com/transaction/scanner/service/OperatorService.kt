package com.transaction.scanner.service

import com.transaction.scanner.dao.OperatorRepository
import com.transaction.scanner.entity.Operator
import org.springframework.stereotype.Component

@Component
class OperatorService(private val operatorRepository: OperatorRepository) : UserService<Operator>(operatorRepository) {

    override fun encodePassword(user: Operator) {
        user.operatorPassword = passwordEncoder.encode(user.operatorPassword)
    }

    override fun login(user: Operator): Operator {
        val userDb = operatorRepository.findByOperatorUsername(user.operatorUsername)
        requireNotNull(userDb) {"Username is incorrect!"}

        if (!passwordEncoder.matches(user.operatorPassword, userDb.operatorPassword)) {

            throw IllegalArgumentException("Password is incorrect!")
        }

        return userDb
    }
}