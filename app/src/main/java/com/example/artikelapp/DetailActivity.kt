package com.example.artikelapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.artikelapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = this.intent
        if (intent != null) {
            val intentImage = intent.getIntExtra("image_receive", R.drawable.terobosan_kemendikbud)
            val intentTitle = intent.getStringExtra("title_receive")
            val intentDescription = intent.getStringExtra("description_receive")
            binding.imageReceive.setImageResource(intentImage)
            binding.titleReceive.text = intentTitle
            binding.descriptionReceive.text = intentDescription
        }

        binding.btnBack.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                val backMainActivity = Intent(this, MainActivity::class.java)
                startActivity(backMainActivity)
            }
        }
    }
}
