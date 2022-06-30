package com.example.gospodarstov3

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gospodarstov3.R
import com.example.gospodarstov3.adapters.IncomeRecyclerAdapter
import com.example.gospodarstov3.adapters.UsersRecyclerAdapter
import com.example.gospodarstov3.model.Income
import com.example.gospodarstov3.sql.DatabaseHelper

class IncomeListActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@IncomeListActivity
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewIncome: RecyclerView
    private lateinit var listIncome: MutableList<Income>
    private lateinit var incomeRecyclerAdapter: IncomeRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var Statystyki: AppCompatTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)
        supportActionBar!!.title = ""
        initViews()
        initObjects()
        initListeners()

    }
    /**
     * This method is to initialize views
     */
    private fun initViews() {
        textViewName = findViewById(R.id.textViewName) as AppCompatTextView
        Statystyki = findViewById(R.id.Statystyki) as AppCompatTextView
        recyclerViewIncome = findViewById(R.id.recyclerViewIncome) as RecyclerView
    }
    private fun initListeners() {
        Statystyki!!.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when (v.id) {

            R.id.Statystyki -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, IncomeStatsActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listIncome = ArrayList()
        incomeRecyclerAdapter = IncomeRecyclerAdapter(listIncome)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewIncome.layoutManager = mLayoutManager
        recyclerViewIncome.itemAnimator = DefaultItemAnimator()
        recyclerViewIncome.setHasFixedSize(true)
        recyclerViewIncome.adapter = incomeRecyclerAdapter
        databaseHelper = DatabaseHelper(activity)
        val nameFromIntent = intent.getStringExtra("NAME")
        textViewName.text = nameFromIntent
        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }
    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Income>>() {
        override fun doInBackground(vararg p0: Void?): List<Income> {
            return databaseHelper.getAllIncome()
        }
        override fun onPostExecute(result: List<Income>?) {
            super.onPostExecute(result)
            listIncome.clear()
            listIncome.addAll(result!!)
        }
    }
}