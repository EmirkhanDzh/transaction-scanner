package com.notiprice.dao

import com.notiprice.entity.Operator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface OperatorRepository: JpaRepository<Operator, Long>