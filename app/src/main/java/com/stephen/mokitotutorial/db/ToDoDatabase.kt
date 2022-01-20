package com.stephen.mokitotutorial.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stephen.mokitotutorial.data.model.ToDoModel

/**
 * Written by StephenLeeDev on 2022/01/20.
 */

@Database(
 entities = [ToDoModel::class],
 version = 1,
 exportSchema = false
)
abstract class ToDoDatabase: RoomDatabase() {

 companion object {
  const val DB_NAME = "ToDoDataBase.db"
 }

 abstract fun toDoDao(): ToDoDao

}
