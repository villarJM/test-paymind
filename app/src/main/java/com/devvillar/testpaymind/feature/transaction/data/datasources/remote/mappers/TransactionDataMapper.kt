package com.devvillar.testpaymind.feature.transaction.data.datasources.remote.mappers

import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.AccountBankDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.Address2Dto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.AddressDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.ContentDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.District2Dto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.DistrictDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.FiscalAccountDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.MerchantDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.NeighborhoodDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.PageableDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.PosDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.Sort2Dto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.SortDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.StateMx2Dto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.StateMxDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.SubMerchantDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.TransactionDataDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.UserDto
import com.devvillar.testpaymind.feature.transaction.domain.models.*

fun TransactionDataDto.toDomain(): TransactionData {
    return TransactionData(
        content = content.map { it.toDomain() },
        pageable = pageable.toDomain(),
        totalPages = totalPages,
        totalElements = totalElements,
        last = last,
        size = size,
        number = number,
        sort = sort.toDomain(),
        numberOfElements = numberOfElements,
        first = first,
        empty = empty
    )
}

fun ContentDto.toDomain(): Content {
    return Content(
        createdAt = createdAt,
        createdBy = createdBy,
        updatedAt = updatedAt,
        updatedBy = updatedBy,
        id = id,
        operation = operation,
        aid = aid,
        amount = amount,
        grossTotal = grossTotal,
        subTotal = subTotal,
        entryMode = entryMode,
        arqc = arqc,
        authCode = authCode,
        cardNumber = cardNumber,
        cardIssuerBank = cardIssuerBank,
        cardIssuerBrand = cardIssuerBrand,
        cardType = cardType,
        currency = currency,
        date = date,
        responseMessage = responseMessage,
        subMerchantId = subMerchantId,
        merchantId = merchantId,
        posId = posId,
        cashierId = cashierId,
        userId = userId,
        providerMerchantId = providerMerchantId,
        originalNumber = originalNumber,
        posName = posName,
        saleType = saleType,
        clientReference = clientReference,
        status = status,
        commission = commission,
        commissionRate = commissionRate,
        commissionMsiRate = commissionMsiRate,
        iva = iva,
        terminalId = terminalId,
        total = total,
        bin = bin,
        cardCountry = cardCountry,
        commissionMsi = commissionMsi,
        subMerchant = subMerchant.toDomain(),
        pos = pos.toDomain(),
        user = user.toDomain(),
        channel = channel,
        phone = phone,
        tip = tip,
        checkOutDate = checkOutDate,
        additionalData = additionalData
    )
}

fun SubMerchantDto.toDomain(): SubMerchant {
    return SubMerchant(
        createdAt = createdAt,
        createdBy = createdBy,
        updatedAt = updatedAt,
        updatedBy = updatedBy,
        id = id,
        merchant = merchant.toDomain(),
        merchantId = merchantId,
        name = name,
        address = address.toDomain(),
        enabled = enabled,
        activated = activated,
        idMit = idMit,
        contactName = contactName,
        contactEmail = contactEmail,
        allowWhatsapp = allowWhatsapp,
        billingUr = billingUr,
        maxAmount = maxAmount,
        billable = billable
    )
}

fun MerchantDto.toDomain(): Merchant {
    return Merchant(
        createdAt = createdAt,
        createdBy = createdBy,
        updatedAt = updatedAt,
        updatedBy = updatedBy,
        id = id,
        name = name,
        amexId = amexId,
        enabled = enabled,
        activated = activated,
        webhook = webhook,
        webhookEnabled = webhookEnabled,
        token = token,
        validateAmount = validateAmount,
        maxAmount = maxAmount,
        urlLogo = urlLogo,
        clientId = clientId,
        address = address.toDomain(),
        fiscalAccount = fiscalAccount.toDomain()
    )
}

