package com.example.praticamvp.presenter.search

import com.example.praticamvp.model.NewsResponse
import com.example.praticamvp.model.data.NewsDataSource
import com.example.praticamvp.presenter.ViewHome

class SearchPresenter (val view : ViewHome.View, private val dataSource: NewsDataSource) : SearchHome.Presenter{
    override fun search(term: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }
}