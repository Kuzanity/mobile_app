package com.example.prelim_examapp.news

import com.google.gson.annotations.SerializedName

class QuizNewsResponse {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("totalResult")
    var totalResult: Int = 0
    @SerializedName("articles")
    var articles = ArrayList<Article>()
}
class Article {
    @SerializedName("source")
    var source: Source? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("urlToImage")
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    var publishedAt: String? = null
    @SerializedName("content")
    var content: String? = null
}

class Source {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null
}