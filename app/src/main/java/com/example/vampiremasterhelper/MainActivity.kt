package com.example.vampiremasterhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vampiremasterhelper.databinding.ActivityMainBinding
import com.example.vampiremasterhelper.model.PointGroupModel
import com.example.vampiremasterhelper.model.PointGroupSetModel
import com.example.vampiremasterhelper.model.PointItemModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.tvPointGroupSetTitle.setText("ATRIBUTOS")

        getDataSet().let {
            binding.pgsvAttributes.setData(it[0])
            binding.pgsvSkills.setData(it[1])
        }
    }

    private fun getDataSet() : Array<PointGroupSetModel> {
        return arrayOf(
                PointGroupSetModel(title = "ATRIBUTOS",
                        items = arrayOf(
                            PointGroupModel(title = "Fisicos",
                                    items = arrayOf(PointItemModel("Fuerza"), PointItemModel("Destreza"), PointItemModel("Resistencia"))),
                            PointGroupModel(title = "Mentales",
                                    items = arrayOf(PointItemModel("Manipulacion"), PointItemModel("Carisma"), PointItemModel("Apariencia"))),
                            PointGroupModel(title = "Sociales",
                                    items = arrayOf(PointItemModel("Percepcion"), PointItemModel("Inteligencia"), PointItemModel("Astucia"))),
                )),
                PointGroupSetModel(title = "HABILIDADES",
                        items = arrayOf(
                                PointGroupModel(title = "Talentos",
                                        items = arrayOf(PointItemModel("Alerta"), PointItemModel("Atletismo"), PointItemModel("Callejeo"))),
                                PointGroupModel(title = "Tecnicas",
                                        items = arrayOf(PointItemModel("Armas C.C"), PointItemModel("Armas de Fuego"), PointItemModel("Conducir"))),
                                PointGroupModel(title = "Conocimientos",
                                        items = arrayOf(PointItemModel("Academisismo"), PointItemModel("Ciencias"), PointItemModel("Finanzas"))),
                        ))
        )
    }
}