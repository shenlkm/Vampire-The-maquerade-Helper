package com.example.vampiremasterhelper

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vampiremasterhelper.databinding.CharacterCreationFragmentBinding
import com.example.vampiremasterhelper.databinding.CharacterInfoFragmentBinding
import com.example.vampiremasterhelper.model.CharacterInformation
import com.example.vampiremasterhelper.model.DialogSelectItem
import com.example.vampiremasterhelper.utils.Refrigerator
import com.example.vampiremasterhelper.viewmodel.CharacterCreationViewModel
import com.example.vampiremasterhelper.views.BottomDialogView
import com.example.vampiremasterhelper.views.holders.SelectDialogViewHolder

class CharacterInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterInfoFragment()
    }

    private lateinit var viewModel: CharacterCreationViewModel
    private lateinit var binding: CharacterInfoFragmentBinding
    private var clanList: List<DialogSelectItem>? = null
    private var selectClanDialog: BottomDialogView? = null
    private var characterInfo : CharacterInformation? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            CharacterInfoFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (clanList.isNullOrEmpty()) {
            clanList = Refrigerator.getDialogItems(context, "clan_list")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CharacterCreationViewModel::class.java)
        characterInfo = viewModel.characterInfo.value
        binding.characterInfo = characterInfo
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tilClanInput.setOnClickListener {
            if (selectClanDialog == null) {
                selectClanDialog = BottomDialogView(requireActivity())
                selectClanDialog?.let { dialog ->
                    dialog.window?.setBackgroundDrawableResource(R.drawable.bc_border)
                    clanList?.let {
                        val selectDestinationView = SelectDialogViewHolder(it, requireActivity())
                        dialog.setContentView(selectDestinationView.getView("Clan") { dialogItem ->
                            dialogItem?.let { selected ->
                                binding.tilClanInput.setText(selected.value)
                                dialog.dismiss()
                            }
                        })
                    }
                }
            }
            selectClanDialog?.show()
        }

        binding.btSaveCharacterInfo.setOnClickListener {
            binding.characterInfo?.let {
                viewModel.saveCharacterInformation(it)
            }
            requireActivity().onBackPressed()
        }
    }
}