package com.example.trym2

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.trym2.Model.User
import kotlinx.android.synthetic.main.new_game_page.*











// ******* NOT USING ANYMORE **********

















class NewGamePage : AppCompatActivity() {

    var username: String? = null
    var difficultyOption: String? = null
    var toast: Toast? = null
    val player = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game_page)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        userName.setOnEditorActionListener { textView, action, event ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_DONE) {
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(userName.windowToken, 0)
                handled = true
            }
            handled
        }

        val spinner = spinner_result_new

        val adapter = ArrayAdapter(
            this, R.layout.custom_spinner, resources.getStringArray(R.array.difficulties)
        )
        adapter.setDropDownViewResource(R.layout.custom_dropdown)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                l: Long
            ) {
                val selectedItem = spinner.getItemAtPosition(position).toString()
                //stores the difficulty choice
                difficultyOption = selectedItem
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    fun startNewGame(v: View) {
        val getUserName = userName.text.toString().trim()
        if (getUserName.isNotEmpty()) {
            username = getUserName
            player.setName(username as String)

            when {
                difficultyOption.equals("EASY") -> {
                    player.setDifficulty(1)
                }
                difficultyOption.equals("MED") -> {
                    player.setDifficulty(2)
                }
                difficultyOption.equals("HARD") -> {
                    player.setDifficulty(3)
                }
            }

            //   Toast.makeText(applicationContext, "difficulty: " + player.getDifficulty().toString(), Toast.LENGTH_LONG).show()

            val intent = Intent(this, ShopPage::class.java)
            intent.putExtra("player", player)
            startActivity(intent)

        } else {
            Toast.makeText(applicationContext, "NAME REQUIRED", Toast.LENGTH_LONG).show()
        }
    }

}


