package com.shbhack.eggmoneyna.util

import java.text.DecimalFormat

object MoneyUtils {

    fun convertAddComma(money: Int) :String {
        val dec = DecimalFormat("#,###")
        return dec.format(money)
    }

    fun formatCurrency(input: Int, output: Int): String? {
        return when {
            input > 0 -> "+${String.format("%,d", input)}원"
            output > 0 -> "-${String.format("%,d", output)}원"
            else -> null
        }
    }

}