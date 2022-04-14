package inu.jinsol.hug.data

data class ShelterItem(
    val id: Int,
    val name: String,
    val sex: Boolean?,       // null 가능
    val age: String,
    val type: String,
    val period: String,
    val state: String,
    val city: String,
    val street: String,
    val lat: Double,
    val lng: Double,
    val heads: Int?,        // null 가능
    val phone: String?,      //
    val mail: String?,       //
    val homepage: String?,   //
    val kakao: String?,      //
    val facebook: String?,   //
    val insta: String?       //
)