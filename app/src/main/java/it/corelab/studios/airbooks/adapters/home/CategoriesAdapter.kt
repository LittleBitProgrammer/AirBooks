package it.corelab.studios.airbooks.adapters.HOME

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.data.model.HOME.Genre

/**
 * Created by Roberto_Vecchio on 19/02/18.
 */

class CategoriesAdapter(context: Context, private val items: List<Genre>) : RecyclerView.Adapter<CategoriesAdapter.RecyclerViewHolder>() {
    private val layoutInflater: LayoutInflater

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.RecyclerViewHolder {
        val item = layoutInflater.inflate(R.layout.categories_home, parent, false)

        return CategoriesAdapter.RecyclerViewHolder(item)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoriesAdapter.RecyclerViewHolder, position: Int) {
        val item = items[position]

        val colors = intArrayOf(Color.parseColor("#" + item.firstColor), Color.parseColor("#" + item.secondColor))
        val gd = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors)
        gd.cornerRadius = 0f

        holder.image.background = gd
        holder.textView.text = item.name


    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal inner class RecyclerViewHolder private constructor(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView
        private val textView: TextView


        init {

            image = v.findViewById(R.id.cardImage_categories_home)
            textView = v.findViewById(R.id.categories_home_id)
        }
    }
}
