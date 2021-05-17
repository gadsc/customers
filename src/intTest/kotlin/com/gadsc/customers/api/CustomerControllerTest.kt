package com.gadsc.customers.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.gadsc.customers.infra.EntityManagerAdapter
import com.gadsc.customers.api.model.CivilStatus
import com.gadsc.customers.api.model.Customer
import com.gadsc.customers.objectmothers.dto.CustomerDTOObjectMother
import com.gadsc.customers.api.repository.CustomerRepository
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
internal class CustomerControllerTest {

    @Autowired
    private lateinit var entityManager: EntityManagerAdapter

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Nested
    inner class `Create Customer` {

        @Test
        fun `should create and return status created for customer with all fields`() {
            val expected = CustomerDTOObjectMother.build()
            val request = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected))

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expected.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(expected.email))
                .andExpect(MockMvcResultMatchers.jsonPath("$.job_title").value(expected.jobTitle!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.civil_status").value(expected.civilStatus.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value(expected.birthdate.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mother_full_name").value(expected.motherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.father_full_name").value(expected.fatherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.politically_exposed").value(expected.politicallyExposed))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.city_of_birth").value(expected.naturalness?.cityOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.state_of_birth").value(expected.naturalness?.stateOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.country_of_birth").value(expected.naturalness?.countryOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.nationality").value(expected.naturalness?.nationality!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.main_document_type").value(expected.mainDocument.mainDocumentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.code").value(expected.mainDocument.code))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones.length()", Matchers.`is`(expected.phones.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].number").value(expected.phones.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].type").value(expected.phones.first().type))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.length()", Matchers.`is`(expected.addresses.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].city").value(expected.addresses.first().city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].state").value(expected.addresses.first().state))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].street").value(expected.addresses.first().street))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].zipcode").value(expected.addresses.first().zipcode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].neighborhood").value(expected.addresses.first().neighborhood))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].complement").value(expected.addresses.first().complement!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].number").value(expected.addresses.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].address_type").value(expected.addresses.first().addressType))
        }

        @Test
        fun `should create and return status created for customer without optional fields`() {
            val expected = CustomerDTOObjectMother.build(
                jobTitle = null,
                motherFullName = null,
                fatherFullName = null,
                naturalness = null
            )
            val request = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected))

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expected.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(expected.email))
                .andExpect(MockMvcResultMatchers.jsonPath("$.job_title").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.civil_status").value(expected.civilStatus.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value(expected.birthdate.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mother_full_name").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.father_full_name").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.politically_exposed").value(expected.politicallyExposed))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.main_document_type").value(expected.mainDocument.mainDocumentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.code").value(expected.mainDocument.code))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones.length()", Matchers.`is`(expected.phones.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].number").value(expected.phones.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].type").value(expected.phones.first().type))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.length()", Matchers.`is`(expected.addresses.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].city").value(expected.addresses.first().city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].state").value(expected.addresses.first().state))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].street").value(expected.addresses.first().street))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].zipcode").value(expected.addresses.first().zipcode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].neighborhood").value(expected.addresses.first().neighborhood))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].complement").value(expected.addresses.first().complement!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].number").value(expected.addresses.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].address_type").value(expected.addresses.first().addressType))
        }

        @Test
        fun `shouldn't create and return status bad request for customer without required fields`() {
            val request = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        }
    }

    @Nested
    inner class `List Customer` {

        @Test
        fun `should return all existing customers`() {
            val customers = listOf(
                CustomerDTOObjectMother.build(),
                CustomerDTOObjectMother.build(
                    name = "Customer name 2",
                    email = "customer_email_2@email.com",
                    jobTitle = "Customer Job Title 2",
                    civilStatus = CivilStatus.MARRIED,
                    birthdate = LocalDate.of(1991, 1, 10),
                    motherFullName = "Customer Mother Name 2",
                    fatherFullName = "Customer Father Name 2"
                )
            )

            customers.forEach { customerRepository.save(it.toDomain()) }

            val request = MockMvcRequestBuilders.get("/customers")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.`is`(customers.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(customers[0].name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(customers[0].email))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].job_title").value(customers[0].jobTitle!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].civil_status").value(customers[0].civilStatus.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthdate").value(customers[0].birthdate.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mother_full_name").value(customers[0].motherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].father_full_name").value(customers[0].fatherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].politically_exposed").value(customers[0].politicallyExposed))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].naturalness.city_of_birth").value(customers[0].naturalness?.cityOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].naturalness.state_of_birth").value(customers[0].naturalness?.stateOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].naturalness.country_of_birth").value(customers[0].naturalness?.countryOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].naturalness.nationality").value(customers[0].naturalness?.nationality!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].main_document.main_document_type").value(customers[0].mainDocument.mainDocumentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].main_document.code").value(customers[0].mainDocument.code))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phones.length()", Matchers.`is`(customers[0].phones.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phones[0].number").value(customers[0].phones.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phones[0].type").value(customers[0].phones.first().type))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses.length()", Matchers.`is`(customers[0].addresses.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].city").value(customers[0].addresses.first().city))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].state").value(customers[0].addresses.first().state))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].street").value(customers[0].addresses.first().street))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].zipcode").value(customers[0].addresses.first().zipcode))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].neighborhood").value(customers[0].addresses.first().neighborhood))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].complement").value(customers[0].addresses.first().complement!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].number").value(customers[0].addresses.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addresses[0].address_type").value(customers[0].addresses.first().addressType))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(customers[1].name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value(customers[1].email))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].job_title").value(customers[1].jobTitle!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].civil_status").value(customers[1].civilStatus.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].birthdate").value(customers[1].birthdate.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].mother_full_name").value(customers[1].motherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].father_full_name").value(customers[1].fatherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].politically_exposed").value(customers[1].politicallyExposed))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].naturalness.city_of_birth").value(customers[1].naturalness?.cityOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].naturalness.state_of_birth").value(customers[1].naturalness?.stateOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].naturalness.country_of_birth").value(customers[1].naturalness?.countryOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].naturalness.nationality").value(customers[1].naturalness?.nationality!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].main_document.main_document_type").value(customers[1].mainDocument.mainDocumentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].main_document.code").value(customers[1].mainDocument.code))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phones.length()", Matchers.`is`(customers[1].phones.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phones[0].number").value(customers[1].phones.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phones[0].type").value(customers[1].phones.first().type))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses.length()", Matchers.`is`(customers[1].addresses.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].city").value(customers[1].addresses.first().city))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].state").value(customers[1].addresses.first().state))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].street").value(customers[1].addresses.first().street))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].zipcode").value(customers[1].addresses.first().zipcode))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].neighborhood").value(customers[1].addresses.first().neighborhood))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].complement").value(customers[1].addresses.first().complement!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].number").value(customers[1].addresses.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addresses[0].address_type").value(customers[1].addresses.first().addressType))
        }

        @Test
        fun `should return empty list when doesn't exists any customer`() {
            val request = MockMvcRequestBuilders.get("/customers")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.`is`(0)))
        }
    }

    @Nested
    inner class `Find Customer` {

        @Test
        fun `should return existent customer`() {
            val expected = CustomerDTOObjectMother.build()
            val savedCustomer = customerRepository.save(expected.toDomain())
            val request = MockMvcRequestBuilders.get("/customers/${savedCustomer.id}")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expected.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(expected.email))
                .andExpect(MockMvcResultMatchers.jsonPath("$.job_title").value(expected.jobTitle!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.civil_status").value(expected.civilStatus.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value(expected.birthdate.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mother_full_name").value(expected.motherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.father_full_name").value(expected.fatherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.politically_exposed").value(expected.politicallyExposed))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.city_of_birth").value(expected.naturalness?.cityOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.state_of_birth").value(expected.naturalness?.stateOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.country_of_birth").value(expected.naturalness?.countryOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.nationality").value(expected.naturalness?.nationality!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.main_document_type").value(expected.mainDocument.mainDocumentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.code").value(expected.mainDocument.code))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones.length()", Matchers.`is`(expected.phones.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].number").value(expected.phones.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].type").value(expected.phones.first().type))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.length()", Matchers.`is`(expected.addresses.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].city").value(expected.addresses.first().city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].state").value(expected.addresses.first().state))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].street").value(expected.addresses.first().street))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].zipcode").value(expected.addresses.first().zipcode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].neighborhood").value(expected.addresses.first().neighborhood))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].complement").value(expected.addresses.first().complement!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].number").value(expected.addresses.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].address_type").value(expected.addresses.first().addressType))

        }

        @Test
        fun `should return not found when customer doesn't exist`() {
            val randomUUID = UUID.randomUUID()
            val request = MockMvcRequestBuilders.get("/customers/$randomUUID")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }

        @Test
        fun `should return bad request when path param isn't is a uuid`() {
            val request = MockMvcRequestBuilders.get("/customers/12345")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        }
    }

    @Nested
    inner class `Delete Customer` {

        @Test
        fun `should delete existent customer`() {
            val savedCustomer = customerRepository.save(CustomerDTOObjectMother.build().toDomain())
            val request = MockMvcRequestBuilders.delete("/customers/${savedCustomer.id}")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent)
        }

        @Test
        fun `should return not found when customer doesn't exist`() {
            val randomUUID = UUID.randomUUID()
            val request = MockMvcRequestBuilders.delete("/customers/$randomUUID")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }

        @Test
        fun `should return bad request when path param isn't is a uuid`() {
            val request = MockMvcRequestBuilders.delete("/customers/12345")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        }
    }

    @Nested
    inner class `Update Customer` {

        @Test
        fun `should update existing customer`() {
            val savedCustomer = customerRepository.save(CustomerDTOObjectMother.build().toDomain())
            val expected = CustomerDTOObjectMother.build(
                name = "Customer name 2",
                email = "customer_email_2@email.com",
                jobTitle = "Customer Job Title 2",
                civilStatus = CivilStatus.MARRIED,
                birthdate = LocalDate.of(1991, 1, 10),
                motherFullName = "Customer Mother Name 2",
                fatherFullName = "Customer Father Name 2"
            )
            val request = MockMvcRequestBuilders.put("/customers/${savedCustomer.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected))

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expected.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(expected.email))
                .andExpect(MockMvcResultMatchers.jsonPath("$.job_title").value(expected.jobTitle!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.civil_status").value(expected.civilStatus.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value(expected.birthdate.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mother_full_name").value(expected.motherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.father_full_name").value(expected.fatherFullName!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.politically_exposed").value(expected.politicallyExposed))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.city_of_birth").value(expected.naturalness?.cityOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.state_of_birth").value(expected.naturalness?.stateOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.country_of_birth").value(expected.naturalness?.countryOfBirth!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.naturalness.nationality").value(expected.naturalness?.nationality!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.main_document_type").value(expected.mainDocument.mainDocumentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.main_document.code").value(expected.mainDocument.code))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones.length()", Matchers.`is`(expected.phones.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].number").value(expected.phones.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].type").value(expected.phones.first().type))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.length()", Matchers.`is`(expected.addresses.size)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].city").value(expected.addresses.first().city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].state").value(expected.addresses.first().state))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].street").value(expected.addresses.first().street))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].zipcode").value(expected.addresses.first().zipcode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].neighborhood").value(expected.addresses.first().neighborhood))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].complement").value(expected.addresses.first().complement!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].number").value(expected.addresses.first().number))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].address_type").value(expected.addresses.first().addressType))
        }

        @Test
        fun `should return not found when customer doesn't exist`() {
            val randomUUID = UUID.randomUUID()
            val request = MockMvcRequestBuilders.put("/customers/$randomUUID")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CustomerDTOObjectMother.build()))

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }

        @Test
        fun `should return bad request when customer id isn't valid`() {
            val request = MockMvcRequestBuilders.put("/customers/12345")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CustomerDTOObjectMother.build()))

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        }

        @Test
        fun `should return bad request when customer body isn't valid`() {
            val randomUUID = UUID.randomUUID()
            val request = MockMvcRequestBuilders.put("/customers/$randomUUID")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")

            mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        }
    }

    @AfterEach
    fun afterEach() {
        entityManager.deleteAll(Customer::class)
    }
}
