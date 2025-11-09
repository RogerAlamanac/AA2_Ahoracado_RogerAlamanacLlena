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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout_manager, parent, false)

        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val level = levels[position]
        holder.bind(level)
        // Aquí hacemos el cambio de escena directamente
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, Gameplay::class.java)

            //Envia una clave con la palabra del nivel actual
            intent.putExtra("currentWord", level.word)
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

        //Setear las propiedades del nivel (variables del constructor)
        fun bind(level:Level){
            levelWord.text = level.word
            letterCount.text = level.letterNum.toString()

            val length : Int = levelWord.length()

            //Seteamos la imagen de dificultad en funcion de las letras que tenga la palabra
            if (length < 5) {
                imageDifficulty.setImageResource(R.drawable.img_charmander)
            } else if (length in 5..7) {
                imageDifficulty.setImageResource(R.drawable.img_charmeleon)
            } else {
                imageDifficulty.setImageResource(R.drawable.img_charizard)
            }

        }
    }
}