package com.devvillar.testpaymind.feature.transaction.data.datasources.remote.mappers

import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.*
import com.devvillar.testpaymind.feature.transaction.domain.models.*

// TransactionDataDto -> TransactionData
fun TransactionDataDto?.toDomain(): TransactionData {
    return TransactionData(
        content = this?.content.orEmpty().map { it.toDomain() },
        pageable = this?.pageable.toDomain(),
        totalPages = this?.totalPages ?: 0L,
        totalElements = this?.totalElements ?: 0L,
        last = this?.last ?: false,
        size = this?.size ?: 0L,
        number = this?.number ?: 0L,
        sort = this?.sort.toDomain(),
        numberOfElements = this?.numberOfElements ?: 0L,
        first = this?.first ?: false,
        empty = this?.empty ?: true
    )
}

// ContentDto -> Content
fun ContentDto?.toDomain(): Content {
    return Content(
        createdAt = this?.createdAt ?: 0L,
        createdBy = this?.createdBy ?: 0L,
        updatedAt = this?.updatedAt ?: 0L,
        updatedBy = this?.updatedBy ?: 0L,
        id = this?.id ?: "",
        operation = this?.operation ?: "",
        aid = this?.aid ?: "",
        amount = this?.amount ?: 0.0,
        grossTotal = this?.grossTotal ?: 0.0,
        subTotal = this?.subTotal ?: 0.0,
        entryMode = this?.entryMode ?: "",
        arqc = this?.arqc ?: "",
        authCode = this?.authCode,
        cardNumber = this?.cardNumber ?: "",
        cardIssuerBank = this?.cardIssuerBank ?: "",
        cardIssuerBrand = this?.cardIssuerBrand ?: "",
        cardType = this?.cardType ?: "",
        currency = this?.currency ?: "",
        date = this?.date ?: 0L,
        responseMessage = this?.responseMessage ?: "",
        subMerchantId = this?.subMerchantId ?: 0L,
        merchantId = this?.merchantId ?: 0L,
        posId = this?.posId ?: "",
        cashierId = this?.cashierId ?: "",
        userId = this?.userId ?: 0L,
        providerMerchantId = this?.providerMerchantId ?: "",
        originalNumber = this?.originalNumber ?: "",
        posName = this?.posName ?: "",
        saleType = this?.saleType ?: "",
        clientReference = this?.clientReference ?: "",
        status = this?.status ?: "",
        commission = this?.commission ?: 0.0,
        commissionRate = this?.commissionRate ?: 0.0,
        commissionMsiRate = this?.commissionMsiRate,
        iva = this?.iva ?: 0.0,
        terminalId = this?.terminalId ?: "",
        total = this?.total ?: 0.0,
        bin = this?.bin ?: "",
        cardCountry = this?.cardCountry ?: "",
        commissionMsi = this?.commissionMsi,
        subMerchant = this?.subMerchant.toDomain(),
        pos = this?.pos.toDomain(),
        user = this?.user.toDomain(),
        channel = this?.channel,
        phone = this?.phone,
        tip = this?.tip,
        checkOutDate = this?.checkOutDate,
        additionalData = this?.additionalData
    )
}

fun SubMerchantDto?.toDomain(): SubMerchant {
    return SubMerchant(
        createdAt = this?.createdAt ?: 0L,
        createdBy = this?.createdBy ?: 0L,
        updatedAt = this?.updatedAt ?: 0L,
        updatedBy = this?.updatedBy ?: 0L,
        id = this?.id ?: 0L,
        merchant = this?.merchant.toDomain(),
        merchantId = this?.merchantId ?: 0L,
        name = this?.name ?: "",
        address = this?.address.toDomain(),
        enabled = this?.enabled ?: false,
        activated = this?.activated ?: false,
        idMit = this?.idMit,
        contactName = this?.contactName ?: "",
        contactEmail = this?.contactEmail ?: "",
        allowWhatsapp = this?.allowWhatsapp ?: false,
        billingUr = this?.billingUr ?: "",
        maxAmount = this?.maxAmount ?: 0.0,
        billable = this?.billable ?: false
    )
}

fun MerchantDto?.toDomain(): Merchant {
    return Merchant(
        createdAt = this?.createdAt ?: 0L,
        createdBy = this?.createdBy ?: 0L,
        updatedAt = this?.updatedAt ?: 0L,
        updatedBy = this?.updatedBy ?: 0L,
        id = this?.id ?: 0L,
        name = this?.name ?: "",
        amexId = this?.amexId,
        enabled = this?.enabled ?: false,
        activated = this?.activated ?: false,
        webhook = this?.webhook ?: "",
        webhookEnabled = this?.webhookEnabled ?: false,
        token = this?.token,
        validateAmount = this?.validateAmount ?: false,
        maxAmount = this?.maxAmount,
        urlLogo = this?.urlLogo ?: "",
        clientId = this?.clientId,
        address = this?.address.toDomain(),
        fiscalAccount = this?.fiscalAccount.toDomain()
    )
}

fun AddressDto?.toDomain(): Address {
    return Address(
        street = this?.street ?: "",
        state = this?.state ?: "",
        phone = this?.phone ?: "",
        comment = this?.comment,
        exteriorNumber = this?.exteriorNumber,
        interiorNumber = this?.interiorNumber,
        neighborhoods = this?.neighborhoods ?: "",
        districts = this?.districts ?: "",
        locationLatitude = this?.locationLatitude ?: 0.0,
        locationLongitude = this?.locationLongitude ?: 0.0,
        zipCode = this?.zipCode ?: "",
        neighborhood = this?.neighborhood,
        district = this?.district,
        stateMx = this?.stateMx,
        merchantId = this?.merchantId ?: 0L
    )
}

