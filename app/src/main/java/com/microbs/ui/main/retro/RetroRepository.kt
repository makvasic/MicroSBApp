package com.microbs.ui.main.retro

import android.content.Context
import com.microbs.database.AppDatabase
import com.microbs.model.Retro
import com.microbs.network.WebApi
import com.microbs.network.WebApiFactory

class RetroRepository private constructor(appContext: Context) {

    private val webApi: WebApi = WebApiFactory.webApi
    private val database: AppDatabase = AppDatabase.getInstance(appContext)

    suspend fun getRetrosForUser(gitUsername: String): List<Retro> {

        val retroDao = database.retroDao()

        val retrosCount = retroDao.getRetrosCount()
        if (retrosCount == 0) {
            val retros: List<Retro>? = webApi.getRetrosForUser(gitUsername)

            if (!retros.isNullOrEmpty()) {
                retroDao.insert(retros)
            }
        }

        return retroDao.getRetros()

    }

    companion object {

        private var instance: RetroRepository? = null

        fun getInstance(context: Context): RetroRepository {
            if (instance == null) {
                instance = RetroRepository(context.applicationContext)
            }

            return instance!!
        }
    }


}