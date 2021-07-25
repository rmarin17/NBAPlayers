package com.rmarin17.nbaplayers.data.network.models

import com.google.gson.annotations.SerializedName
import com.rmarin17.nbaplayers.ui.models.TeamUiModel

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
) {

    fun transformToTeamUiModel(): TeamUiModel {
        return TeamUiModel(
            id = id,
            name = fullName,
            city = city,
            conference = conference,
            abbreviation = abbreviation
        )
    }
}
