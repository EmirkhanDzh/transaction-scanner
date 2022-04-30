package com.notiprice.notiprice.service

import com.notiprice.notiprice.dao.UserDao
import com.notiprice.notiprice.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userDao: UserDao) {

    fun addProduct(user: User): User {
        return userDao.save(user)
    }

    fun getProductById(id: Long): User {
        return userDao.findByIdOrNull(id) ?: throw IllegalArgumentException("No such element")//ToDo: write a norm mess
    }

    fun getProductByUsername(username: String): User {
        return userDao.findByUsernameOrNull(username) ?: throw IllegalArgumentException("No such element")//ToDo: write a norm mess
    }

    fun login(user: User): User {

        val userDb = getProductByUsername(user.username)

        if(user.password != userDb.password) {
            throw IllegalArgumentException("Password is incorrect")
        }

        return user
    }

    fun getAllProducts(): List<User> {
        return userDao.findAll()
    }

    fun updateProduct(user: User) {
        userDao.update(user) //ToDo: throw ex there
    }

    fun deleteProduct(id: Long) {
        userDao.delete(id)
    }
}