package fr.eni.ecole.eni_shop.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFrenchFormat() : String{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    return dateFormat.format(this)

}