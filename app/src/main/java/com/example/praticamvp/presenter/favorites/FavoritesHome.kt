package com.example.praticamvp.presenter.favorites

import com.example.praticamvp.model.Article

interface FavoritesHome {
    fun showArticles(article: List<Article>)
}