package com.example.creaturemonmvp.presenter

import androidx.lifecycle.LiveData
import com.example.creaturemonmvp.model.Creature
import com.example.creaturemonmvp.model.CreatureRepository

class AllCreaturesPresenter(private val repository: CreatureRepository)
    : BasePresenter<AllCreaturesContract.View>(), AllCreaturesContract.Presenter {

    override fun getAllCreatures(): LiveData<List<Creature>> {
        return repository.getAllCreatures()
    }

    override fun clearAllCreatures() {
        repository.clearAllCreatures()
        getView()?.showCreaturesCleared()
    }
}