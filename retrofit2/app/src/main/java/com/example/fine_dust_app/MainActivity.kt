package com.example.fine_dust_app

import android.annotation.SuppressLint
import android.app.Activity
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fine_dust_app.data.model.GithubInfo
import com.example.fine_dust_app.data.retrofit_builder.RetrofitBuilder
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvBio : TextView = findViewById(R.id.tvBio)
        val tvUserName : TextView = findViewById(R.id.tvUserName)
        val tvUserID : TextView = findViewById(R.id.tvUserID)
        val tvFollower : TextView = findViewById(R.id.tvFollower)
        val tvFollowing : TextView = findViewById(R.id.tvFollowing)
        val profileImage: ImageView = findViewById(R.id.ivProfile)

        val call = RetrofitBuilder.githubApi.getGithubInfo()

        call.enqueue(object : Callback<GithubInfo> {

            override fun onResponse(call: Call<GithubInfo>, response: Response<GithubInfo>) {
                val userInfo = response.body()

                Log.d(
                    "response",
                    "${userInfo?.bio} ${userInfo?.login} ${userInfo?.followers} ${userInfo?.following} ${userInfo?.html_url} ${userInfo?.name}" +
                            "${userInfo?.company} ${userInfo?.location} ${userInfo?.blog} ${userInfo?.avatar_url}"
                )
                tvBio.text = userInfo?.bio.toString()
                tvUserName.text = userInfo?.name.toString()
                tvUserID.text = userInfo?.login.toString()
                tvFollower.text = "Follower : " + userInfo?.followers.toString()
                tvFollowing.text = "Following : " + userInfo?.following.toString()

                val imageURL = userInfo?.avatar_url.toString()
                getImageURL(imageURL)
            }

            override fun onFailure(call: Call<GithubInfo>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

            fun getImageURL(imageURL: String) {

                Glide.with(applicationContext)
                    .load(imageURL)
                    .into(profileImage)
            }
        })
    }
}


