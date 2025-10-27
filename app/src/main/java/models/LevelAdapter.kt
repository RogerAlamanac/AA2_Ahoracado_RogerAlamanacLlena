package models

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aa2_ahorcado_rogeralamanac.Gameplay
import com.example.aa2_ahorcado_rogeralamanac.R

class LevelAdapter(private val levels: List<Level>)
    : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelAdapter.LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout_manager, parent, false)

        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelAdapter.LevelViewHolder, position: Int) {
        val level = levels[position]
        holder.bind(level)
        // Aquí hacemos el cambio de escena directamente
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, Gameplay::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val levelWord: TextView = itemView.findViewById(R.id.textWord)
        private val letterCount: TextView = itemView.findViewById(R.id.textLettersCount)
        private val imageDifficulty: ImageView = itemView.findViewById(R.id.imageDifficulty)

        fun bind(level:Level){
            levelWord.text = level.word
            letterCount.text = level.letterNum.toString()
            imageDifficulty.setImageResource(level.imageDifficulty)
        }
    }
}