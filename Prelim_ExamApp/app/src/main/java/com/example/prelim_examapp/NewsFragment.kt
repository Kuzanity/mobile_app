package com.example.prelim_examapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prelim_examapp.databinding.FragmentNewsBinding
import com.example.prelim_examapp.news.QuizNews
import com.example.prelim_examapp.news.QuizNewsResponse
import com.example.prelim_examapp.news.QuizNewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {
    lateinit var newCall:QuizNewsService
    lateinit var mLayout:LinearLayoutManager
    lateinit var nadapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNewsBinding>(inflater,
            R.layout.fragment_news, container, false)
        val recyclerview:RecyclerView = binding.recyclerView
        mLayout = LinearLayoutManager(this.context)
        recyclerview.layoutManager = mLayout

        newCall = QuizNews.quizNewsService
        newCall.getCurrentNews(query, api_key).enqueue(object :
            Callback<QuizNewsResponse> {
            override fun onResponse(
                call: Call<QuizNewsResponse>,
                response: Response<QuizNewsResponse>
            ) {
                nadapter = NewsAdapter(response.body() as QuizNewsResponse, click)
                nadapter.notifyDataSetChanged()

                recyclerview.adapter = nadapter
            }

            override fun onFailure(call: Call<QuizNewsResponse>, t: Throwable) {
                Log.v("retrofit", "error")
            }

        })
        return binding.root
    }

    companion object {
        var api_key ="c9c60ad6739b4b649f82fa6f9f34a5df"
        var query = "quiz"
        var category = "education"
    }
    private val click = NewsAdapter.OnClickListener{items ->
        val intent: Intent = Intent(ACTION_VIEW, Uri.parse(items.url))
        startActivity(intent)
    }
}