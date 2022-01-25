package com.stephen.mokitotutorial.data.repository

import com.stephen.mokitotutorial.data.model.ToDoModel

/**
 * Written by StephenLeeDev on 2022/01/19.
 */

interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoModel>

    suspend fun getToDoItem(id: Long): ToDoModel?

    suspend fun insertToDoItem(toDoModel: ToDoModel): Long

    suspend fun insertToDoList(toDoList: List<ToDoModel>)

    suspend fun updateToDoItem(toDoModel: ToDoModel)

    suspend fun deleteToDoItem(id: Long)

    suspend fun deleteAll()

}