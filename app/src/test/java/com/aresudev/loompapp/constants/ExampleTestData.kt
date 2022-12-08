package com.aresudev.loompapp.constants

import com.aresudev.loompapp.commons.data.model.FavoriteModel
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel

//Hardcoded data only for testing.
class ExampleTestData {
    companion object {
        //Generates example data: Page has Loompas with different profession and gender.
        fun generateExampleData() =
            LoompaPageModel(
                1,
                2,
                listOf(
                    LoompaModel(
                        1, "Loompa name", "Loompa Surname",
                        FavoriteModel("brown", "cookies", "lalalala", "random"),
                        "m", "image loompa", "policeman", "my description", "my email", "15",
                        "japan", "15", "quota"
                    ),
                    LoompaModel(
                        2, "Loompa name", "Loompa Surname",
                        FavoriteModel("brown", "cookies", "lalalala", "random"),
                        "f", "image loompa", "developer", "my description", "my email", "15",
                        "japan", "15", "quota"
                    ),
                    LoompaModel(
                        3, "Loompa name", "Loompa Surname",
                        FavoriteModel("brown", "cookies", "lalalala", "random"),
                        "m", "image loompa", "developer", "my description", "my email", "15",
                        "japan", "15", "quota"
                    )
                )
            )
    }
}