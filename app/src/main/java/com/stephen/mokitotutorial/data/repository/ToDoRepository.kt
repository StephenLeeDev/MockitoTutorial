package com.stephen.mokitotutorial.data.repository

import com.stephen.mokitotutorial.data.model.ToDoModel

/**
 * Written by StephenLeeDev on 2022/01/19.
 */

interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoModel>

    suspend fun insertToDoList(toDoList: List<ToDoModel>)

}