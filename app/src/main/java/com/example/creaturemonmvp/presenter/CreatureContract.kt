package com.example.creaturemonmvp.presenter

import androidx.annotation.DrawableRes
import com.example.creaturemonmvp.model.AttributeType

interface CreatureContract {

    interface Presenter {

        fun nameChanged(name: String)

        fun attributeSelected(attributeType: AttributeType, position: Int)

        fun drawableSelected(drawable: Int)

        fun isDrawableSelected(): Boolean

        fun saveCreature()
    }

    interface View {

        fun showHitPoints(hitPoints: Int)

        fun showAvatarDrawable(@DrawableRes drawable: Int)

        fun showCreatureSaved()

        fun showCreatureSavedError()
    }
}