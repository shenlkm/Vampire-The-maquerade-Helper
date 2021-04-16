package com.example.vampiremasterhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vampiremasterhelper.model.CharacterInformation
import com.example.vampiremasterhelper.model.PointGroupSetModel

class CharacterCreationViewModel : ViewModel() {

    private var _characterInfo:  MutableLiveData<CharacterInformation> = MutableLiveData()
    var characterInfo:  LiveData<CharacterInformation> = _characterInfo
    var skillSets: List<PointGroupSetModel>? = null

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

    fun saveCharacterInformation(info: CharacterInformation){
        _characterInfo.postValue(info)
    }
}