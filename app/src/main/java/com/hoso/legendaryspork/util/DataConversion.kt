package com.hoso.legendaryspork.util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

val gson = GsonBuilder().create()

inline fun <reified T> String.toDataClassList(): List<T> {
    val list: List<Map<String, Any>> = gson.fromJson(this, object : TypeToken<List<Map<String, Any>>>() {}.type)
    return list.map { obj -> obj.toDataClass() }
}

inline fun <reified T> String.toDataClass(): T {
    return gson.fromJson(this, object : TypeToken<T>() {}.type)
}

inline fun <reified T> Map<String, Any>.toDataClass(): T {
    return convert()
}

inline fun <I, reified O> I.convert(): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}
