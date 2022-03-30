//package com.test.homework4
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.test.homework4.databinding.ActivitySetImgBinding
//
//class SetImgActivity : AppCompatActivity() {
//    private lateinit var binding: ActivitySetImgBinding
//
//    companion object {
//        const val IMG_ID = "img_id"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySetImgBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupActionBar()
//
//        binding.btn1Img.setOnClickListener {
//            setResult(RESULT_OK, Intent().putExtra(IMG_ID, 1))
//            finish()
//        }
//        binding.btn2Img.setOnClickListener {
//            setResult(RESULT_OK, Intent().putExtra(IMG_ID, 2))
//            finish()
//        }
//        binding.btn3Img.setOnClickListener {
//            setResult(RESULT_OK, Intent().putExtra(IMG_ID, 3))
//            finish()
//        }
//    }
//    override fun onSupportNavigateUp(): Boolean {
//        finish()
//        return true
//    }
//    private fun setupActionBar() {
//        supportActionBar?.apply{
//            setDisplayShowHomeEnabled(true)
//            setDisplayHomeAsUpEnabled(true)
//        }
//    }
//
//}