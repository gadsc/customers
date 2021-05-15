package com.gadsc.customers.objectmothers.dto

import com.gadsc.customers.dto.*
import com.gadsc.customers.model.CivilStatus
import java.time.LocalDate

object CustomerDTOObjectMother {
    fun build(
        name: String = "Customer Name",
        email: String = "customer_email@email.com",
        jobTitle: String? = "Customer Job Title",
        civilStatus: CivilStatus = CivilStatus.SINGLE,
        birthdate: LocalDate = LocalDate.of(1995, 5, 21),
        motherFullName: String? = "Customer Mother Name",
        fatherFullName: String? = "Customer Father Name",
        politicallyExposed: Boolean = false,
        phones: Set<PhoneDTO> = setOf(PhoneDTOObjectMother.build()),
        addresses: Set<AddressDTO> = setOf(AddressDTOObjectMother.build()),
        naturalness: NaturalnessDTO? = NaturalnessDTOObjectMother.build(),
        mainDocument: MainDocumentDTO = MainDocumentDTOObjectMother.build()
    ): CustomerDTO = CustomerDTO(
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
