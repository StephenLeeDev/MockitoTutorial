package com.stephen.mokitotutorial.domain.todo

import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.data.repository.ToDoRepository
import com.stephen.mokitotutorial.domain.UseCase

internal class InsertToDoListUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(toDoList: List<ToDoModel>) {
        return toDoRepository.insertToDoList(toDoList)
    }

}
