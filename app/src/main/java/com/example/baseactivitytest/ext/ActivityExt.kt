package com.example.baseactivitytest.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 *  author : wsy
 *  date   : 2023/1/6
 *  desc   : cope by jeff
 */
inline fun <reified T : Activity> Activity.startKtxActivity(
    flags: Int? = null,
    extra: Bundle? = null,
    value: Pair<String, Any>? = null,
    values: Collection<Pair<String, Any>?>? = null
) {
    val list = ArrayList<Pair<String, Any>?>()
    value?.let { list.add(it) }
    values?.let { list.addAll(it) }
    startActivity(getIntent<T>(flags, extra, list))
}

inline fun <reified T : Context> Context.getIntent(
    flags: Int? = null,
    extra: Bundle? = null,
    pairs: List<Pair<String, Any>?>? = null
): Intent = Intent(this, T::class.java).apply {
    flags?.let { setFlags(it) }
    extra?.let { putExtras(it) }
    pairs?.let {
        for (pair in it) {
            pair?.let {
                val name = pair.first
                when (val value = pair.second) {
                    is Int -> putExtra(name, value)
                    is Byte -> putExtra(name, value)
                    is Char -> putExtra(name, value)
                    is Short -> putExtra(name, value)
                    is Boolean -> putExtra(name, value)
                    is Long -> putExtra(name, value)
                    is Float -> putExtra(name, value)
                    is Double -> putExtra(name, value)
                    is String -> putExtra(name, value)
                    is CharSequence -> putExtra(name, value)
                    is Parcelable -> putExtra(name, value)
                    is Array<*> -> putExtra(name, value)
                    is ArrayList<*> -> putExtra(name, value)
                    is Serializable -> putExtra(name, value)
                    is BooleanArray -> putExtra(name, value)
                    is ByteArray -> putExtra(name, value)
                    is ShortArray -> putExtra(name, value)
                    is CharArray -> putExtra(name, value)
                    is IntArray -> putExtra(name, value)
                    is LongArray -> putExtra(name, value)
                    is FloatArray -> putExtra(name, value)
                    is DoubleArray -> putExtra(name, value)
                    is Bundle -> putExtra(name, value)
                    is Intent -> putExtra(name, value)
                    else -> {
                    }
                }
            }
        }
    }
}