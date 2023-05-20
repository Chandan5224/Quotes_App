package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]
        setQuote(mainViewModel.getQuote())

        binding.onNext.setOnClickListener {
            nextQuote()
        }
        binding.onPrev.setOnClickListener {
            prevQuote()
        }
        binding.floatingActionButton.setOnClickListener {
            onShare()
        }

    }

    private fun setQuote(quote: Quote) {
        binding.quoteText.text = quote.text
        binding.quoteAuthor.text = quote.author
    }

    private fun nextQuote() {
        setQuote(mainViewModel.nextQuote())
    }

    private fun prevQuote() {
        setQuote(mainViewModel.previousQuote())
    }

    fun onShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plan"
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}