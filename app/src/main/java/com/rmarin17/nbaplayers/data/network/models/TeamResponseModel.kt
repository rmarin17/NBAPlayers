package com.rmarin17.nbaplayers.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Data class to handle the team response.
 */
data class TeamResponseModel(
    @SerializedName("abbreviation") val abbreviation: String,
    @SerializedName("city") val city: String,
    @SerializedName("conference") val conference: String,
    @SerializedName("division") val division: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
