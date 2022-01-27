package com.stephen.mokitotutorial.di

import android.content.Context
import androidx.room.Room
import com.stephen.mokitotutorial.data.repository.DefaultToDoRepository
import com.stephen.mokitotutorial.data.repository.ToDoRepository
import com.stephen.mokitotutorial.db.ToDoDatabase
import com.stephen.mokitotutorial.domain.todo.*
import com.stephen.mokitotutorial.domain.todo.DeleteToDoItemUseCase
import com.stephen.mokitotutorial.domain.todo.GetToDoItemUseCase
import com.stephen.mokitotutorial.domain.todo.GetToDoListUseCase
import com.stephen.mokitotutorial.domain.todo.InsertToDoListUseCase
import com.stephen.mokitotutorial.domain.todo.InsertToDoUseCase
import com.stephen.mokitotutorial.presenter.detail.DetailMode
import com.stephen.mokitotutorial.presenter.detail.DetailViewModel
import com.stephen.mokitotutorial.presenter.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Written by StephenLeeDev on 2022/01/17.
 */

internal val diModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }

    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get(), get()) }

}

internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()