fun FiscalAccountDto?.toDomain(): FiscalAccount {
    return FiscalAccount(
        merchantId = this?.merchantId ?: 0L,
        account = this?.account ?: "",
        ownerName = this?.ownerName ?: "",
        merchantCategoryCode = this?.merchantCategoryCode ?: 0L,
        ownerLastName = this?.ownerLastName ?: "",
        cityName = this?.cityName ?: "",
        stateName = this?.stateName ?: "",
        streetAddress = this?.streetAddress ?: "",
        postalCode = this?.postalCode ?: "",
        ownerDateOfBirth = this?.ownerDateOfBirth ?: "",
        businessName = this?.businessName ?: "",
        ownerMail = this?.ownerMail ?: "",
        merchantCategoryAmex = this?.merchantCategoryAmex ?: 0L,
        accountBank = this?.accountBank.toDomain()
    )
}

fun AccountBankDto?.toDomain(): AccountBank {
    return AccountBank(
        merchantId = this?.merchantId ?: 0L,
        account = this?.account ?: "",
        bankName = this?.bankName ?: ""
    )
}

fun Address2Dto?.toDomain(): Address2 {
    return Address2(
        street = this?.street ?: "",
        state = this?.state ?: "",
        phone = this?.phone ?: "",
        comment = this?.comment ?: "",
        exteriorNumber = this?.exteriorNumber ?: "",
        interiorNumber = this?.interiorNumber ?: "",
        neighborhoods = this?.neighborhoods ?: "",
        districts = this?.districts ?: "",
        locationLatitude = this?.locationLatitude ?: 0.0,
        locationLongitude = this?.locationLongitude ?: 0.0,
        zipCode = this?.zipCode ?: "",
        neighborhood = this?.neighborhood.toDomain(),
        district = this?.district.toDomain(),
        stateMx = this?.stateMx.toDomain(),
        subMerchantId = this?.subMerchantId ?: 0L
    )
}

fun NeighborhoodDto?.toDomain(): Neighborhood {
    return Neighborhood(
        id = this?.id ?: 0L,
        neighborhoodId = this?.neighborhoodId ?: 0L,
        neighborhoodName = this?.neighborhoodName ?: "",
        district = this?.district.toDomain(),
        stateMx = this?.stateMx.toDomain()
    )
}

fun DistrictDto?.toDomain(): District {
    return District(
        id = this?.id ?: 0L,
        districtId = this?.districtId ?: 0L,
        districtName = this?.districtName ?: ""
    )
}

fun StateMxDto?.toDomain(): StateMx {
    return StateMx(
        stateName = this?.stateName ?: "",
        id = this?.id ?: 0L
    )
}

fun District2Dto?.toDomain(): District2 {
    return District2(
        id = this?.id ?: 0L,
        districtId = this?.districtId ?: 0L,
        districtName = this?.districtName ?: ""
    )
}

fun StateMx2Dto?.toDomain(): StateMx2 {
    return StateMx2(
        stateName = this?.stateName ?: "",
        id = this?.id ?: 0L
    )
}

fun PosDto?.toDomain(): Pos {
    return Pos(
        createdAt = this?.createdAt ?: 0L,
        createdBy = this?.createdBy ?: 0L,
        updatedAt = this?.updatedAt ?: 0L,
        updatedBy = this?.updatedBy ?: 0L,
        id = this?.id ?: "",
        subMerchantId = this?.subMerchantId ?: 0L,
        modelChip = this?.modelChip ?: "",
        numberChip = this?.numberChip ?: "",
        chipBrand = this?.chipBrand ?: "",
        name = this?.name ?: "",
        active = this?.active ?: false,
        encryptionMethod = this?.encryptionMethod
    )
}

fun UserDto?.toDomain(): User {
    return User(
        createdAt = this?.createdAt ?: 0L,
        createdBy = this?.createdBy ?: 0L,
        updatedAt = this?.updatedAt ?: 0L,
        updatedBy = this?.updatedBy ?: 0L,
        id = this?.id ?: 0L,
        name = this?.name ?: "",
        username = this?.username ?: "",
        email = this?.email ?: "",
        role = this?.role ?: "",
        attempts = this?.attempts ?: 0L,
        status = this?.status ?: "",
        enabled = this?.enabled ?: false,
        activated = this?.activated ?: false,
        roleId = this?.roleId ?: 0L
    )
}

fun PageableDto?.toDomain(): Pageable {
    return Pageable(
        sort = this?.sort.toDomain(),
        offset = this?.offset ?: 0L,
        pageNumber = this?.pageNumber ?: 0L,
        pageSize = this?.pageSize ?: 0L,
        paged = this?.paged ?: false,
        unpaged = this?.unpaged ?: false
    )
}

fun SortDto?.toDomain(): Sort {
    return Sort(
        empty = this?.empty ?: true,
        unsorted = this?.unsorted ?: true,
        sorted = this?.sorted ?: false
    )
}

fun Sort2Dto?.toDomain(): Sort2 {
    return Sort2(
        empty = this?.empty ?: true,
        unsorted = this?.unsorted ?: true,
        sorted = this?.sorted ?: false
    )
}
