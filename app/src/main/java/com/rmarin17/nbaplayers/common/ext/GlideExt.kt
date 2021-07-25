package com.rmarin17.nbaplayers.common.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rmarin17.nbaplayers.R

/**
 * File that contains all the glide extensions functions.
 */

/**
 * Extension function to load an image from URL into ImageView.
 */
fun ImageView.displayImage(url: String) {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .placeholder(R.drawable.ic_default_player_image)
        .error(R.drawable.ic_broken_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .skipMemoryCache(false)
        .into(this)
}
