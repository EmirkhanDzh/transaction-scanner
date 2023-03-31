package com.notiprice.dbfields

enum class TransactionFieldsInDataBase(name: String) {
    ID("id"),
    FROM_CLIENT_ID("from_client_id"),
    TO_CLIENT_ID("to_client_id"),
    AMOUNT("amount"),
    CURRENCY("currency"),
    BANK_FROM("bank_from"),
    BANK_TO("bank_to"),
    PAY_SYSTEM_FROM("paysystem_from"),
    PAY_SYSTEM_TO("paysystem_to"),
    TRANSFER_DATE("transfer_date"),
    CITY_FROM("city_from"),
    CITY_TO("city_to"),
    COUNTRY_CODE_FROM("country_code_from"),
    COUNTRY_CODE_TO("country_code_to"),
    RESULT_ID("result_id"),
}