package com.example.trym2.Model

import android.util.DisplayMetrics


class Tile<T> {

    private val device: DisplayMetrics = DisplayMetrics()
    private val width: Int = device.widthPixels / 14
    private val height: Int = device.heightPixels / 12

    private var x: Int? = null
    private var y: Int? = null
    //device coordinates
    private var dX: Int? = 0
    private var dY: Int? = 0
    private var value: T? = null


    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
        this.dX = x * width
        this.dY = y * height


    }

    fun setValue(value: T?) {
        this.value = value
    }

    fun getdX(): Int? {
        return dX

    }

    fun getdY(): Int? {
        return dY
    }
    fun getX(): Int? {
        return x

    }

    fun getY(): Int? {
        return y
    }
}