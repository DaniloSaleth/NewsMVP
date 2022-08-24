package com.example.praticamvp.presenter.search

import com.example.praticamvp.model.NewsResponse

interface SearchHome {
    interface Presenter{
        fun search(term : String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message : String)
        fun onComplete()
    }
}