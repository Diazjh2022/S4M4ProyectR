package com.diegocampos.ejercicioviewmodelnoticiasapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s4m4proyectr.databinding.ActivityNoticiasGeneralesBinding
import com.example.s4m4proyectr.repository.recycler.AdaptadorRecycler
import com.example.s4m4proyectr.repository.recycler.EtiquetaNoticia
import com.example.s4m4proyectr.viewmodel.ViewModel


class NoticiasGenerales : AppCompatActivity() {
    private lateinit var mainViewModel: ViewModel
    private lateinit var binding: ActivityNoticiasGeneralesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiasGeneralesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        observar()

        binding.btnTraerNoticias.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            mainViewModel.onBtnTraerNoticias()

        }

    }

    private fun observar() {
        mainViewModel.noticias.observe(this, Observer {

            binding.myRecyclerView.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            var listaRcycler: ArrayList<EtiquetaNoticia> = ArrayList()

            for((i,v) in it.articles.withIndex()){
                listaRcycler.add( EtiquetaNoticia(v.title, it.articles[i].urlToImage, it.articles[i].description))
            }

            var adaptador: AdaptadorRecycler = AdaptadorRecycler(applicationContext, listaRcycler)
            binding.myRecyclerView.adapter = adaptador

            binding.progressBar.visibility = View.GONE
        })
    }
}