package com.example.gospodarstov3

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.example.gospodarstov3.helpers.InputValidation
import com.example.gospodarstov3.model.Expense
import com.example.gospodarstov3.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDate

class AddExpense : AppCompatActivity(), View.OnClickListener
{ private val activity = this@AddExpense
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var textInputLayoutValue: TextInputLayout
    private lateinit var textInputEditTextValue: TextInputEditText
    private lateinit var textInputEditTextCategory: Spinner
    private lateinit var appCompatButtonAddExpense: AppCompatButton
    private lateinit var appCompatTextViewBack: AppCompatTextView
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_expense)
        // hiding the action bar
        supportActionBar!!.hide()
        // initializing the views
        initViews()
        // initializing the listeners
        initListeners()
        // initializing the objects
        initObjects()
    }
    /**
     * This method is to initialize views
     */
    private fun initViews() {
        nestedScrollView = findViewById(R.id.nestedScrollView) as NestedScrollView
        textInputLayoutValue = findViewById(R.id.textInputLayoutValue) as TextInputLayout

        textInputEditTextValue = findViewById(R.id.textInputEditTextValue) as TextInputEditText
        textInputEditTextCategory = findViewById(R.id.textInputEditTextCategory) as Spinner
        appCompatButtonAddExpense = findViewById(R.id.appCompatButtonAddExpense) as AppCompatButton

    }
    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        appCompatButtonAddExpense!!.setOnClickListener(this)

    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonAddExpense -> postDataToSQLite()
        }
    }
    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun postDataToSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextValue, textInputLayoutValue, getString(R.string.error_message_name))) {
            return
        }
        var category = textInputEditTextCategory.getSelectedItem().toString()
        val emailFromIntent = intent.getStringExtra("NAME").toString()
        val ID = databaseHelper!!.getUserID(emailFromIntent).toString().toInt()
        val name = databaseHelper!!.getUserName(ID)
        val dateTime = LocalDate.now().toString()
        var expense = Expense(
            expense_value = textInputEditTextValue!!.text.toString().toInt(),
            Date = dateTime,
            Category_ID =  category,
            User_id = name
        )
        databaseHelper!!.addExpense(expense)
        // Snack Bar to show success message that record saved successfully
        Snackbar.make(nestedScrollView!!, "Dodano pomyślnie", Snackbar.LENGTH_LONG).show()
        textInputEditTextValue!!.text = null

    }
    /**
     * This method is to empty all input edit text
     */

}