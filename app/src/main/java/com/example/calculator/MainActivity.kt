package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var dotValue:Boolean=true
    var plusMinsValue:Boolean=true
    var holder: String = ""
    var holder2: String = ""
    var isNewOp = true
    var oldNumber = ""
    var newNumber = ""
    var operation = ""
    private lateinit var textView_input: TextView
    private lateinit var textView_output: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView_input = findViewById(R.id.text_input)
        textView_output = findViewById(R.id.text_output)
        supportActionBar?.hide() //hide the action bar

        val clear = findViewById<Button>(R.id.btn_c)
        clear.setOnClickListener{
            dotValue=true
            textView_input.setText("")
            textView_output.setText("")
            oldNumber = ""
            newNumber = ""
            holder = ""
            holder2 = ""
            prevNum = ""
        }
    }

    fun onNumber(view: View) {
        var textbox:String = textView_output.text.toString()
        var buttonOnClick: Button = view as Button

        if (isNewOp){
            textView_output.setText("")
        }

        isNewOp = false

        when(buttonOnClick.id){
            R.id.btn_zero -> { textbox += "0" }
            R.id.btn_one -> { textbox += "1" }
            R.id.btn_two -> { textbox += "2" }
            R.id.btn_three -> { textbox += "3" }
            R.id.btn_four -> { textbox += "4" }
            R.id.btn_five -> { textbox += "5" }
            R.id.btn_six -> { textbox += "6" }
            R.id.btn_seven -> { textbox += "7" }
            R.id.btn_eight -> { textbox += "8" }
            R.id.btn_nine -> { textbox += "9" }
            R.id.btn_dot -> {
                if(dotValue) {
                    textbox += "."
                    dotValue = false
                }
            }
            R.id.btn_plusMinus ->{
                if(plusMinsValue){
                    textbox = "-$textbox"
                    plusMinsValue = false
                } else {

                    plusMinsValue = true
                }
            }
        }
        textView_output.setText(textbox)
        textView_input.setText(holder)
    }

    fun onOperator(view: View) {
        oldNumber = textView_output.text.toString()
        var buttonOnClick: Button = view as Button
        dotValue = true

        when(buttonOnClick.id){
            R.id.btn_add -> { operation = "+" }
            R.id.btn_subtract -> { operation = "-" }
            R.id.btn_multiply -> { operation = "*" }
            R.id.btn_division -> { operation = "/" }
        }
        holder = oldNumber.plus(" ").plus(operation)
        textView_input.setText(holder)

        if (!isNewOp){
            textView_output.setText("")
        }
    }

    fun equalsEve (view: View) {
        newNumber = textView_output.text.toString()
        var result:Double? = 0.0
        when (operation){
            "+" -> { result = oldNumber.toDouble() + newNumber.toDouble() }
            "-" -> { result = oldNumber.toDouble() - newNumber.toDouble() }
            "*" -> { result = oldNumber.toDouble() * newNumber.toDouble() }
            "/" -> { result = oldNumber.toDouble() / newNumber.toDouble() }
        }

        holder = oldNumber.plus(" ").plus(operation)
        holder2 = holder.plus(" ").plus(newNumber)
        textView_input.setText(holder2)

        textView_output.setText(result.toString())
    }

}