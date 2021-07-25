package com.rmarin17.nbaplayers.data.network.models

import com.rmarin17.nbaplayers.ui.models.PlayerUiModel

/**
 * Data class to handle the data response.
 */
data class DataResponseModel(
    val data: List<PlayerResponseModel>
) {
    fun transformToListOfPlayersUiModel(): List<PlayerUiModel> {
        return data.map { it.transformToPlayerUiModel() }
    }
}