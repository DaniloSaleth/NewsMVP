package com.example.praticamvp.ui

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praticamvp.R
import com.example.praticamvp.adapter.MainAdapter
import com.example.praticamvp.databinding.ActivityMainBinding
import com.example.praticamvp.databinding.ActivitySearchBinding
import com.example.praticamvp.model.Article
import com.example.praticamvp.model.data.NewsDataSource
import com.example.praticamvp.presenter.ViewHome
import com.example.praticamvp.presenter.news.NewsPresenter
import com.example.praticamvp.presenter.search.SearchPresenter

class SearchActivity : AppCompatActivity(), ViewHome.View {
    private lateinit var adapter: MainAdapter
    private lateinit var binding : ActivitySearchBinding
    private lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = NewsDataSource()
        presenter = SearchPresenter(this, dataSource)

        binding.svSearch.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (currentFocus != null) {
                    val inputMethodManager: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                }
                Log.v("teste_search", ""+textView.text.toString())
                presenter.search(textView.text.toString())
            }
            false
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        adapter = MainAdapter()
        binding.rvSearch.adapter = adapter
        binding.rvSearch.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        binding.rvSearch.addItemDecoration(
            DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun showProgressBar() {
        binding.pbSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT)
    }

    override fun hideProgressBar() {
        binding.pbSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(article: List<Article>) {
        adapter.differ.submitList(article)
    }
}