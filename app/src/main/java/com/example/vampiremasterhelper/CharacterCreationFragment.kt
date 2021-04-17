package com.example.vampiremasterhelper

import android.content.Context
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
import com.example.vampiremasterhelper.views.listener.PointSetGroupViewListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterCreationFragment : Fragment(), PointSetGroupViewListener {

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
            viewModel.applyWeakness(characterInformation)
        })

        viewModel.attributes.observe(viewLifecycleOwner, { attributes ->
            attributes?.let {
                binding.pgsvAttributes.setData(attributes)
            }
        })

        viewModel.abilities.observe(viewLifecycleOwner, { abilities ->
            abilities?.let {
                binding.pgsvAbilities.setData(abilities)
            }
        })

        viewModel.advantages.observe(viewLifecycleOwner, { advantages ->
            advantages?.let {
                binding.pgsvAdvantages.setData(advantages)
            }
        })

        binding.pgsvAdvantages.setIsExpanded(false)
        binding.pgsvAbilities.setIsExpanded(false)
        binding.pgsvAttributes.setViewDataChangeListener(this)

        CoroutineScope(Dispatchers.Default).launch {
            getDataSet().let {
                viewModel.addSkills(it)
            }
        }
    }


    private fun getDataSet(): List<PointGroupSetModel> {
        return Refrigerator.getPointSetGroupItems(requireContext(), "point_view")
    }

    override fun notifyPunctuationChange(view: View) {
        if (binding.pgsvAttributes.id == view.id) {
            viewModel.checkAttributes(binding.pgsvAttributes.getData())
        }
    }
}