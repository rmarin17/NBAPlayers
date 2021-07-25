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
    val weight: String,
    val height: String
) : Parcelable
