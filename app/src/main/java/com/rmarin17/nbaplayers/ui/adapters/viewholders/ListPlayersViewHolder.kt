package com.rmarin17.nbaplayers.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rmarin17.nbaplayers.common.ext.displayImage
import com.rmarin17.nbaplayers.databinding.ItemPlayerBinding
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel

/**
 * View holder of players list adapter.
 */
class ListPlayersViewHolder(
    view: View,
    private val onItemClick: (PlayerUiModel) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPlayerBinding.bind(itemView)

    fun bind(product: PlayerUiModel) {
        with(product) {
            // TODO - When services return image for players, please pass it here.
            binding.itemPlayerImage.displayImage(MOCK_PLAYER_IMAGE)
            binding.itemPlayerName.text = name
            binding.itemPlayerTeam.text = team.name
            binding.itemPlayerPosition.text = position
            binding.itemPlayerCard.setOnClickListener {
                onItemClick.invoke(this)
            }
        }
    }

    companion object {
        // TODO - When services return image for players, please remove this workaround
        private const val MOCK_PLAYER_IMAGE = "https://image.shutterstock.com/image-photo/8-nov-2019-beijing-china-260nw-1554266144.jpg"
    }

}
