package com.example.praticamvp.presenter.search

import com.example.praticamvp.model.NewsResponse
import com.example.praticamvp.model.data.NewsDataSource
import com.example.praticamvp.presenter.ViewHome

class SearchPresenter (val view : ViewHome.View, private val dataSource: NewsDataSource) : SearchHome.Presenter{
    override fun search(term: String) {
        dataSource.searchNews(term, this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        view.showFailure(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }
}