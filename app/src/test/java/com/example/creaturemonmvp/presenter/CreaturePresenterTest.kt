package com.example.creaturemonmvp.presenter

import com.example.creaturemonmvp.model.AttributeType
import com.example.creaturemonmvp.model.Creature
import com.example.creaturemonmvp.model.CreatureAttributes
import com.example.creaturemonmvp.model.CreatureGenerator
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CreaturePresenterTest {
    private val mockGenerator: CreatureGenerator = mockk()
    private val mockView: CreatureContract.View = mockk(relaxed = true)

    private lateinit var creaturePresenter: CreaturePresenter

    @Before
    fun setup() {
        creaturePresenter = CreaturePresenter(mockGenerator).apply {
            setView(mockView)
        }
    }

    @Test
    fun testIntelligenceSelected() {
        val attributes = CreatureAttributes(10, 0, 0)
        val stubCreature = Creature(attributes, 50, "Test Creature")
        every { mockGenerator.generateCreature(attributes) } returns stubCreature

        creaturePresenter.attributeSelected(AttributeType.INTELLIGENCE, 3)

        verify(exactly = 1) {
            mockView.showHitPoints(50)
        }
    }
}