package com.gadsc.customers.objectmothers.model

import com.gadsc.customers.model.*
import java.time.LocalDate
import java.util.UUID

object CustomerObjectMother {
    fun build(
        id: UUID? = null,
        name: String = "Customer Name",
        email: String = "customer_email@email.com",
        jobTitle: String? = "Customer Job Title",
        civilStatus: CivilStatus = CivilStatus.SINGLE,
        birthdate: LocalDate = LocalDate.of(1995, 5, 21),
        motherFullName: String? = "Customer Mother Name",
        fatherFullName: String? = "Customer Father Name",
        politicallyExposed: Boolean = false,
        phones: Set<Phone> = setOf(PhoneObjectMother.build()),
        addresses: Set<Address> = setOf(AddressObjectMother.build()),
        naturalness: Naturalness? = NaturalnessObjectMother.build(),
        mainDocument: MainDocument = MainDocumentObjectMother.build()
    ): Customer = Customer(
        id = id,
        name = name,
        email = email,
        jobTitle = jobTitle,
        civilStatus = civilStatus,
        birthdate = birthdate,
        motherFullName = motherFullName,
        fatherFullName = fatherFullName,
        politicallyExposed = politicallyExposed,
        phones = phones,
        addresses = addresses,
        naturalness = naturalness,
        mainDocument = mainDocument
    )
}
