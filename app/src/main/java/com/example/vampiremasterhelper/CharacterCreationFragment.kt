package com.example.vampiremasterhelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vampiremasterhelper.databinding.CharacterCreationFragmentBinding
import com.example.vampiremasterhelper.model.PointGroupModel
import com.example.vampiremasterhelper.model.PointGroupSetModel
import com.example.vampiremasterhelper.model.PointItemModel
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
        binding.characterInfo = viewModel.characterInfo
        binding.ivEditCharacterInfo.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fl_container, CharacterInfoFragment.newInstance())
                addToBackStack(null)
            }
        }

        getDataSet().let {
            binding.pgsvAttributes.setData(it[0])
            binding.pgsvSkills.setData(it[1])
            binding.pgsvAdvantages.setData(it[2])
        }
    }

    private fun getDataSet() : Array<PointGroupSetModel> {
        return arrayOf(
            PointGroupSetModel(title = "Atributos",
                items = arrayOf(
                    PointGroupModel(title = "Fisicos",
                        items = arrayOf(PointItemModel("Fuerza"), PointItemModel("Destreza"), PointItemModel("Resistencia"))),
                    PointGroupModel(title = "Mentales",
                        items = arrayOf(PointItemModel("Manipulacion"), PointItemModel("Carisma"), PointItemModel("Apariencia"))),
                    PointGroupModel(title = "Sociales",
                        items = arrayOf(PointItemModel("Percepcion"), PointItemModel("Inteligencia"), PointItemModel("Astucia"))),
                )),
            PointGroupSetModel(title = "Habilidades",
                items = arrayOf(
                    PointGroupModel(title = "Talentos",
                        items = arrayOf(
                            PointItemModel("Alerta"), PointItemModel("Atletismo"), PointItemModel("Callejeo"),
                            PointItemModel("Conciencia"), PointItemModel("Empatia"), PointItemModel("Expresion"),
                            PointItemModel("Intimidacion"), PointItemModel("Liderazgo"), PointItemModel("Pelea"),
                            PointItemModel("Subterfugio")
                        )),
                    PointGroupModel(title = "Tecnicas",
                        items = arrayOf(
                            PointItemModel("Armas de Fuego"), PointItemModel("Artesania"), PointItemModel("Conducir"),
                            PointItemModel("Etiqueta"), PointItemModel("Interpretacion"), PointItemModel("Latrocinio"),
                            PointItemModel("Pelea con armas"), PointItemModel("Sigilo"), PointItemModel("Supervivencia"),
                            PointItemModel("Trato con animales")
                        )),
                    PointGroupModel(title = "Conocimientos",
                        items = arrayOf(
                            PointItemModel("Academisismo"), PointItemModel("Ciencias"), PointItemModel("Finanzas"),
                            PointItemModel("Informatica"), PointItemModel("Investigacion"), PointItemModel("Leyes"),
                            PointItemModel("Medcina"), PointItemModel("Ocultismo"), PointItemModel("Politica"),
                            PointItemModel("Tecnologia")
                        )),
                )),
            PointGroupSetModel(title = "Ventajas",
                items = arrayOf(
                    PointGroupModel(title = "Disiplinas",
                        items = arrayOf(PointItemModel("Animalismo"), PointItemModel("Auspex"), PointItemModel("Fortaleza"))),
                    PointGroupModel(title = "Trasfondos",
                        items = arrayOf(PointItemModel("Generacion"), PointItemModel("Recursos"), PointItemModel("Aliados"))),
                    PointGroupModel(title = "Virtudes",
                        items = arrayOf(PointItemModel("Conciencia"), PointItemModel("Autocontrol"), PointItemModel("Coraje"))),
                )),
        )
    }
}