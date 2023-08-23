package com.example.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.Home
import com.example.myapplication.adapter.HomeListItemAdapter
import com.example.myapplication.service.URLFactory
import com.example.myapplication.service.UserService
import com.google.gson.Gson
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewHomeList : RecyclerView

    lateinit var homeListItemAdapter: HomeListItemAdapter

    private var userService: UserService?=null

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    private var list = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val data = HttpLoggingInterceptor()

        data.level = HttpLoggingInterceptor.Level.HEADERS

        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(data)
            .build()


        retrofit = Retrofit.Builder()
            .baseUrl(URLFactory.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        userService = retrofit.create(UserService::class.java)
        getUserList()

        recyclerViewHomeList = findViewById(R.id.recyclerViewHomeList)

        setRecyclerViewAds()


    }

    override fun onDestroy() {
        super.onDestroy()
        userService = null
    }

    private fun setRecyclerViewAds() {
        recyclerViewHomeList.setHasFixedSize(true)
        recyclerViewHomeList.layoutManager = LinearLayoutManager(this)
        homeListItemAdapter = HomeListItemAdapter()
        homeListItemAdapter.addHomeData(getList())
        recyclerViewHomeList.adapter = homeListItemAdapter
    }

    private fun getList():ArrayList<Home>{
        return arrayListOf(
            Home("42324B424242424GHf2342342","John Deo12","500 ","Thailand","Ahmedabad ","www.apple.com","Smooth as Silk / I Fly THAI"),
            Home("42324B424242424GHf2342342","John Deo12","500 ","Thailand","Ahmedabad ","www.apple.com","Smooth as Silk / I Fly THAI"),
            Home("42324B424242424GHf2342342","John Deo12","500 ","Thailand","Ahmedabad ","www.apple.com","Smooth as Silk / I Fly THAI"),
            Home("42324B424242424GHf2342342","John Deo12","500 ","Thailand","Ahmedabad ","www.apple.com","Smooth as Silk / I Fly THAI"),
            Home("42324B424242424GHf2342342","John Deo12","500 ","Thailand","Ahmedabad ","www.apple.com","Smooth as Silk / I Fly THAI")
        )
    }

    //For GET METHOD
    private fun getUserList() {
        userService?.getUser()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<Any> {
                override fun onSuccess(t: Any) {
                    /*t.let {
                        for (i in 0..t.size){
                            list.addAll(t)
                            if (list.size == 50){
                                break
                            }
                        }
                        Log.d("userData", "User: ${Gson().fromJson<Any>(it)}")
                    }*/

                    Log.d("userData", "User: ${Gson().toJson(t)}}")
                    Toast.makeText(this@MainActivity, "hello", Toast.LENGTH_SHORT).show()
                }
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError : ${e.printStackTrace().toString()}")
                }
            })
    }
}