package com.notiprice.dao

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class TransactionDao(private val jdbcTemplate: NamedParameterJdbcTemplate) {

    private companion object {
        val GET_TRANSACTION_TO_SCAN_SQL = """
            select distinct tr.id as transaction_id
            from transactions tr
                     join operators_results opr on opr.id = tr.operator_result_id
                     left join rules_engine_results rer on opr.rules_engine_result_id = rer.id
            where rer.id is null
            order by tr.id
            limit 1000;
        """.trimIndent()
        // for update skip locked
    }


    fun getTransactionIdsToScan(): List<Long> {
        return jdbcTemplate.query(GET_TRANSACTION_TO_SCAN_SQL) { rs, _ ->
            rs.getLong("transaction_id")
        }
    }
}