package com.emmanuelamet.dog_info.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emmanuelamet.dog_info.model.DogBreed
import com.emmanuelamet.dog_info.model.DogsApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromRemote()
        /*
        val dog1 = DogBreed("1", "Coggy", "12 years", "Spark", "breadFor", "Fast", "ksmbslfjn")
        val dog2 = DogBreed("1", "Coggy", "12 years", "Spark", "breadFor", "Fast", "ksmbslfjn")
        val dog3 = DogBreed("1", "Coggy", "12 years", "Spark", "breadFor", "Fast", "ksmbslfjn")
        val dog4 = DogBreed("1", "Coggy", "12 years", "Spark", "breadFor", "Fast", "ksmbslfjn")
        val dog5 = DogBreed("1", "Coggy", "12 years", "Spark", "breadFor", "Fast", "ksmbslfjn")

        val doglist = arrayListOf<DogBreed>(dog1, dog2, dog3, dog4, dog5)
        dogs.value = doglist
        dogsLoadError.value = false
        loading.value = false

         */
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(t: List<DogBreed>) {
                        dogs.value = t
                        dogsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}