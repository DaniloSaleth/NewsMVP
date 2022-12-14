package com.example.praticamvp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praticamvp.R
import com.example.praticamvp.adapter.MainAdapter
import com.example.praticamvp.databinding.ActivityMainBinding
import com.example.praticamvp.databinding.ItemNewsBinding
import com.example.praticamvp.model.Article
import com.example.praticamvp.model.data.NewsDataSource
import com.example.praticamvp.presenter.ViewHome
import com.example.praticamvp.presenter.news.NewsPresenter

class MainActivity : AppCompatActivity(), ViewHome.View {

    private lateinit var adapter: MainAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var presenter: NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        adapter = MainAdapter()
        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.rvNews.addItemDecoration(
            DividerItemDecoration(
                this,DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun showProgressBar() {
        binding.pbNews.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun hideProgressBar() {
        binding.pbNews.visibility = View.INVISIBLE
    }

    override fun showArticles(article: List<Article>) {
        adapter.differ.submitList(article.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_menu ->{
                val intent = Intent(this,SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.favorite ->{
                val intent = Intent(this,FavoritesActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}