package com.example.praticamvp.presenter

import com.example.praticamvp.model.Article

interface ViewHome {
    interface View{
        fun showProgressBar()
        fun showFailure(message : String)
        fun hideProgressBar()
        fun showArticles(article: List<Article>)
    }
}