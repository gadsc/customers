package com.gadsc.customers.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CustomersException::class)
    fun handleBanquinhoException(banquinhoException: CustomersException): ResponseEntity<ApiError> {
        logger.info(banquinhoException.message)
        return ResponseEntity.status(banquinhoException.statusCode).body(ApiError(banquinhoException.message))
    }
}
