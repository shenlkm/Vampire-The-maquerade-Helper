package com.example.vampiremasterhelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.vampiremasterhelper.databinding.CharacterCreationFragmentBinding
import com.example.vampiremasterhelper.model.PointGroupSetModel
import com.example.vampiremasterhelper.utils.Refrigerator
import com.example.vampiremasterhelper.viewmodel.CharacterCreationViewModel

class CharacterCreationFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterCreationFragment()
    }

    private lateinit var viewModel: CharacterCreationViewModel
    private lateinit var binding: CharacterCreationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            CharacterCreationFragmentBinding.inflate(LayoutInflater.from(context), container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CharacterCreationViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        binding.ivEditCharacterInfo.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fl_container, CharacterInfoFragment.newInstance())
                addToBackStack(null)
            }
        }

        viewModel.characterInfo.observe(viewLifecycleOwner, { characterInformation ->
            viewModel.skillSets?.find { pgs -> pgs.title == "Atributos" }?.let { attr ->
                binding.pgsvAttributes.setData(viewModel.applyWeakness(attr, characterInformation))
            }
        })

        if (viewModel.skillSets == null) {
            getDataSet().let {
                viewModel.skillSets = it
                binding.pgsvAttributes.setData(it[0])
                binding.pgsvSkills.setData(it[1])
                binding.pgsvSkills.setIsExpanded(false)
                binding.pgsvAdvantages.setData(it[2])
                binding.pgsvAdvantages.setIsExpanded(false)
            }
        }
    }



    private fun getDataSet(): List<PointGroupSetModel> {
        return Refrigerator.getPointSetGroupItems(requireContext(), "point_view")
    }
}