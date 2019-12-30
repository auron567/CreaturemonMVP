package com.example.creaturemonmvp.di

import com.example.creaturemonmvp.view.allcreatures.AllCreaturesActivity
import dagger.Subcomponent

@Subcomponent
interface AllCreaturesComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): AllCreaturesComponent
    }

    fun inject(activity: AllCreaturesActivity)
}