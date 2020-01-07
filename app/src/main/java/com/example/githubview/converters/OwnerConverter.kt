package com.example.githubview.converters

import androidx.room.TypeConverter
import com.example.githubview.entities.Owner

class OwnerConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toOwner(login: String) = Owner(login)

        @TypeConverter
        @JvmStatic
        fun fromOwner(owner: Owner) = owner.login
    }
}