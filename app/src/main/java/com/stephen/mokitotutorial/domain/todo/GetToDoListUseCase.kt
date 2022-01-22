package com.stephen.mokitotutorial.domain.todo

import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.data.repository.ToDoRepository
import com.stephen.mokitotutorial.domain.UseCase

internal class GetToDoListUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(): List<ToDoModel> {
        return toDoRepository.getToDoList()
    }

}
