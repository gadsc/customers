package com.gadsc.customers.api.repository

import com.gadsc.customers.api.model.Customer
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CustomerRepository : CrudRepository<Customer, UUID>
