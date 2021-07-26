package com.rmarin17.nbaplayers.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rmarin17.nbaplayers.R
import com.rmarin17.nbaplayers.common.ext.inflate
import com.rmarin17.nbaplayers.ui.adapters.viewholders.ListPlayersViewHolder
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel

/**
 * Adapter for list players.
 */
class ListPlayersAdapter(
    private val players: MutableList<PlayerUiModel>,
    private val onItemClick: (PlayerUiModel) -> Unit
) : RecyclerView.Adapter<ListPlayersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlayersViewHolder {
        return ListPlayersViewHolder(
            parent.inflate(R.layout.item_player),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: ListPlayersViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int {
        return players.size
    }

    fun updatePlayerList(listPlayers: List<PlayerUiModel>) {
        players.clear()
        players.addAll(listPlayers)
        notifyDataSetChanged()
    }
}
