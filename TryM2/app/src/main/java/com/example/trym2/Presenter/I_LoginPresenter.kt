package com.example.trym2.Presenter

interface I_LoginPresenter {

    fun setDifficulty(difficulty: String)
    fun login(name : String)
    fun checkUserName(userName : String) : Boolean
    fun checkDifficulty() : Boolean
}