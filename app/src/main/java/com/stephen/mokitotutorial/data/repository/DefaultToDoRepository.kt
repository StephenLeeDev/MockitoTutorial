package com.stephen.mokitotutorial.data.repository

import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.db.ToDoDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Written by StephenLeeDev on 2022/01/20.
 */

class DefaultToDoRepository(
    private val toDoDao: ToDoDao,
    private val ioDispatcher: CoroutineDispatcher
): ToDoRepository {

    override suspend fun getToDoList(): List<ToDoModel> = withContext(ioDispatcher) {
        toDoDao.getAll()
    }

    override suspend fun getToDoItem(id: Long): ToDoModel? = withContext(ioDispatcher) {
        toDoDao.getById(id)
    }

    override suspend fun insertToDoItem(toDoModel: ToDoModel): Long = withContext(ioDispatcher) {
        toDoDao.insert(toDoModel)
    }

    override suspend fun insertToDoList(toDoList: List<ToDoModel>) = withContext(ioDispatcher) {
        toDoDao.insert(toDoList)
    }

    override suspend fun updateToDoItem(toDoModel: ToDoModel) = withContext(ioDispatcher) {
        toDoDao.update(toDoModel)
    }

    override suspend fun deleteToDoItem(id: Long) = withContext(ioDispatcher) {
        toDoDao.delete(id)
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        toDoDao.deleteAll()
    }

}
