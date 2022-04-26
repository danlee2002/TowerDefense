package com.example.trym2.View

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
import com.example.trym2.Presenter.I_LoginPresenter
import com.example.trym2.Presenter.LoginPresenter
import com.example.trym2.R
import com.example.trym2.ShopPage

// *** Maybe need to delete this
import com.example.trym2.Model.User

// *** NewGamePage -> View ***

class LoginView : AppCompatActivity(), I_LoginView {

// userName, spinner_result_new

    private lateinit var userName : EditText
    private lateinit var spinner : Spinner
    private lateinit var button : Button
    lateinit var presenter : I_LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game_page)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        initPresenter()
        findView()
        setActionListener()
        itemSelectListener()
        setOnClickListener()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun initPresenter() {
        presenter = LoginPresenter(this)
    }

    private fun findView() {
        userName = findViewById(R.id.userName)
        spinner = findViewById(R.id.spinner_result_new)
        button = findViewById(R.id.loginButton) // If this works well, we need to delete 'onClick()' on the new_game_page.xml
    }

    private fun setActionListener() {
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
    }

    private fun itemSelectListener() {
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
                //difficultyOption = selectedItem

                // ** Go to Presenter **
                presenter.setDifficulty(selectedItem)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    private fun setOnClickListener() {
        button.setOnClickListener {
            presenter.login(userName.text.toString().trim())
        }
    }

    // *** Not sure with this + I have no idea with passing the data which is stored in the Presenter
    fun goToShopPage(player : User) {
        //Toast.makeText(applicationContext, player.getName() + " : " + player.getDifficulty(), Toast.LENGTH_LONG).show()
        val intent = Intent(this, ShopView::class.java)
        intent.putExtra("player", player)
        startActivity(intent)
    }
}