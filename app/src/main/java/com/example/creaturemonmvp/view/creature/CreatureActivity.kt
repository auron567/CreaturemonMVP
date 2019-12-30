package com.example.creaturemonmvp.view.creature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.creaturemonmvp.R
import com.example.creaturemonmvp.app.CreaturemonApplication
import com.example.creaturemonmvp.app.toast
import com.example.creaturemonmvp.model.AttributeStore
import com.example.creaturemonmvp.model.AttributeType
import com.example.creaturemonmvp.model.AttributeValue
import com.example.creaturemonmvp.model.Avatar
import com.example.creaturemonmvp.presenter.CreatureContract
import com.example.creaturemonmvp.presenter.CreaturePresenter
import com.example.creaturemonmvp.view.avatar.AvatarBottomDialogFragment
import com.example.creaturemonmvp.view.avatar.AvatarListener
import kotlinx.android.synthetic.main.activity_creature.*
import javax.inject.Inject

class CreatureActivity : AppCompatActivity(), AvatarListener, CreatureContract.View {
    @Inject lateinit var presenter: CreaturePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CreaturemonApplication).appComponent
            .creatureComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creature)

        presenter.setView(this)

        setUI()
        setSpinnerAdapters()
        setSpinnerListeners()
        setNameEditText()
        setClickListeners()
    }

    private fun setUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (presenter.isDrawableSelected()) {
            hideTapLabel()
        }
    }

    private fun setSpinnerAdapters() {
        intelligenceSpinner.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.INTELLIGENCE
        )
        strengthSpinner.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.STRENGTH
        )
        enduranceSpinner.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.ENDURANCE
        )
    }

    private fun setSpinnerListeners() {
        intelligenceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.attributeSelected(AttributeType.INTELLIGENCE, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        strengthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.attributeSelected(AttributeType.STRENGTH, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        enduranceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.attributeSelected(AttributeType.ENDURANCE, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setNameEditText() {
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.nameChanged(s.toString())
            }
        })
    }

    private fun setClickListeners() {
        avatarImageView.setOnClickListener {
            val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
        }

        saveButton.setOnClickListener {
            presenter.saveCreature()
        }
    }

    override fun avatarClicked(avatar: Avatar) {
        presenter.drawableSelected(avatar.drawable)
        hideTapLabel()
    }

    private fun hideTapLabel() {
        tapLabel.visibility = View.INVISIBLE
    }

    override fun showHitPoints(hitPoints: Int) {
        this.hitPoints.text = hitPoints.toString()
    }

    override fun showAvatarDrawable(drawable: Int) {
        avatarImageView.setImageResource(drawable)
    }

    override fun showCreatureSaved() {
        toast(R.string.creature_saved)
        finish()
    }

    override fun showCreatureSavedError() {
        toast(R.string.error_saving_creature)
    }
}