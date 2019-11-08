package com.example.creaturemonmvp.presenter

import com.example.creaturemonmvp.model.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CreaturePresenterTest {
    private val mockGenerator: CreatureGenerator = mockk()
    private val mockRepository: CreatureRepository = mockk()
    private val mockView: CreatureContract.View = mockk(relaxed = true)

    private lateinit var creaturePresenter: CreaturePresenter

    @Before
    fun setup() {
        creaturePresenter = CreaturePresenter(mockGenerator, mockRepository).apply {
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

    @Test
    fun testStrengthSelected() {
        val attributes = CreatureAttributes(0, 7, 0)
        val stubCreature = Creature(attributes, 21, "Test Creature")
        every { mockGenerator.generateCreature(attributes) } returns stubCreature

        creaturePresenter.attributeSelected(AttributeType.STRENGTH, 2)

        verify(exactly = 1) {
            mockView.showHitPoints(21)
        }
    }

    @Test
    fun testEnduranceSelected() {
        val attributes = CreatureAttributes(0, 0, 3)
        val stubCreature = Creature(attributes, 12, "Test Creature")
        every { mockGenerator.generateCreature(attributes) } returns stubCreature

        creaturePresenter.attributeSelected(AttributeType.ENDURANCE, 1)

        verify(exactly = 1) {
            mockView.showHitPoints(12)
        }
    }
}