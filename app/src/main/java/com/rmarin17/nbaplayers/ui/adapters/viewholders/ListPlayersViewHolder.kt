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

    fun bind(player: PlayerUiModel) {
        with(player) {
            binding.itemPlayerImage.displayImage(image)
            binding.itemPlayerName.text = name
            binding.itemPlayerTeam.text = team.name
            binding.itemPlayerPosition.text = position
            binding.itemPlayerCard.setOnClickListener {
                onItemClick.invoke(this)
            }
        }
    }
}
