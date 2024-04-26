package com.niichaa.culinaryapp.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.niichaa.core.domain.model.Culinary
import com.niichaa.culinaryapp.R
import com.niichaa.culinaryapp.databinding.ActivityDetailCulinaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCulinaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCulinaryBinding
    private val detailCulinaryViewModel: DetailCulinaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCulinaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailCulinary = intent.getParcelableExtra<Culinary>(EXTRA_DATA)
        supportActionBar?.setTitle(detailCulinary?.title)

        getDetailCulinary(detailCulinary)

    }

    private fun getDetailCulinary(detailCulinary: Culinary?){
        detailCulinary?.let {
            binding.tvTitle.text = it.title
            Glide.with(this@DetailCulinaryActivity)
                .load(detailCulinary.image)
                .into(binding.imgImage)
            binding.tvSummary.text = it.summary

            var favoriteStatus = detailCulinary.isFavorite
            setFavoriteStatus(favoriteStatus)
            binding.imgbFavorite.setOnClickListener {
                favoriteStatus = !favoriteStatus
                detailCulinaryViewModel.setFavoriteCulinary(detailCulinary, favoriteStatus)
                setFavoriteStatus(favoriteStatus)
            }

            val sourceUrl = it.sourceUrl
            binding.tvSourceUrl.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl))
                startActivity(intent)
            }

            val spoonSource = it.spoonacularSourceUrl
            binding.tvSpoonacularSource.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(spoonSource))
                startActivity(intent)
            }

        }
    }

    private fun setFavoriteStatus(favoriteStatus: Boolean) {
        if (favoriteStatus){
            binding.imgbFavorite.setImageResource(R.drawable.ic_favorite)
        }else{
            binding.imgbFavorite.setImageResource(R.drawable.ic_not_favorite)
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}