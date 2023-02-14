package com.fcrysthian.netflixremake.util

import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class CategoryTask {

    fun execute(url: String){
        //nesse momento estamos usando a UI Thread (1)
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            try {
                //nesse momento estamos usando a NOVA Thread [processo paralelo] (2)
                val requestUrl =  URL(url) //abrir uma url
                val urlConnection = requestUrl.openConnection() as HttpURLConnection //abrir a conexao
                urlConnection.readTimeout = 2000 //tempo de leitura (2s)
                urlConnection.connectTimeout = 2000 // tempo de conexao (2s)

                val statusCode: Int = urlConnection.responseCode

                if (statusCode > 400){
                    throw IOException("Erro na comunicação com o servidor")
                }

                val stream = urlConnection.inputStream
                val jsonAsString = stream.bufferedReader().use { it.readText() } // bytes -> string
                Log.i("Teste", jsonAsString)

            }catch (e: IOException){
                Log.e("Teste", e.message ?:"erro desconhecido", e)
            }

        }
    }
}