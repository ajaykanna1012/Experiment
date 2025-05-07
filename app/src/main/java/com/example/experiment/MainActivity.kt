package com.example.experiment
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.experiment.databinding.ActivityMainBinding
import com.example.experiment.databinding.ItemLayoutBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val windowBackground = window.decorView.background
        val items = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21")
        val rootView = findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
        val adapter = MyAdapter(items, rootView, windowBackground)
        binding.recyclr.adapter = adapter
        binding.recyclr.layoutManager = LinearLayoutManager(this)
    }

    class MyAdapter(private val items: List<String>,private val rootView: ViewGroup,
                    private val windowBackground: Drawable
    ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        inner class MyViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(text: String) {

                binding.blurView.setupWith(rootView) // root view of your activity
                    .setFrameClearDrawable(windowBackground)
                    .setBlurRadius(text.toFloat())            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size
    }

}