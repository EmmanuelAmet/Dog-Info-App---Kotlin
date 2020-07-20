package com.emmanuelamet.dog_info.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emmanuelamet.dog_info.R
import com.emmanuelamet.dog_info.model.DogBreed
import com.emmanuelamet.dog_info.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetchData()
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.dogLiveData.observe(this, Observer {
            dog ->
            dog?.let {
                dogName.text = dog.dogBreed.toString()
                dogLifeSpan.text = dog.lifeSpan.toString()
                dogPurpose.text = dog.breedFor.toString()
                dogTemperament.text = dog.temperament.toString()
            }
        })
    }

}