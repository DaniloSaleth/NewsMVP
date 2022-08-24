package com.example.praticamvp.model

class NewsResponse (
    val status : String,
    val totalResults : Int,
    val articles : MutableList<Article>
    )