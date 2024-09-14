package com.example.example

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://vu-nit3213-api.onrender.com/"

class MainActivity : AppCompatActivity() {
    private lateinit var txtArtist: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txtArtist = findViewById(R.id.txtArtist)
        getEntity();
    }

    private fun getEntity() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<Entity>?> {
            override fun onResponse(call: Call<List<Entity>?>, response: Response<List<Entity>?>) {
                val responseArtist = response.body()!!

                val myStringBuilder = StringBuilder()

                for (myData in responseArtist) {
                    myStringBuilder.append(myData.artist)
                    myStringBuilder.append("\n")
                }
                txtArtist.text = myStringBuilder
            }

            override fun onFailure(call: Call<List<Entity>?>, t: Throwable) {
                println("Request failed: ${t.message}")
            }
        })

    }
}