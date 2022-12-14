package com.example.praticamvp.model.data

import com.example.praticamvp.network.RetrofitInstance
import com.example.praticamvp.presenter.news.NewsHome
import com.example.praticamvp.presenter.search.SearchHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    fun getBreakingNews(callBack : NewsHome.Presenter){
        GlobalScope.launch(Dispatchers.Main){
           val response =  RetrofitInstance.api.getBreakingNews("br")
            if (response.isSuccessful){
                response.body()?.let{
                    callBack.onSuccess(it)
                }
                callBack.onComplete()
            }else{
                callBack.onError(response.message())
                callBack.onComplete()
            }
        }
    }

    fun searchNews(term : String,callBack : SearchHome.Presenter){
        GlobalScope.launch(Dispatchers.Main){
            val response =  RetrofitInstance.api.searchNews(term)
            if (response.isSuccessful){
                response.body()?.let{
                    callBack.onSuccess(it)
                }
                callBack.onComplete()
            }else{
                callBack.onError(response.message())
                callBack.onComplete()
            }
        }
    }

}