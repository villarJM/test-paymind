package com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class TransactionDataDto(
    @SerializedName("content") val content: List<ContentDto>,
    @SerializedName("pageable") val pageable: PageableDto,
    @SerializedName("totalPages") val totalPages: Long,
    @SerializedName("totalElements") val totalElements: Long,
    @SerializedName("last") val last: Boolean,
    @SerializedName("size") val size: Long,
    @SerializedName("number") val number: Long,
    @SerializedName("sort") val sort: Sort2Dto,
    @SerializedName("numberOfElements") val numberOfElements: Long,
    @SerializedName("first") val first: Boolean,
    @SerializedName("empty") val empty: Boolean,
)

data class ContentDto(
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("createdBy") val createdBy: Long,
    @SerializedName("updatedAt") val updatedAt: Long,
    @SerializedName("updatedBy") val updatedBy: Long,
    @SerializedName("id") val id: String,
    @SerializedName("operation") val operation: String,
    @SerializedName("aid") val aid: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("grossTotal") val grossTotal: Double,
    @SerializedName("subTotal") val subTotal: Double,
    @SerializedName("entryMode") val entryMode: String,
    @SerializedName("arqc") val arqc: String,
    @SerializedName("authCode") val authCode: String?,
    @SerializedName("cardNumber") val cardNumber: String,
    @SerializedName("cardIssuerBank") val cardIssuerBank: String,
    @SerializedName("cardIssuerBrand") val cardIssuerBrand: String,
    @SerializedName("cardType") val cardType: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("date") val date: Long,
    @SerializedName("responseMessage") val responseMessage: String,
    @SerializedName("subMerchantId") val subMerchantId: Long,
    @SerializedName("merchantId") val merchantId: Long,
    @SerializedName("posId") val posId: String,
    @SerializedName("cashierId") val cashierId: String,
    @SerializedName("userId") val userId: Long,
    @SerializedName("providerMerchantId") val providerMerchantId: String,
    @SerializedName("originalNumber") val originalNumber: String,
    @SerializedName("posName") val posName: String,
    @SerializedName("saleType") val saleType: String,
    @SerializedName("clientReference") val clientReference: String,
    @SerializedName("status") val status: String,
    @SerializedName("commission") val commission: Double,
    @SerializedName("commissionRate") val commissionRate: Double,
    @SerializedName("commissionMsiRate") val commissionMsiRate: Any?,
    @SerializedName("iva") val iva: Double,
    @SerializedName("terminalId") val terminalId: String,
    @SerializedName("total") val total: Double,
    @SerializedName("bin") val bin: String,
    @SerializedName("cardCountry") val cardCountry: String,
    @SerializedName("commissionMsi") val commissionMsi: Any?,
    @SerializedName("subMerchant") val subMerchant: SubMerchantDto,
    @SerializedName("pos") val pos: PosDto,
    @SerializedName("user") val user: UserDto,
    @SerializedName("channel") val channel: Any?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("tip") val tip: Any?,
    @SerializedName("checkOutDate") val checkOutDate: Any?,
    @SerializedName("additionalData") val additionalData: Any?,
)

data class SubMerchantDto(
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("createdBy") val createdBy: Long,
    @SerializedName("updatedAt") val updatedAt: Long,
    @SerializedName("updatedBy") val updatedBy: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("merchant") val merchant: MerchantDto,
    @SerializedName("merchantId") val merchantId: Long,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: Address2Dto,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("activated") val activated: Boolean,
    @SerializedName("idMit") val idMit: Any?,
    @SerializedName("contactName") val contactName: String,
    @SerializedName("contactEmail") val contactEmail: String,
    @SerializedName("allowWhatsapp") val allowWhatsapp: Boolean,
    @SerializedName("billingUr") val billingUr: String,
    @SerializedName("maxAmount") val maxAmount: Double,
    @SerializedName("billable") val billable: Boolean,
)

data class MerchantDto(
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("createdBy") val createdBy: Long,
    @SerializedName("updatedAt") val updatedAt: Long,
    @SerializedName("updatedBy") val updatedBy: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("amexId") val amexId: Any?,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("activated") val activated: Boolean,
    @SerializedName("webhook") val webhook: String,
    @SerializedName("webhookEnabled") val webhookEnabled: Boolean,
    @SerializedName("token") val token: Any?,
    @SerializedName("validateAmount") val validateAmount: Boolean,
    @SerializedName("maxAmount") val maxAmount: Any?,
    @SerializedName("urlLogo") val urlLogo: String,
    @SerializedName("clientId") val clientId: Any?,
    @SerializedName("address") val address: AddressDto,
    @SerializedName("fiscalAccount") val fiscalAccount: FiscalAccountDto,
)

