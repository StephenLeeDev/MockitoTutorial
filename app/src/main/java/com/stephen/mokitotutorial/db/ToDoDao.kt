package com.stephen.mokitotutorial.db

import androidx.room.*
import com.stephen.mokitotutorial.data.model.ToDoModel

/**
 * Written by StephenLeeDev on 2022/01/20.
 */

@Dao
interface ToDoDao {

 @Query("SELECT * FROM ToDoModel")
 suspend fun getAll(): List<ToDoModel>

 @Query("SELECT * FROM ToDoModel WHERE id=:id")
 suspend fun getById(id: Long): ToDoModel?

 @Insert
 suspend fun insert(toDoModel: ToDoModel): Long

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insert(toDoModelList: List<ToDoModel>)

 @Query("DELETE FROM ToDoModel WHERE id=:id")
 suspend fun delete(id: Long)

 @Query("DELETE FROM ToDoModel")
 suspend fun deleteAll()

 @Update
 suspend fun update(toDoModel: ToDoModel)

}
