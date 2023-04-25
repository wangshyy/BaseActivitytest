package com.example.baseactivitytest

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.baseactivitytest.base.BaseVMActivity
import com.example.baseactivitytest.databinding.ActivityMainBinding

class MainActivity : BaseVMActivity(), View.OnClickListener {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        binding.apply {
            onClickListener = this@MainActivity
            val layoutParams2 =
                binding.text.layoutParams as ConstraintLayout.LayoutParams
            layoutParams2.height =
                resources.displayMetrics.heightPixels - (binding.recyclerView.layoutParams as ConstraintLayout.LayoutParams).height
            binding.text.layoutParams = layoutParams2
        }

        var y = 0f
        var moveY: Float
        binding.recyclerView.setOnTouchListener { _, e ->
            when (e.action) {
                MotionEvent.ACTION_DOWN -> {
                    y = e.y
                }
                MotionEvent.ACTION_MOVE -> {
                    moveY = e.y - y
                    val layoutParams1 =
                        binding.recyclerView.layoutParams as ConstraintLayout.LayoutParams
                    val layoutParams2 =
                        binding.text.layoutParams as ConstraintLayout.LayoutParams
                    when (layoutParams1.height) {
                        in 0..500 -> {
                            if (moveY > 0) {
                                return@setOnTouchListener false
                            }
                        }
                        in 1000..resources.displayMetrics.heightPixels -> {
                            if (moveY < 0) {
                                return@setOnTouchListener false
                            }
                        }
                    }

                    layoutParams1.height = (layoutParams1.height - moveY).toInt()
                    binding.recyclerView.layoutParams = layoutParams1

                    layoutParams2.height = (layoutParams2.height + moveY).toInt()
                    binding.text.layoutParams = layoutParams2

                }
                MotionEvent.ACTION_UP -> {
                    val layoutParams1 =
                        binding.recyclerView.layoutParams as ConstraintLayout.LayoutParams

                    if (layoutParams1.height > 1000) {
                        layoutParams1.height = 1000
                        binding.recyclerView.layoutParams = layoutParams1
                    }
                    if (layoutParams1.height < 500) {
                        layoutParams1.height = 500
                        binding.recyclerView.layoutParams = layoutParams1
                    }
                    val layoutParams2 =
                        binding.text.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams2.height =
                        resources.displayMetrics.heightPixels - layoutParams1.height
                    binding.text.layoutParams = layoutParams2
                }
            }
            true
        }
    }

    override fun initData() {
        viewModel.requestData()
    }

    override fun startObserve() {
        viewModel.uiState.observe(this) {
        }
    }


    override fun onClick(v: View) {
        when (v.id) {

        }
    }
}