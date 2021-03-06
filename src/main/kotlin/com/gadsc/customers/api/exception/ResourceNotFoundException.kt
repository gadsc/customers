package com.gadsc.customers.api.exception

import org.springframework.http.HttpStatus

class ResourceNotFoundException(message: String) : CustomersException(message) {

    override val statusCode: HttpStatus = HttpStatus.NOT_FOUND

}
