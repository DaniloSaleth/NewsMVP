package com.example.praticamvp.presenter.news

import com.example.praticamvp.model.NewsResponse
import com.example.praticamvp.model.data.NewsDataSource
import com.example.praticamvp.presenter.ViewHome

class NewsPresenter(val view : ViewHome.View, private val dataSource : NewsDataSource) : NewsHome.Presenter{

    override fun requestAll() {
        view.showProgressBar()
        dataSource.getBreakingNews(this)
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