package com.example.prelim_examapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.prelim_examapp.news.Article

import com.example.prelim_examapp.news.QuizNewsResponse
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val newsData: QuizNewsResponse,
    private val onClickListener: OnClickListener
): RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    class ListViewHolder(view:View): RecyclerView.ViewHolder(view){
        val img: ImageView = view.findViewById(R.id.newsImg)
        val title: TextView = view.findViewById(R.id.title)
        val newsId: TextView = view.findViewById(R.id.id)
        val source: TextView = view.findViewById(R.id.source)
        val newsCard: CardView = view.findViewById(R.id.newsCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val newsItems = newsData.articles[position]
        Picasso.get().load(newsItems.urlToImage).into(holder.img)

        holder.title.text = newsItems.title
        holder.source.text = newsItems.source?.name
        holder.newsId.text = newsItems.author

        holder.newsCard.setOnClickListener{
            onClickListener.onCLick(newsItems)
        }
    }

    override fun getItemCount(): Int {
       return newsData.articles.size
    }

    class OnClickListener(val click : (newsData : Article) -> Unit){
        fun onCLick(newsData: Article) =click(newsData)

    }
}
