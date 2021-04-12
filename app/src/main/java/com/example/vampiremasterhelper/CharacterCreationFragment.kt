package com.example.vampiremasterhelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vampiremasterhelper.databinding.CharacterCreationFragmentBinding
import com.example.vampiremasterhelper.viewmodel.CharacterCreationViewModel
import com.example.vampiremasterhelper.views.BottomDialogView

class CharacterCreationFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterCreationFragment()
    }

    private lateinit var viewModel: CharacterCreationViewModel
    private lateinit var binding: CharacterCreationFragmentBinding
    private var selectClanDialog: BottomDialogView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            CharacterCreationFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterCreationViewModel::class.java)
        /*binding.tilClanInput.setOnClickListener {
            if (selectClanDialog == null) {
                selectClanDialog = BottomDialogView(requireActivity())
                selectClanDialog?.let { dialog -> dialog.window?.setBackgroundDrawableResource(R.drawable.bc_border)

                    val selectDestinationView = SelectDialogViewHolder(listOf(DialogSelectItem("Assamita"), DialogSelectItem("Brujah")), requireActivity())
                    dialog.setContentView(selectDestinationView.getView("Clan") {
                        it?.let { selected ->
                            binding.tilClanInput.setText(selected.value)
                            dialog.dismiss()
                        }
                    })
                }
            }
            selectClanDialog?.show()
        }*/
    }
}