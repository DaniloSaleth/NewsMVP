package com.example.praticamvp.presenter.news

import com.example.praticamvp.model.NewsResponse

interface NewsHome {
    interface Presenter{
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message : String)
        fun onComplete()
    }
}