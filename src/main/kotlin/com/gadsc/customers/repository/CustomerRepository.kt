package com.gadsc.customers.repository

import com.gadsc.customers.model.Customer
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CustomerRepository : CrudRepository<Customer, UUID>
