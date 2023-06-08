package com.carlynj.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var tvInputTop: TextView? = null
    private var tvOperator: TextView? = null
    var lastNumeric = false
    var lastDot : Boolean = false
    var keyPress = ""
    var negativeNumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        tvInputTop = findViewById(R.id.tvInputTop)
        tvOperator = findViewById(R.id.tvOperator)
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true



    }
    fun onClear(view: View) {
        tvInput?.text=""
        tvInputTop?.text=""
        tvOperator?.text=""
        lastNumeric = false
        lastDot = false
        negativeNumber = false
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }


    fun onOperator(view :View){
        keyPress = (view as Button).text as String

        tvInput?.text?.let {
            if (!lastNumeric && keyPress == "-" && !negativeNumber) {
                tvInput?.append("-")
                negativeNumber = true

            } else if (lastNumeric) {
                tvInputTop?.append(tvInput?.text)
                tvOperator?.append((view as Button).text)
                tvInput?.text = ""
                lastNumeric = false
                lastDot = false
                negativeNumber = false
            }

        }
    }
    fun onEquals(view:View){
        var firstNumber = tvInputTop?.text.toString()
        var secondNumber = tvInput?.text.toString()
        var symbol = tvOperator?.text.toString()
        tvInputTop?.text = ""
        tvOperator?.text = ""

        var result = ""
        if (secondNumber.toDouble() == 0.0 && symbol == "/"){
            result = "No zero division"

        }else{
            when (symbol) {
                "-" -> result = (firstNumber.toDouble() - secondNumber.toDouble()).toString()
                "+" -> result = (firstNumber.toDouble() + secondNumber.toDouble()).toString()
                "*" -> result = (firstNumber.toDouble() * secondNumber.toDouble()).toString()
                "/" -> result = (firstNumber.toDouble() / secondNumber.toDouble()).toString()
            }
        }
        tvInput?.text = result
    }



}















