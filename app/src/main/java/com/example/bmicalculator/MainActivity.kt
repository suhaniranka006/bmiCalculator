package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    //1
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //2
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //step 5
        binding.calculatebtn.setOnClickListener{
            calculateBMI()
        }




        //5 step logic
        //1. setup binding
        //2. create calcualte bmi method
        //3.bmi formula and category
        //4. set output in result
        //5. call calculate bmi method in setonclicklistener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //step 2
    private fun calculateBMI(){
        val weight = binding.weightedit.text.toString().toFloatOrNull()
        val height = binding.heightedit.text.toString().toFloatOrNull()


        //step3
        if(weight!=null && height!=null){
            val bmi = weight/(height/100).pow(2)
            val bmiResult = String.format("%.2f",bmi)

            val bmiCategory = when {
                bmi < 18.5 -> "Underweight"

                bmi < 25 -> "Normal Wight"
                bmi < 30 -> "OverWeight"
                else -> "obese"
            }

            val Suggestion = when {
                bmi < 18.5 -> {
                    val rem = 18.5 - bmi
                    val weightToGain = rem * (height/100).pow(2)
                    "You need to gain ${String.format("%.2f", weightToGain)} kg... have a healthy diet"
                }
                bmi > 24.9 -> {
                    val rem = bmi - 24.9
                    val weightToLose = rem * (height/100).pow(2)
                    "You need to lose ${String.format("%.2f", weightToLose)} kg... Try to amke a workout routine!"
                }
                else -> "You are within a healthy weight range..enjoy!"
            }
            //step 5
            binding.resulttext.text = "BMI: $bmiResult\nCategory: $bmiCategory\nInfo: $Suggestion"
        }
        else{
            binding.resulttext.text = "Invalid "
        }



    }
}