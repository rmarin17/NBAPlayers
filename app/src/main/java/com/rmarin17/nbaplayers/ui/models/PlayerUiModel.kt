package com.rmarin17.nbaplayers.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class to handle the player information to show in UI.
 */
@Parcelize
data class PlayerUiModel(
    val id: Int,
    val name: String,
    val team: TeamUiModel,
    val position: String,
    val weight: Int,
    val height: Int,
    // TODO - When services add image for players, please replace this value with it.
    val image: String = MOCK_PLAYER_IMAGE
) : Parcelable {

    companion object {
        // TODO - When services return image for players, please remove this workaround
        const val MOCK_PLAYER_IMAGE = "https://image.shutterstock.com/image-photo/8-nov-2019-beijing-china-260nw-1554266144.jpg"
    }
}
