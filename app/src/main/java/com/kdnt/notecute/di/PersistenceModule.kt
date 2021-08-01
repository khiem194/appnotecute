package com.kdnt.notecute.di

import com.kdnt.notecute.data.database.TaskDatabase
import org.koin.dsl.module

val persistenceModule = module {
    single { TaskDatabase.getInstance(get()) }
    single { get<TaskDatabase>().taskDao() }
}