fun AddressDto.toDomain(): Address {
    return Address(
        street = street,
        state = state,
        phone = phone,
        comment = comment,
        exteriorNumber = exteriorNumber,
        interiorNumber = interiorNumber,
        neighborhoods = neighborhoods,
        districts = districts,
        locationLatitude = locationLatitude,
        locationLongitude = locationLongitude,
        zipCode = zipCode,
        neighborhood = neighborhood,
        district = district,
        stateMx = stateMx,
        merchantId = merchantId
    )
}

fun FiscalAccountDto.toDomain(): FiscalAccount {
    return FiscalAccount(
        merchantId = merchantId,
        account = account,
        ownerName = ownerName,
        merchantCategoryCode = merchantCategoryCode,
        ownerLastName = ownerLastName,
        cityName = cityName,
        stateName = stateName,
        streetAddress = streetAddress,
        postalCode = postalCode,
        ownerDateOfBirth = ownerDateOfBirth,
        businessName = businessName,
        ownerMail = ownerMail,
        merchantCategoryAmex = merchantCategoryAmex,
        accountBank = accountBank.toDomain()
    )
}

fun AccountBankDto.toDomain(): AccountBank {
    return AccountBank(
        merchantId = merchantId,
        account = account,
        bankName = bankName
    )
}

fun Address2Dto.toDomain(): Address2 {
    return Address2(
        street = street,
        state = state,
        phone = phone,
        comment = comment,
        exteriorNumber = exteriorNumber,
        interiorNumber = interiorNumber,
        neighborhoods = neighborhoods,
        districts = districts,
        locationLatitude = locationLatitude,
        locationLongitude = locationLongitude,
        zipCode = zipCode,
        neighborhood = neighborhood.toDomain(),
        district = district.toDomain(),
        stateMx = stateMx.toDomain(),
        subMerchantId = subMerchantId
    )
}

fun NeighborhoodDto.toDomain(): Neighborhood {
    return Neighborhood(
        id = id,
        neighborhoodId = neighborhoodId,
        neighborhoodName = neighborhoodName,
        district = district.toDomain(),
        stateMx = stateMx.toDomain()
    )
}

fun DistrictDto.toDomain(): District {
    return District(
        id = id,
        districtId = districtId,
        districtName = districtName
    )
}

fun StateMxDto.toDomain(): StateMx {
    return StateMx(
        stateName = stateName,
        id = id
    )
}

fun District2Dto.toDomain(): District2 {
    return District2(
        id = id,
        districtId = districtId,
        districtName = districtName
    )
}

fun StateMx2Dto.toDomain(): StateMx2 {
    return StateMx2(
        stateName = stateName,
        id = id
    )
}

fun PosDto.toDomain(): Pos {
    return Pos(
        createdAt = createdAt,
        createdBy = createdBy,
        updatedAt = updatedAt,
        updatedBy = updatedBy,
        id = id,
        subMerchantId = subMerchantId,
        modelChip = modelChip,
        numberChip = numberChip,
        chipBrand = chipBrand,
        name = name,
        active = active,
        encryptionMethod = encryptionMethod
    )
}

fun UserDto.toDomain(): User {
    return User(
        createdAt = createdAt,
        createdBy = createdBy,
        updatedAt = updatedAt,
        updatedBy = updatedBy,
        id = id,
        name = name,
        username = username,
        email = email,
        role = role,
        attempts = attempts,
        status = status,
        enabled = enabled,
        activated = activated,
        roleId = roleId
    )
}

fun PageableDto.toDomain(): Pageable {
    return Pageable(
        sort = sort.toDomain(),
        offset = offset,
        pageNumber = pageNumber,
        pageSize = pageSize,
        paged = paged,
        unpaged = unpaged
    )
}

fun SortDto.toDomain(): Sort {
    return Sort(
        empty = empty,
        unsorted = unsorted,
        sorted = sorted
    )
}

fun Sort2Dto.toDomain(): Sort2 {
    return Sort2(
        empty = empty,
        unsorted = unsorted,
        sorted = sorted
    )
}

