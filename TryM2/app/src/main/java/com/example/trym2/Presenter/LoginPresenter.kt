package com.example.trym2.Presenter

import com.example.trym2.Model.User
import com.example.trym2.View.LoginView

class LoginPresenter() : I_LoginPresenter {

    private lateinit var userName : String
    private var difficulty: Int = 0
    lateinit var player : User
    lateinit var loginview: LoginView

    constructor(loginview: LoginView) : this() {
        this.loginview = loginview
    }

    override fun setDifficulty(difficulty: String) {
        when {
            difficulty.equals("EASY") -> {
                this.difficulty = 1
            }
            difficulty.equals("MED") -> {
                this.difficulty = 2
            }
            difficulty.equals("HARD") -> {
                this.difficulty = 3
            }
            else -> {
                loginview.showErrorMessage("Difficulty Error")
            }
        }
    }

    override fun login(name: String) {
        if (checkUserName(name) && checkDifficulty()) {
            player = User(name, difficulty)
            // *** I don't know if this is the right way to pass the data to the View ***
            loginview.goToShopPage(player)
        } else {
            loginview.showErrorMessage("Valid Name Required")
        }
    }

    override fun checkUserName(userName : String) : Boolean {
        if (userName.isNotEmpty()) {
            this.userName = userName
            return true
        }
        return false
    }

    override fun checkDifficulty() : Boolean {
        if (this.difficulty == 1 || this.difficulty == 2 || this.difficulty == 3) {
            return true
        }
        return false
    }
}