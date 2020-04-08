package com.alexeykatsuro.themestylesample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alexeykatsuro.themestylesample.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dynamic Theme assignment
        setTheme(R.style.Theme_MyApp_Pro)
        super.onCreate(savedInstanceState)
        //Use ViewBinding to avoid findViewById
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.root)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // this part hides the button immediately and waits bottom sheet
                // to collapse to show

                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING,
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.buttonChat.animate().scaleX(0f).scaleY(0f).setDuration(300).start();
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.buttonChat.animate().scaleX(1f).scaleY(1f).setDuration(300).start();
                    }
                }
            }
        })

        val onPeekViewClick = View.OnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.bottomSheet.apply {
            labelForgetPassword.setOnClickListener(onPeekViewClick)
            iconDraw.setOnClickListener(onPeekViewClick)

            buttonRecover.setOnClickListener {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

    }
}