data class AddressDto(
    @SerializedName("street") val street: String,
    @SerializedName("state") val state: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("comment") val comment: Any?,
    @SerializedName("exteriorNumber") val exteriorNumber: Any?,
    @SerializedName("interiorNumber") val interiorNumber: Any?,
    @SerializedName("neighborhoods") val neighborhoods: String,
    @SerializedName("districts") val districts: String,
    @SerializedName("locationLatitude") val locationLatitude: Double,
    @SerializedName("locationLongitude") val locationLongitude: Double,
    @SerializedName("zipCode") val zipCode: String,
    @SerializedName("neighborhood") val neighborhood: Any?,
    @SerializedName("district") val district: Any?,
    @SerializedName("stateMx") val stateMx: Any?,
    @SerializedName("merchantId") val merchantId: Long,
)

data class FiscalAccountDto(
    @SerializedName("merchantId") val merchantId: Long,
    @SerializedName("account") val account: String,
    @SerializedName("ownerName") val ownerName: String,
    @SerializedName("merchantCategoryCode") val merchantCategoryCode: Long,
    @SerializedName("ownerLastName") val ownerLastName: String,
    @SerializedName("cityName") val cityName: String,
    @SerializedName("stateName") val stateName: String,
    @SerializedName("streetAddress") val streetAddress: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("ownerDateOfBirth") val ownerDateOfBirth: String,
    @SerializedName("businessName") val businessName: String,
    @SerializedName("ownerMail") val ownerMail: String,
    @SerializedName("merchantCategoryAmex") val merchantCategoryAmex: Long,
    @SerializedName("accountBank") val accountBank: AccountBankDto,
)

data class AccountBankDto(
    @SerializedName("merchantId") val merchantId: Long,
    @SerializedName("account") val account: String,
    @SerializedName("bankName") val bankName: String,
)

data class Address2Dto(
    @SerializedName("street") val street: String,
    @SerializedName("state") val state: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("comment") val comment: String,
    @SerializedName("exteriorNumber") val exteriorNumber: String,
    @SerializedName("interiorNumber") val interiorNumber: String,
    @SerializedName("neighborhoods") val neighborhoods: String,
    @SerializedName("districts") val districts: String,
    @SerializedName("locationLatitude") val locationLatitude: Double,
    @SerializedName("locationLongitude") val locationLongitude: Double,
    @SerializedName("zipCode") val zipCode: String,
    @SerializedName("neighborhood") val neighborhood: NeighborhoodDto,
    @SerializedName("district") val district: District2Dto,
    @SerializedName("stateMx") val stateMx: StateMx2Dto,
    @SerializedName("subMerchantId") val subMerchantId: Long,
)

data class NeighborhoodDto(
    @SerializedName("id") val id: Long,
    @SerializedName("neighborhoodId") val neighborhoodId: Long,
    @SerializedName("neighborhoodName") val neighborhoodName: String,
    @SerializedName("district") val district: DistrictDto,
    @SerializedName("stateMx") val stateMx: StateMxDto,
)

data class DistrictDto(
    @SerializedName("id") val id: Long,
    @SerializedName("districtId") val districtId: Long,
    @SerializedName("districtName") val districtName: String,
)

data class StateMxDto(
    @SerializedName("stateName") val stateName: String,
    @SerializedName("id") val id: Long,
)

data class District2Dto(
    @SerializedName("id") val id: Long,
    @SerializedName("districtId") val districtId: Long,
    @SerializedName("districtName") val districtName: String,
)

data class StateMx2Dto(
    @SerializedName("stateName") val stateName: String,
    @SerializedName("id") val id: Long,
)

data class PosDto(
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("createdBy") val createdBy: Long,
    @SerializedName("updatedAt") val updatedAt: Long,
    @SerializedName("updatedBy") val updatedBy: Long,
    @SerializedName("id") val id: String,
    @SerializedName("subMerchantId") val subMerchantId: Long,
    @SerializedName("modelChip") val modelChip: String,
    @SerializedName("numberChip") val numberChip: String,
    @SerializedName("chipBrand") val chipBrand: String,
    @SerializedName("name") val name: String,
    @SerializedName("active") val active: Boolean,
    @SerializedName("encryptionMethod") val encryptionMethod: String?,
)

data class UserDto(
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("createdBy") val createdBy: Long,
    @SerializedName("updatedAt") val updatedAt: Long,
    @SerializedName("updatedBy") val updatedBy: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String,
    @SerializedName("attempts") val attempts: Long,
    @SerializedName("status") val status: String,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("activated") val activated: Boolean,
    @SerializedName("roleId") val roleId: Long,
)

data class PageableDto(
    @SerializedName("sort") val sort: SortDto,
    @SerializedName("offset") val offset: Long,
    @SerializedName("pageNumber") val pageNumber: Long,
    @SerializedName("pageSize") val pageSize: Long,
    @SerializedName("paged") val paged: Boolean,
    @SerializedName("unpaged") val unpaged: Boolean,
)

data class SortDto(
    @SerializedName("empty") val empty: Boolean,
    @SerializedName("unsorted") val unsorted: Boolean,
    @SerializedName("sorted") val sorted: Boolean,
)

data class Sort2Dto(
    @SerializedName("empty") val empty: Boolean,
    @SerializedName("unsorted") val unsorted: Boolean,
    @SerializedName("sorted") val sorted: Boolean,
)
