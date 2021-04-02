package com.example.vampiremasterhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                                            PointItemModel("Intimidacion"), PointItemModel("Liderazgo"), PointItemModel("Pelea"),PointItemModel("Subterfugio"))),
                                PointGroupModel(title = "Tecnicas",
                                        items = arrayOf(PointItemModel("Armas de Fuego"), PointItemModel("Artesania"), PointItemModel("Conducir"),
                                                        PointItemModel("Etiqueta"), PointItemModel("Interpretacion"), PointItemModel("Latrocinio"),
                                                        PointItemModel("Pelea con armas"), PointItemModel("Sigilo"), PointItemModel("Supervivencia"),PointItemModel("Trato con animales"))),
                                PointGroupModel(title = "Conocimientos",
                                        items = arrayOf(PointItemModel("Academisismo"), PointItemModel("Ciencias"), PointItemModel("Finanzas"),
                                                        PointItemModel("Informatica"), PointItemModel("Investigacion"), PointItemModel("Leyes"),
                                                        PointItemModel("Medcina"), PointItemModel("Ocultismo"), PointItemModel("Politica"),PointItemModel("Tecnologia"))),
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