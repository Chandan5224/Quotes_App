package com.example.quotesapp

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]
    fun nextQuote(): Quote {
        if (index == quoteList.size - 1) {
            index = 0
            return quoteList[index]
        }
        return quoteList[++index]
    }

    fun previousQuote(): Quote {
        if (index == 0) {
            index = quoteList.size - 1
            return quoteList[index]
        }
        return quoteList[--index]
    }

}