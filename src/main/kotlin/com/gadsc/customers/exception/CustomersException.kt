package com.gadsc.customers.exception

import org.springframework.http.HttpStatus

abstract class CustomersException(override val message: String) : RuntimeException(message) {

    abstract val statusCode: HttpStatus

}
