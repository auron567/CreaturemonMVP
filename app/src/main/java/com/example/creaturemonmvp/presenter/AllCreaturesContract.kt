package com.example.creaturemonmvp.presenter

import androidx.lifecycle.LiveData
import com.example.creaturemonmvp.model.Creature

interface AllCreaturesContract {

    interface Presenter {

        fun getAllCreatures(): LiveData<List<Creature>>

        fun clearAllCreatures()
    }

    interface View {

        fun showCreaturesCleared()
    }
}