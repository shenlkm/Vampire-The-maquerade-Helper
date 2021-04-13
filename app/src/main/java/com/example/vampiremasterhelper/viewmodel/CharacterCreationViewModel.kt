package com.example.vampiremasterhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vampiremasterhelper.model.CharacterInformation

class CharacterCreationViewModel : ViewModel() {

    private var _characterInfo:  MutableLiveData<CharacterInformation> = MutableLiveData()
    var characterInfo:  LiveData<CharacterInformation> = _characterInfo

    init {
        _characterInfo.value =
            CharacterInformation(
                name = "Cratos",
                nature = "Conocedor",
                demeanor = "Ignorante",
                concept = "Indigente",
                clan = "Gangrel",
                sire = "Chris Pratt"
            )
    }
}