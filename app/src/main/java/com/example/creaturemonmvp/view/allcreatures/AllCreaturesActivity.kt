package com.example.creaturemonmvp.view.allcreatures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creaturemonmvp.R
import com.example.creaturemonmvp.app.CreaturemonApplication
import com.example.creaturemonmvp.app.toast
import com.example.creaturemonmvp.presenter.AllCreaturesContract
import com.example.creaturemonmvp.presenter.AllCreaturesPresenter
import com.example.creaturemonmvp.view.creature.CreatureActivity
import kotlinx.android.synthetic.main.activity_all_creatures.*
import javax.inject.Inject

class AllCreaturesActivity : AppCompatActivity(), AllCreaturesContract.View {
    @Inject lateinit var presenter: AllCreaturesPresenter

    private val adapter = CreatureAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CreaturemonApplication).appComponent
            .allCreaturesComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_creatures)

        presenter.setView(this)

        setCreaturesRecyclerView()
        setAllCreaturesLiveDataObserver()
        setAddFab()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_all -> {
                presenter.clearAllCreatures()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showCreaturesCleared() {
        toast(R.string.creatures_cleared)
    }

    private fun setCreaturesRecyclerView() {
        creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
        creaturesRecyclerView.adapter = adapter
    }

    private fun setAllCreaturesLiveDataObserver() {
        presenter.getAllCreatures().observe(this, Observer { creatures ->
            adapter.updateCreatures(creatures)
        })
    }

    private fun setAddFab() {
        addFab.setOnClickListener {
            val intent = Intent(this, CreatureActivity::class.java)
            startActivity(intent)
        }
    }
}