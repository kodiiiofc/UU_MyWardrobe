package com.kodiiiofc.urbanuniversity.mywardrobe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WardrobeActivity : AppCompatActivity(), Updatable {

    private lateinit var statusBar: View
    private lateinit var toolbar: Toolbar
    private lateinit var clothesRV: RecyclerView

    val clothes: MutableList<Cloth> = Clothes().list.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wardrobe)
        initToolbar()
        clothesRV = findViewById(R.id.rv_clothes)
        clothesRV.layoutManager = LinearLayoutManager(this)
        val adapter = ClothesAdapter(clothes)
        clothesRV.adapter = adapter
        clothesRV.setHasFixedSize(true)

        adapter.setOnItemClickListener(object : ClothesAdapter.OnItemLongClickListener {
            override fun onItemClick(cloth: Cloth, position: Int) {
                val args = Bundle()
                args.putSerializable("item", cloth)
                args.putInt("position", position)
                val dialog = ItemDialog()
                dialog.arguments = args
                dialog.show(supportFragmentManager, "ItemDialog")
            }

        })

    }

    private fun initToolbar() {
        statusBar = findViewById(R.id.status_bar)

        val statusBarHeight = resources.getIdentifier("status_bar_height", "dimen", "android")
        statusBar.layoutParams.height = resources.getDimensionPixelSize(statusBarHeight)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_exit -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateItem(cloth: Cloth, position: Int) {
        clothes[position] = cloth
        clothesRV.adapter?.notifyItemChanged(position)
    }

}