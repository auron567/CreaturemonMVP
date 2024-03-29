package com.example.creaturemonmvp.view.allcreatures

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.creaturemonmvp.R
import com.example.creaturemonmvp.app.inflate
import com.example.creaturemonmvp.model.Creature
import kotlinx.android.synthetic.main.list_item_creature.view.*

class CreatureAdapter(private val creatures: MutableList<Creature>)
    : RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_creature))
    }

    override fun getItemCount() = creatures.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    fun updateCreatures(creatures: List<Creature>) {
        this.creatures.clear()
        this.creatures.addAll(creatures)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var creature: Creature

        fun bind(creature: Creature) {
            this.creature = creature

            with(itemView) {
                avatarImageView.setImageResource(creature.drawable)
                name.text = creature.name
                hitPoints.text = creature.hitPoints.toString()
            }
        }
    }
}