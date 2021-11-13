package com.example.prelim_examapp.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prelim_examapp.Inquires.Inquiries
import com.example.prelim_examapp.Inquires.InquiryDao

@Database(entities = [User::class,Inquiries::class],version = 3,exportSchema = false)
abstract class LoginDatabase: RoomDatabase() {
    abstract fun inquiryDao(): InquiryDao

    abstract val loginDao: LoginDao

    companion object {
        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDatabase(context: Context): LoginDatabase {

            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoginDatabase::class.java,
                    "login_database"
                )
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
            return instance
        }
    }
    }