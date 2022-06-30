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
import com.example.gospodarstov3.adapters.UsersRecyclerAdapter
import com.example.gospodarstov3.model.Income
import com.example.gospodarstov3.model.User
import com.example.gospodarstov3.sql.DatabaseHelper

class UsersListActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@UsersListActivity
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<User>
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var textViewIncome: AppCompatTextView
    private lateinit var textViewAddIncome: AppCompatTextView
    private lateinit var textViewExpense: AppCompatTextView
    private lateinit var textViewAddExpense:  AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
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
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers) as RecyclerView
        textViewIncome = findViewById(R.id.textViewIncome) as AppCompatTextView
        textViewAddIncome = findViewById(R.id.textViewAddIncome) as AppCompatTextView
        textViewExpense = findViewById(R.id.textViewExpense) as AppCompatTextView
        textViewAddExpense = findViewById(R.id.textViewAddExpense) as AppCompatTextView
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initListeners() {
        textViewIncome!!.setOnClickListener(this)
        textViewExpense!!.setOnClickListener(this)
        textViewAddIncome!!.setOnClickListener(this)
        textViewAddExpense!!.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        when (v.id) {


            R.id.textViewIncome -> {
                // Navigate to RegisterActivity
                val emailFromIntent = intent.getStringExtra("NAME").toString()
                val accountsIntent2 = Intent(activity, IncomeListActivity::class.java)
                accountsIntent2.putExtra("NAME", emailFromIntent)
                startActivity(accountsIntent2)
            }
            R.id.textViewAddIncome -> {
                // Navigate to RegisterActivity
                val emailFromIntent = intent.getStringExtra("NAME").toString()
                val accountsIntent2 = Intent(activity, AddIncome::class.java)
                accountsIntent2.putExtra("NAME", emailFromIntent)
                startActivity(accountsIntent2)
            }
            R.id.textViewExpense -> {
                // Navigate to RegisterActivity
                val emailFromIntent = intent.getStringExtra("NAME").toString()
                val accountsIntent2 = Intent(activity, ExpenseListActivity::class.java)
                accountsIntent2.putExtra("NAME", emailFromIntent)
                startActivity(accountsIntent2)
            }
            R.id.textViewAddExpense -> {
                // Navigate to RegisterActivity
                val emailFromIntent = intent.getStringExtra("NAME").toString()
                val accountsIntent2 = Intent(activity, AddExpense::class.java)
                accountsIntent2.putExtra("NAME", emailFromIntent)
                startActivity(accountsIntent2)
            }
        }
    }
    private fun initObjects() {
        listUsers = ArrayList()
        usersRecyclerAdapter = UsersRecyclerAdapter(listUsers)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewUsers.layoutManager = mLayoutManager
        recyclerViewUsers.itemAnimator = DefaultItemAnimator()
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.adapter = usersRecyclerAdapter
        databaseHelper = DatabaseHelper(activity)
        val emailFromIntent = intent.getStringExtra("NAME").toString()
        var ID = databaseHelper.getUserID(emailFromIntent).toInt()
        var name = databaseHelper.getUserName(ID)
        textViewName.text = name
        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }
    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<User>>() {
        override fun doInBackground(vararg p0: Void?): List<User> {
            return databaseHelper.getAllUser()
        }
        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
        }
    }
}