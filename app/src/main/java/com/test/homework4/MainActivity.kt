//package com.test.homework4
//
//import android.app.Activity
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import com.test.homework4.SetImgActivity.Companion.IMG_ID
//import com.test.homework4.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    companion object {
//        const val IMG_ID_MAIN = "img_id"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.img.setImageResource(savedInstanceState?.getInt(IMG_ID_MAIN)?: R.drawable.one)
//
//        binding.btn.setOnClickListener {
//            startForResult.launch(Intent(this, SetImgActivity::class.java))
//        }
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        binding.img.tag?.let {
//            outState.putInt(IMG_ID_MAIN, binding.img.tag as Int)
//        }
//    }
//
//    private val startForResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val intent = result.data
//                intent?.let {
//                    when (intent.getIntExtra(IMG_ID, 0)) {
//                        1 -> {
//                            binding.img.setImageResource(R.drawable.one)
//                            binding.img.tag = R.drawable.one
//                        }
//                        2 -> {
//                            binding.img.setImageResource(R.drawable.two)
//                            binding.img.tag = R.drawable.two
//                        }
//                        3 -> {
//                            binding.img.setImageResource(R.drawable.three)
//                            binding.img.tag = R.drawable.three
//                        }
//                    }
//                }
//            }
//        }
//}