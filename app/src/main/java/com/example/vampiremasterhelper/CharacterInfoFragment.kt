package com.example.vampiremasterhelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vampiremasterhelper.databinding.CharacterCreationFragmentBinding
import com.example.vampiremasterhelper.databinding.CharacterInfoFragmentBinding
import com.example.vampiremasterhelper.model.DialogSelectItem
import com.example.vampiremasterhelper.viewmodel.CharacterCreationViewModel
import com.example.vampiremasterhelper.views.BottomDialogView
import com.example.vampiremasterhelper.views.holders.SelectDialogViewHolder

class CharacterInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterInfoFragment()
    }

    private lateinit var viewModel: CharacterCreationViewModel
    private lateinit var binding: CharacterInfoFragmentBinding
    private var selectClanDialog: BottomDialogView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            CharacterInfoFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CharacterCreationViewModel::class.java)
        binding.characterInfo = viewModel.characterInfo
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tilClanInput.setOnClickListener {
            if (selectClanDialog == null) {
                selectClanDialog = BottomDialogView(requireActivity())
                selectClanDialog?.let { dialog ->
                    dialog.window?.setBackgroundDrawableResource(R.drawable.bc_border)

                    val selectDestinationView = SelectDialogViewHolder(
                        listOf(
                            DialogSelectItem("Caitiff"),
                            DialogSelectItem("Camarilla", isSection = true),
                            DialogSelectItem("Brujah"),
                            DialogSelectItem("Gangrel"),
                            DialogSelectItem("Malkavian"),
                            DialogSelectItem("Nosferatu"),
                            DialogSelectItem("Toreador"),
                            DialogSelectItem("Tremere"),
                            DialogSelectItem("Centrue"),
                            DialogSelectItem("Sabbat", isSection = true),
                            DialogSelectItem("Lasombre"),
                            DialogSelectItem("Tzimisce"),
                            DialogSelectItem("Independiences", isSection = true),
                            DialogSelectItem("Assamita"),
                            DialogSelectItem("Giovanni"),
                            DialogSelectItem("Seguidores de Set"),
                            DialogSelectItem("Ravnos")
                        ), requireActivity()
                    )
                    dialog.setContentView(selectDestinationView.getView("Clan") {
                        it?.let { selected ->
                            binding.tilClanInput.setText(selected.value)
                            dialog.dismiss()
                        }
                    })
                }
            }
            selectClanDialog?.show()
        }

        binding.btSaveCharacterInfo.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}