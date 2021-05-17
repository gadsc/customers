package com.gadsc.customers.api.dto

import com.gadsc.customers.objectmothers.dto.CustomerDTOObjectMother
import com.gadsc.customers.objectmothers.model.CustomerObjectMother
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CustomerDTOTest {
    @Nested
    inner class `from domain` {
        @Test
        fun `should create CustomerDTO from domain Customer with optional fields`() {
            val expected = CustomerObjectMother.build()
            val subject = CustomerDTO.fromDomain(expected)

            assertEquals(expected.name, subject.name)
            assertEquals(expected.email, subject.email)
            assertNotNull(subject.jobTitle)
            assertEquals(expected.jobTitle, subject.jobTitle)
            assertEquals(expected.civilStatus, subject.civilStatus)
            assertEquals(expected.birthdate, subject.birthdate)
            assertNotNull(subject.motherFullName)
            assertEquals(expected.motherFullName, subject.motherFullName)
            assertNotNull(subject.fatherFullName)
            assertEquals(expected.fatherFullName, subject.fatherFullName)
            assertEquals(expected.politicallyExposed, subject.politicallyExposed)

            assertEquals(expected.phones.first().type, subject.phones.first().type)
            assertEquals(expected.phones.first().number, subject.phones.first().number)

            assertEquals(expected.addresses.first().city, subject.addresses.first().city)
            assertEquals(expected.addresses.first().state, subject.addresses.first().state)
            assertEquals(expected.addresses.first().street, subject.addresses.first().street)
            assertEquals(expected.addresses.first().zipcode, subject.addresses.first().zipcode)
            assertEquals(expected.addresses.first().neighborhood, subject.addresses.first().neighborhood)
            assertNotNull(subject.addresses.first().complement)
            assertEquals(expected.addresses.first().complement, subject.addresses.first().complement)
            assertEquals(expected.addresses.first().number, subject.addresses.first().number)
            assertEquals(expected.addresses.first().addressType, subject.addresses.first().addressType)


            assertNotNull(subject.naturalness!!.cityOfBirth)
            assertEquals(expected.naturalness!!.cityOfBirth, subject.naturalness!!.cityOfBirth)
            assertNotNull(subject.naturalness!!.stateOfBirth)
            assertEquals(expected.naturalness!!.stateOfBirth, subject.naturalness!!.stateOfBirth)
            assertNotNull(subject.naturalness!!.countryOfBirth)
            assertEquals(expected.naturalness!!.countryOfBirth, subject.naturalness!!.countryOfBirth)
            assertEquals(expected.naturalness!!.nationality, subject.naturalness!!.nationality)

            assertEquals(expected.mainDocument.mainDocumentType, subject.mainDocument.mainDocumentType)
            assertEquals(expected.mainDocument.code, subject.mainDocument.code)

        }

        @Test
        fun `should create CustomerDTO from domain Customer without optional fields`() {
            val expected = CustomerObjectMother.build(
                jobTitle = null,
                motherFullName = null,
                fatherFullName = null,
                naturalness = null
            )
            val subject = CustomerDTO.fromDomain(expected)

            assertEquals(expected.name, subject.name)
            assertEquals(expected.email, subject.email)
            assertNull(subject.jobTitle)
            assertEquals(expected.civilStatus, subject.civilStatus)
            assertEquals(expected.birthdate, subject.birthdate)
            assertNull(subject.motherFullName)
            assertNull(subject.fatherFullName)
            assertEquals(expected.politicallyExposed, subject.politicallyExposed)

            assertEquals(expected.phones.first().type, subject.phones.first().type)
            assertEquals(expected.phones.first().number, subject.phones.first().number)

            assertEquals(expected.addresses.first().city, subject.addresses.first().city)
            assertEquals(expected.addresses.first().state, subject.addresses.first().state)
            assertEquals(expected.addresses.first().street, subject.addresses.first().street)
            assertEquals(expected.addresses.first().zipcode, subject.addresses.first().zipcode)
            assertEquals(expected.addresses.first().neighborhood, subject.addresses.first().neighborhood)
            assertNotNull(subject.addresses.first().complement)
            assertEquals(expected.addresses.first().complement, subject.addresses.first().complement)
            assertEquals(expected.addresses.first().number, subject.addresses.first().number)
            assertEquals(expected.addresses.first().addressType, subject.addresses.first().addressType)


            assertNull(subject.naturalness)

            assertEquals(expected.mainDocument.mainDocumentType, subject.mainDocument.mainDocumentType)
            assertEquals(expected.mainDocument.code, subject.mainDocument.code)
        }
    }

    @Nested
    inner class `to domain` {
        @Test
        fun `should create domain Customer from CustomerDTO with complement`() {
            val expected = CustomerDTOObjectMother.build()
            val subject = expected.toDomain()

            assertEquals(expected.name, subject.name)
            assertEquals(expected.email, subject.email)
            assertNotNull(subject.jobTitle)
            assertEquals(expected.jobTitle, subject.jobTitle)
            assertEquals(expected.civilStatus, subject.civilStatus)
            assertEquals(expected.birthdate, subject.birthdate)
            assertNotNull(subject.motherFullName)
            assertEquals(expected.motherFullName, subject.motherFullName)
            assertNotNull(subject.fatherFullName)
            assertEquals(expected.fatherFullName, subject.fatherFullName)
            assertEquals(expected.politicallyExposed, subject.politicallyExposed)

            assertEquals(expected.phones.first().type, subject.phones.first().type)
            assertEquals(expected.phones.first().number, subject.phones.first().number)

            assertEquals(expected.addresses.first().city, subject.addresses.first().city)
            assertEquals(expected.addresses.first().state, subject.addresses.first().state)
            assertEquals(expected.addresses.first().street, subject.addresses.first().street)
            assertEquals(expected.addresses.first().zipcode, subject.addresses.first().zipcode)
            assertEquals(expected.addresses.first().neighborhood, subject.addresses.first().neighborhood)
            assertNotNull(subject.addresses.first().complement)
            assertEquals(expected.addresses.first().complement, subject.addresses.first().complement)
            assertEquals(expected.addresses.first().number, subject.addresses.first().number)
            assertEquals(expected.addresses.first().addressType, subject.addresses.first().addressType)


            assertNotNull(subject.naturalness!!.cityOfBirth)
            assertEquals(expected.naturalness!!.cityOfBirth, subject.naturalness!!.cityOfBirth)
            assertNotNull(subject.naturalness!!.stateOfBirth)
            assertEquals(expected.naturalness!!.stateOfBirth, subject.naturalness!!.stateOfBirth)
            assertNotNull(subject.naturalness!!.countryOfBirth)
            assertEquals(expected.naturalness!!.countryOfBirth, subject.naturalness!!.countryOfBirth)
            assertEquals(expected.naturalness!!.nationality, subject.naturalness!!.nationality)

            assertEquals(expected.mainDocument.mainDocumentType, subject.mainDocument.mainDocumentType)
            assertEquals(expected.mainDocument.code, subject.mainDocument.code)
        }

        @Test
        fun `should create domain Customer from CustomerDTO without complement`() {
            val expected = CustomerDTOObjectMother.build(
                jobTitle = null,
                motherFullName = null,
                fatherFullName = null,
                naturalness = null
            )
            val subject = expected.toDomain()

            assertEquals(expected.name, subject.name)
            assertEquals(expected.email, subject.email)
            assertNull(subject.jobTitle)
            assertEquals(expected.civilStatus, subject.civilStatus)
            assertEquals(expected.birthdate, subject.birthdate)
            assertNull(subject.motherFullName)
            assertNull(subject.fatherFullName)
            assertEquals(expected.politicallyExposed, subject.politicallyExposed)

            assertEquals(expected.phones.first().type, subject.phones.first().type)
            assertEquals(expected.phones.first().number, subject.phones.first().number)

            assertEquals(expected.addresses.first().city, subject.addresses.first().city)
            assertEquals(expected.addresses.first().state, subject.addresses.first().state)
            assertEquals(expected.addresses.first().street, subject.addresses.first().street)
            assertEquals(expected.addresses.first().zipcode, subject.addresses.first().zipcode)
            assertEquals(expected.addresses.first().neighborhood, subject.addresses.first().neighborhood)
            assertNotNull(subject.addresses.first().complement)
            assertEquals(expected.addresses.first().complement, subject.addresses.first().complement)
            assertEquals(expected.addresses.first().number, subject.addresses.first().number)
            assertEquals(expected.addresses.first().addressType, subject.addresses.first().addressType)


            assertNull(subject.naturalness)

            assertEquals(expected.mainDocument.mainDocumentType, subject.mainDocument.mainDocumentType)
            assertEquals(expected.mainDocument.code, subject.mainDocument.code)
        }
    }
}
