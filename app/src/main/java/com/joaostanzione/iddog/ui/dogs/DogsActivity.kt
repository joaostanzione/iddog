package com.joaostanzione.iddog.ui.dogs

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.tabs.TabLayout
import com.joaostanzione.iddog.R
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.android.synthetic.main.dialog_dog_photo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogsActivity : AppCompatActivity(), ItemClick {

    private val viewModel: DogsViewModel by viewModel()
    private lateinit var glide: RequestManager
    private lateinit var adapter: DogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)
        val token = intent.getStringExtra(EXTRA_KEY_TOKEN)
        glide = Glide.with(this)

        setupTabs(token)
        setupRecyclerView()
        initializeObserving()
        viewModel.getDogsByCategory(getString(R.string.menu_labrador), token)
    }

    private fun setupTabs(token: String?) {
        dogsTabs.addTab(dogsTabs.newTab().setText(getString(R.string.menu_labrador)))
        dogsTabs.addTab(dogsTabs.newTab().setText(getString(R.string.menu_husky)))
        dogsTabs.addTab(dogsTabs.newTab().setText(getString(R.string.menu_hound)))
        dogsTabs.addTab(dogsTabs.newTab().setText(getString(R.string.menu_pug)))
        dogsTabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                clearScreen()
                viewModel.getDogsByCategory(tab.text.toString(), token)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) { /*do nothing*/ }
            override fun onTabReselected(tab: TabLayout.Tab) { /*do nothing*/ }
        })
    }

    private fun setupRecyclerView() {
        adapter = DogsAdapter(glide, this)
        dogsRecyclerView?.let {
            it.layoutManager = GridLayoutManager(this, PHOTO_COLUMNS).apply {
                this.stackFromEnd = false
            }
            it.setHasFixedSize(false)
            it.adapter = adapter
        }
    }

    private fun initializeObserving() {
        viewModel.states.observe(this, Observer { states ->
            states?.let {
                when (it) {
                    is DogsViewModel.DogsState.Success -> populateDogsPhotos(it.dogs)
                    is DogsViewModel.DogsState.Error -> showError(it.exception.message)
                }
            }
        })
        viewModel.showLoading.observe(this, Observer { loading ->
            loading?.let {
                dogsLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun populateDogsPhotos(dogs: List<String>?) {
        if (dogs.isNullOrEmpty()) {
            dogsEmpty.visibility = View.VISIBLE
        } else {
            adapter.refresh(dogs)
            dogsEmpty.visibility = View.GONE
        }
    }

    private fun showError(message: String?) {
        dogsErrorView.visibility = View.VISIBLE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun clearScreen() {
        dogsErrorView.visibility = View.GONE
        dogsEmpty.visibility = View.GONE
    }

    override fun click(url: String) {
        val dialog = Dialog(this).apply {
            this.setContentView(R.layout.dialog_dog_photo)
            this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            this.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            this.show()
        }

        dialog.apply {
            detailDogImage?.let {
                glide.load(url).diskCacheStrategy(DiskCacheStrategy.ALL).onlyRetrieveFromCache(true)
                    .into(it)
                it.setOnClickListener {
                    this.dismiss()
                }
            }
        }
    }

    companion object {
        private const val EXTRA_KEY_TOKEN = "token"
        private const val PHOTO_COLUMNS = 2
        fun start(context: Context, token: String) {
            val intent = Intent(context, DogsActivity::class.java)
            intent.putExtra(EXTRA_KEY_TOKEN, token)
            context.startActivity(intent)
        }
    }
}
