package com.devvillar.testpaymind.feature.transaction.domain.models

class TransactionData(
    val content: List<Content>,
    val pageable: Pageable,
    val totalPages: Long,
    val totalElements: Long,
    val last: Boolean,
    val size: Long,
    val number: Long,
    val sort: Sort2,
    val numberOfElements: Long,
    val first: Boolean,
    val empty: Boolean,
)

data class Content(
    val createdAt: Long,
    val createdBy: Long,
    val updatedAt: Long,
    val updatedBy: Long,
    val id: String,
    val operation: String,
    val aid: String,
    val amount: Double,
    val grossTotal: Double,
    val subTotal: Double,
    val entryMode: String,
    val arqc: String,
    val authCode: String?,
    val cardNumber: String,
    val cardIssuerBank: String,
    val cardIssuerBrand: String,
    val cardType: String,
    val currency: String,
    val date: Long,
    val responseMessage: String,
    val subMerchantId: Long,
    val merchantId: Long,
    val posId: String,
    val cashierId: String,
    val userId: Long,
    val providerMerchantId: String,
    val originalNumber: String,
    val posName: String,
    val saleType: String,
    val clientReference: String,
    val status: String,
    val commission: Double,
    val commissionRate: Double,
    val commissionMsiRate: Any?,
    val iva: Double,
    val terminalId: String,
    val total: Double,
    val bin: String,
    val cardCountry: String,
    val commissionMsi: Any?,
    val subMerchant: SubMerchant,
    val pos: Pos,
    val user: User,
    val channel: Any?,
    val phone: String?,
    val tip: Any?,
    val checkOutDate: Any?,
    val additionalData: Any?,
)

data class SubMerchant(
    val createdAt: Long,
    val createdBy: Long,
    val updatedAt: Long,
    val updatedBy: Long,
    val id: Long,
    val merchant: Merchant,
    val merchantId: Long,
    val name: String,
    val address: Address2,
    val enabled: Boolean,
    val activated: Boolean,
    val idMit: Any?,
    val contactName: String,
    val contactEmail: String,
    val allowWhatsapp: Boolean,
    val billingUr: String,
    val maxAmount: Double,
    val billable: Boolean,
)

data class Merchant(
    val createdAt: Long,
    val createdBy: Long,
    val updatedAt: Long,
    val updatedBy: Long,
    val id: Long,
    val name: String,
    val amexId: Any?,
    val enabled: Boolean,
    val activated: Boolean,
    val webhook: String,
    val webhookEnabled: Boolean,
    val token: Any?,
    val validateAmount: Boolean,
    val maxAmount: Any?,
    val urlLogo: String,
    val clientId: Any?,
    val address: Address,
    val fiscalAccount: FiscalAccount,
)

data class Address(
    val street: String,
    val state: String,
    val phone: String,
    val comment: Any?,
    val exteriorNumber: Any?,
    val interiorNumber: Any?,
    val neighborhoods: String,
    val districts: String,
    val locationLatitude: Double,
    val locationLongitude: Double,
    val zipCode: String,
    val neighborhood: Any?,
    val district: Any?,
    val stateMx: Any?,
    val merchantId: Long,
)

data class FiscalAccount(
    val merchantId: Long,
    val account: String,
    val ownerName: String,
    val merchantCategoryCode: Long,
    val ownerLastName: String,
    val cityName: String,
    val stateName: String,
    val streetAddress: String,
    val postalCode: String,
    val ownerDateOfBirth: String,
    val businessName: String,
    val ownerMail: String,
    val merchantCategoryAmex: Long,
    val accountBank: AccountBank,
)

data class AccountBank(
    val merchantId: Long,
    val account: String,
    val bankName: String,
)

data class Address2(
    val street: String,
    val state: String,
    val phone: String,
    val comment: String,
    val exteriorNumber: String,
    val interiorNumber: String,
    val neighborhoods: String,
    val districts: String,
    val locationLatitude: Double,
    val locationLongitude: Double,
    val zipCode: String,
    val neighborhood: Neighborhood,
    val district: District2,
    val stateMx: StateMx2,
    val subMerchantId: Long,
)

data class Neighborhood(
    val id: Long,
    val neighborhoodId: Long,
    val neighborhoodName: String,
    val district: District,
    val stateMx: StateMx,
)

data class District(
    val id: Long,
    val districtId: Long,
    val districtName: String,
)

data class StateMx(
    val stateName: String,
    val id: Long,
)

data class District2(
    val id: Long,
    val districtId: Long,
    val districtName: String,
)

data class StateMx2(
    val stateName: String,
    val id: Long,
)

data class Pos(
    val createdAt: Long,
    val createdBy: Long,
    val updatedAt: Long,
    val updatedBy: Long,
    val id: String,
    val subMerchantId: Long,
    val modelChip: String,
    val numberChip: String,
    val chipBrand: String,
    val name: String,
    val active: Boolean,
    val encryptionMethod: String?,
)

data class User(
    val createdAt: Long,
    val createdBy: Long,
    val updatedAt: Long,
    val updatedBy: Long,
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val role: String,
    val attempts: Long,
    val status: String,
    val enabled: Boolean,
    val activated: Boolean,
    val roleId: Long,
)

data class Pageable(
    val sort: Sort,
    val offset: Long,
    val pageNumber: Long,
    val pageSize: Long,
    val paged: Boolean,
    val unpaged: Boolean,
)

data class Sort(
    val empty: Boolean,
    val unsorted: Boolean,
    val sorted: Boolean,
)

data class Sort2(
    val empty: Boolean,
    val unsorted: Boolean,
    val sorted: Boolean,
)