package com.rmarin17.nbaplayers.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class to handle the team information to show in UI.
 */
@Parcelize
data class TeamUiModel(
    val id : Int,
    val city: String,
    val conference: String,
    val name: String,
    val abbreviation: String
): Parcelable
