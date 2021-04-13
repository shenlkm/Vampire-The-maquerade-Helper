package com.example.vampiremasterhelper.model

data class CharacterInformation(
    var name: String,
    var chronicle: String = "",
    var nature: String,
    var demeanor: String,
    var concept: String,
    var clan: String,
    var generation: String = "",
    var sire: String
)
