package com.example.core.utils

fun getDistance(distance : String) : Float {

    return distance.replace(" km", "").toFloatOrNull() ?: return 0.0f

}