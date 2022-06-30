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
import com.example.gospodarstov3.adapters.ExpenseRecyclerAdapter
import com.example.gospodarstov3.model.Expense
import com.example.gospodarstov3.model.Income
import com.example.gospodarstov3.sql.DatabaseHelper

class ExpenseListActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@ExpenseListActivity
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewExpense: RecyclerView
    private lateinit var listExpense: MutableList<Expense>
    private lateinit var expenseRecyclerAdapter: ExpenseRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var Statystyki: AppCompatTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)
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
        recyclerViewExpense = findViewById(R.id.recyclerViewExpense) as RecyclerView
        Statystyki = findViewById(R.id.Statystyki) as AppCompatTextView
    }
    private fun initListeners() {
        Statystyki!!.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when (v.id) {

            R.id.Statystyki -> {
                // Navigate to com.example.gospodarstov3.ExpenseStatsActivity
                val intentRegister = Intent(applicationContext, ExpenseStatsActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listExpense = ArrayList()
        expenseRecyclerAdapter = ExpenseRecyclerAdapter(listExpense)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewExpense.layoutManager = mLayoutManager
        recyclerViewExpense.itemAnimator = DefaultItemAnimator()
        recyclerViewExpense.setHasFixedSize(true)
        recyclerViewExpense.adapter = expenseRecyclerAdapter
        databaseHelper = DatabaseHelper(activity)
        val emailFromIntent = intent.getStringExtra("NAME").toString()
        val ID = databaseHelper!!.getUserID(emailFromIntent).toInt()
        val name = databaseHelper.getUserName(ID)
        textViewName.text = name
        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }
    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Expense>>() {
        override fun doInBackground(vararg p0: Void?): List<Expense> {
            return databaseHelper.getAllExpense()
        }
        override fun onPostExecute(result: List<Expense>?) {
            super.onPostExecute(result)
            listExpense.clear()
            listExpense.addAll(result!!)
        }
    }
}