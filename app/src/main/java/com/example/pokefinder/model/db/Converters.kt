package com.example.pokefinder.model.db

import androidx.room.TypeConverter
import com.example.pokefinder.model.Pokemon.Type
import com.google.gson.Gson
import java.util.*
import com.google.gson.reflect.TypeToken

class Converters {

    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToSomeTypeList(data: String?): List<Type> {
            if (data == null) {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<Type>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun typeListToString(list: List<Type>): String {
            return gson.toJson(list)
        }
    }

}