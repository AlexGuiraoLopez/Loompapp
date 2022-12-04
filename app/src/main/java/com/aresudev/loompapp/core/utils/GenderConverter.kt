package com.aresudev.loompapp.core.utils

import com.aresudev.loompapp.R
import com.aresudev.loompapp.core.extensions.getAppString

class GenderConverter {
    companion object {
        fun convertGenderByLetterValue(letter: String): String =
            when (letter.lowercase()) {
                "m" -> getAppString(R.string.male_gender)
                "f" -> getAppString(R.string.female_gender)
                else -> getAppString(R.string.non_defined_gender)
            }
    }
}