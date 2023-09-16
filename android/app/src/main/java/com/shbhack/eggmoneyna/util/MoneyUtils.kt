package com.shbhack.eggmoneyna.util

import java.text.DecimalFormat

object MoneyUtils {

    fun convertAddComma(money: Int) :String {
        val dec = DecimalFormat("#,###")
        return dec.format(money)
    }
}