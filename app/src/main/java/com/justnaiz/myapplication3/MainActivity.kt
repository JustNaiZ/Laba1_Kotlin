package com.justnaiz.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var inputTextView: TextView
    private lateinit var thresholdEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var processButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupButtonClickListener()

        setupHideKeyboardOnTouch()

        val inputText = generateInputText()
        inputTextView.text = inputText
    }

    private fun initViews() {
        inputTextView = findViewById(R.id.inputTextView)
        thresholdEditText = findViewById(R.id.thresholdEditText)
        resultTextView = findViewById(R.id.resultTextView)
        processButton = findViewById(R.id.processButton)
    }

    private fun setupButtonClickListener() {
        processButton.setOnClickListener {
            hideKeyboard()

            try {
                val thresholdText = thresholdEditText.text.toString()

                if (thresholdText.isBlank()) {
                    resultTextView.text = "Ошибка: введите число!"
                    return@setOnClickListener
                }

                val threshold = thresholdText.toInt()
                val inputText = generateInputText()

                val (longerWords, shorterWords) = splitWordsByLength(inputText, threshold)
                val result = formatResult(longerWords, shorterWords)

                resultTextView.text = result

            } catch (e: NumberFormatException) {
                resultTextView.text = "Ошибка: введите корректное число!"
            }
        }
    }

    // Функции для скрытия клавиатуры (первое - по кнопке)
    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(thresholdEditText.windowToken, 0)
    }

    // (второе - по нажатию вне клавиатуры)
    private fun setupHideKeyboardOnTouch() {
        findViewById<android.view.View>(android.R.id.content).setOnClickListener {
            hideKeyboard()
        }
    }
}
