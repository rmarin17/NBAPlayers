package com.rmarin17.nbaplayers.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rmarin17.nbaplayers.R
import com.rmarin17.nbaplayers.common.ext.displayImage
import com.rmarin17.nbaplayers.databinding.FragmentDeatilPlayerBinding
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel

/**
 * Fragment of detail player.
 */
class DetailPlayerFragment : Fragment() {

    private lateinit var binding: FragmentDeatilPlayerBinding

    private val player by lazy { arguments?.getParcelable<PlayerUiModel>(PLAYER_DETAIL) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDeatilPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        player?.let { playerUi ->
            with(binding) {
                playerDetailName.text = playerUi.name
                playerDetailImage.displayImage(playerUi.image)
                playerDetailTeamPosition.text = getString(R.string.player_detail_team_position, playerUi.team.name, playerUi.position)
                playerDetailWeight.text = getString(R.string.player_detail_weight, playerUi.weight)
                playerDetailHeight.text = getString(R.string.player_detail_height, playerUi.height)
            }
        }
    }

    companion object {
        const val PLAYER_DETAIL = "player_detail"
    }
}
