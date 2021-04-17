package com.example.vampiremasterhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vampiremasterhelper.model.CharacterInformation
import com.example.vampiremasterhelper.model.PointGroupSetModel

class CharacterCreationViewModel : ViewModel() {

    private var _characterInfo: MutableLiveData<CharacterInformation> = MutableLiveData()
    var characterInfo: LiveData<CharacterInformation> = _characterInfo

    private var _attributes: MutableLiveData<PointGroupSetModel> = MutableLiveData()
    var attributes: LiveData<PointGroupSetModel> = _attributes

    private var _abilities: MutableLiveData<PointGroupSetModel> = MutableLiveData()
    var abilities: LiveData<PointGroupSetModel> = _abilities

    private var _advantages: MutableLiveData<PointGroupSetModel> = MutableLiveData()
    var advantages: LiveData<PointGroupSetModel> = _advantages


    init {
        _characterInfo.value =
            CharacterInformation(
                name = "",
                nature = "",
                demeanor = "",
                concept = "",
                clan = "",
                sire = ""
            )
    }

    fun addSkills(skills: List<PointGroupSetModel>) {
        _attributes.postValue(skills.find { s -> s.title == "Atributos" })
        _abilities.postValue(skills.find { s -> s.title == "Habilidades" })
        _advantages.postValue(skills.find { s -> s.title == "Ventajas" })
    }

    fun saveCharacterInformation(info: CharacterInformation) {
        _characterInfo.postValue(info)
    }

    fun applyWeakness(characterInformation: CharacterInformation) {
        if (characterInformation.clan.isNotBlank()) {
            val data = _attributes.value
            data?.let { attr ->
                attr.items.find { pg -> pg.title == "Mentales" }?.items?.find { pi -> pi.label == "Apariencia" }
                    ?.let {
                        when (characterInformation.clan) {
                            "Nosferatu" -> {
                                it.filled = 0
                                it.locked = true
                            }
                            else -> {
                                it.filled = 1
                                it.locked = false
                            }
                        }
                    }
                _attributes.postValue(attr)
            }
        }
    }

    fun checkAttributes(attributes: PointGroupSetModel) {
        val attrPoints = HashMap<String, Int>()
        val seven = attributes.items.filter { i -> i.totalPoints >= 7 }.count()
        val five = attributes.items.filter { i -> i.totalPoints >= 5 }.count()
        val three = attributes.items.filter { i -> i.totalPoints >= 3 }.count()
        if ( seven >= 1) {
            attributes.items.first { i -> i.totalPoints >= 7 }.locked = true
        }

        if (seven >= 1 && five >= 2) {
            attributes.items.first { i -> i.totalPoints >= 5 }.locked = true
        }

        if (seven >= 1 && five >= 2 && three >= 3) {
            attributes.items.first { i -> i.totalPoints >= 3 }.locked = true
        }
        if (three >= 3 && seven == 0 && five >= 2) {
            attributes.items.first { i -> i.totalPoints >= 3 }.locked = true
        }

    }
}