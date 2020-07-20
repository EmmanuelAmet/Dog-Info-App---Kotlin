package com.emmanuelamet.dog_info.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emmanuelamet.dog_info.model.DogBreed

class DetailViewModel : ViewModel() {
    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetchData(){
        val dog = DogBreed("1", "Coggy", "12 years", "Spark", "breadFor", "Fast", "ksmbslfjn")
        dogLiveData.value = dog
    }
}