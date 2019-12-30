package com.example.creaturemonmvp.presenter

import com.example.creaturemonmvp.model.*
import javax.inject.Inject

class CreaturePresenter @Inject constructor(
    private val generator: CreatureGenerator,
    private val repository: CreatureRepository
) : BasePresenter<CreatureContract.View>(), CreatureContract.Presenter {

    private lateinit var creature: Creature

    private var name = ""
    private var intelligence = 0
    private var strength = 0
    private var endurance = 0
    private var drawable = 0

    override fun attributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            AttributeType.INTELLIGENCE ->
                intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH ->
                strength = AttributeStore.STRENGTH[position].value
            AttributeType.ENDURANCE ->
                endurance = AttributeStore.ENDURANCE[position].value
        }

        updateCreature()
    }

    override fun nameChanged(name: String) {
        this.name = name
        updateCreature()
    }

    override fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        getView()?.showAvatarDrawable(drawable)
        updateCreature()
    }

    override fun isDrawableSelected(): Boolean {
        return drawable != 0
    }

    override fun saveCreature() {
        if (canSaveCreature()) {
            repository.saveCreature(creature)
            getView()?.showCreatureSaved()
        } else {
            getView()?.showCreatureSavedError()
        }
    }

    private fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = generator.generateCreature(attributes, name, drawable)
        getView()?.showHitPoints(creature.hitPoints)
    }

    private fun canSaveCreature(): Boolean {
        return intelligence != 0 && strength != 0 && endurance != 0 &&
                drawable != 0 && name.isNotBlank()
    }
}