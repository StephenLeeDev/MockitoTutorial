package com.stephen.mokitotutorial.data.repository

import com.stephen.mokitotutorial.data.model.ToDoModel

/**
 * Written by StephenLeeDev on 2022/01/20.
 */

class TestToDoRepository: ToDoRepository {

    private val toDoList: MutableList<ToDoModel> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoModel> {
        return toDoList
    }

    override suspend fun getToDoItem(id: Long): ToDoModel? {
        return toDoList.find { it.id == id }
    }

    override suspend fun insertToDoItem(toDoModel: ToDoModel): Long {
        this.toDoList.add(toDoModel)
        return toDoModel.id
    }

    override suspend fun insertToDoList(toDoList: List<ToDoModel>) {
        this.toDoList.addAll(toDoList)
    }

    override suspend fun updateToDoItem(toDoModel: ToDoModel) {
        val foundToDoModel = toDoList.find { it.id == toDoModel.id }
        this.toDoList[toDoList.indexOf(foundToDoModel)] = toDoModel
    }

    override suspend fun deleteToDoItem(id: Long) {
        val foundToDoModel = toDoList.find { it.id == id }
        this.toDoList.removeAt(toDoList.indexOf(foundToDoModel))
    }

    override suspend fun deleteAll() {
        this.toDoList.clear()
    }

}
