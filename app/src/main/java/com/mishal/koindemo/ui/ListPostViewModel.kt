package com.mishal.koindemo.ui

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mishal.koindemo.data.NetworkUtils
import com.mishal.koindemo.data.model.Repo
import com.mishal.koindemo.data.rest.RepoService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListPostViewModel(private val repoService: RepoService) : ViewModel() {
    private var compositeDisposable: CompositeDisposable? = null
    var repos = MutableLiveData<Repo>()
    var repoLoadError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    var pageNumber = 1

    init {
        compositeDisposable = CompositeDisposable()
//        fetchRepos()
    }

    fun getRepos(): LiveData<Repo> {
        return repos
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    fun getError(): LiveData<Boolean> {
        return repoLoadError
    }

    fun fetchRepos() {
        loading.value = true
        val repoDisposable = repoService.getRepositories("3fb9e52684706743cdb30b4b494b294c", pageNumber).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                DisposableSingleObserver<Repo>() {
                override fun onSuccess(t: Repo) {
                    repos.value = t
                    loading.value = false
                    repoLoadError.value = false
                    pageNumber++
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    e.printStackTrace()
                    repoLoadError.value = true
                }
            })
        compositeDisposable?.add(repoDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
        compositeDisposable = null
    }


    fun checkEmail(strEmail: String): Boolean {
        return if (strEmail.isEmpty()) {
            false
        } else Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()
    }

    fun checkPassword(strPassword: String): Boolean {
        return strPassword.isNotEmpty()
    }
}