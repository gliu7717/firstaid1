package com.derek.firstaidgame.ui.flashcards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.derek.firstaidgame.R

class SlideAdapter (private val slides:List<Slide>):
        RecyclerView.Adapter<SlideViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        return SlideViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return slides.size
    }
    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bind(slides[position])
    }
}

class SlideViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val tvQuestion = view.findViewById<TextView>(R.id.tvQuestion)
    private val tvAnswer = view.findViewById<TextView>(R.id.tvAnswer)
    private val backgroundImage = view.findViewById<ImageView>(R.id.imageSlide)
    fun bind(slide:Slide){
        tvQuestion.text = slide.question
        tvAnswer.text = slide.answer
        backgroundImage.setImageResource(slide.backGroundImage)
    }
}
