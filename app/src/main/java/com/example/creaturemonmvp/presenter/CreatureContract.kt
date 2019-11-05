package com.example.creaturemonmvp.presenter

import com.example.creaturemonmvp.model.AttributeType

interface CreatureContract {

    interface Presenter {

        fun nameChanged(name: String)

        fun attributeSelected(attributeType: AttributeType, position: Int)

        fun drawableSelected(drawable: Int)

        fun isDrawableSelected(): Boolean
    }

    interface View {

        // TODO: add interface methods for View
    }
}