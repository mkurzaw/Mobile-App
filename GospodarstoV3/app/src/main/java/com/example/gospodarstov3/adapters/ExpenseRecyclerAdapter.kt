package com.example.gospodarstov3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gospodarstov3.R
import com.example.gospodarstov3.model.Expense
import com.example.gospodarstov3.sql.DatabaseHelper

class ExpenseRecyclerAdapter(private val listExpense: List<Expense>) : RecyclerView.Adapter<ExpenseRecyclerAdapter.ExpenseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        // inflating recycler item view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense_recycler, parent, false)
        return ExpenseViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {


        holder.textViewData.text = listExpense[position].Date
        holder.textViewValue.text = listExpense[position].expense_value.toString()
        holder.textViewUser.text = listExpense[position].User_id.toString()
        holder.textViewCategory.text = listExpense[position].Category_ID
    }
    override fun getItemCount(): Int {
        return listExpense.size
    }
    /**
     * ViewHolder class
     */
    inner class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewData: AppCompatTextView
        var textViewValue: AppCompatTextView
        var textViewUser: AppCompatTextView
        var textViewCategory: AppCompatTextView
        init {
            textViewData = view.findViewById(R.id.textViewData) as AppCompatTextView
            textViewValue = view.findViewById(R.id.textViewValue) as AppCompatTextView
            textViewUser = view.findViewById(R.id.textViewUser) as AppCompatTextView
            textViewCategory = view.findViewById(R.id.textViewCategory) as AppCompatTextView
        }
    }
}