package com.example.creaturemonmvp.di

import com.example.creaturemonmvp.view.creature.CreatureActivity
import dagger.Subcomponent

@Subcomponent
interface CreatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): CreatureComponent
    }

    fun inject(activity: CreatureActivity)
}