package com.rmarin17.nbaplayers.data.network.models

import com.google.gson.annotations.SerializedName
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel

/**
 * Data class to handle the player response.
 */
data class PlayerResponseModel(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("height_feet") val heightFeet: Int,
    @SerializedName("height_inches") val heightInches: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("position") val position: String,
    @SerializedName("team") val team: TeamResponseModel,
    @SerializedName("weight_pounds") val weightPounds: Int
) {

    fun transformToPlayerUiModel(): PlayerUiModel {
        return PlayerUiModel(
            id = id,
            name = "$firstName $lastName",
            position = position,
            weight = weightPounds.toString(),
            height = heightInches.toString(),
            team = team.transformToTeamUiModel()
        )
    }
}
