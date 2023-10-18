package com.example.artikelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvArticles : RecyclerView
    private val list = ArrayList<Articles>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvArticles = findViewById(R.id.rv_article)
        rvArticles.setHasFixedSize(true)

        list.addAll(getListArticle())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_user -> {
                val goToAbout = Intent(this, AboutActivity::class.java)
                startActivity(goToAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListArticle(): ArrayList<Articles> {
        val dataName = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listArticle = ArrayList<Articles>()
        for (i in dataName.indices) {
            val article = Articles(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listArticle.add(article)
        }
        return listArticle
    }

    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(this)
        val listArticleAdapter = ListArticleAdapter(list)
        rvArticles.adapter = listArticleAdapter

        listArticleAdapter.setOnItemClickCallback(object : ListArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Articles) {
                moveSelectedArticle(data)
            }
        })
    }

    private fun moveSelectedArticle(article: Articles) {
        startActivity(
            Intent(this@MainActivity, DetailActivity::class.java)
                .putExtra("image_receive", article.photo)
                .putExtra("title_receive", article.title)
                .putExtra("description_receive", article.description)
        )
    }


}