package com.notiprice.dao

import com.notiprice.entity.User

class UserDaoImpl : UserDao {
    override fun save(user: User): User {
        TODO("Not yet implemented")
    }

    override fun findByIdOrNull(id: Long): User? {
        TODO("Not yet implemented")
    }

    override fun findByUsernameOrNull(name: String): User? {
        TODO("Not yet implemented")
    }

    override fun update(user: User): Int {
        TODO("Not yet implemented")
    }

    override fun delete(userId: Long): Int {
        TODO("Not yet implemented")
    }
}