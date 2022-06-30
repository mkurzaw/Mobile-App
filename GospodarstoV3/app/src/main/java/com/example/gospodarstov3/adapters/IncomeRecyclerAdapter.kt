package com.example.gospodarstov3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gospodarstov3.R
import com.example.gospodarstov3.model.Income
import com.example.gospodarstov3.sql.DatabaseHelper


class IncomeRecyclerAdapter(private val listIncome: List<Income>) : RecyclerView.Adapter<IncomeRecyclerAdapter.IncomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        // inflating recycler item view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_income_recycler, parent, false)
        return IncomeViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {


        holder.textViewData.text = listIncome[position].Date
        holder.textViewValue.text = listIncome[position].Income_value.toString()
        holder.textViewUser.text = listIncome[position].User_id.toString()
        holder.textViewSource.text = listIncome[position].Source_ID
    }


    override fun getItemCount(): Int {
        return listIncome.size
    }


    /**
     * ViewHolder class
     */
    inner class IncomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewData: AppCompatTextView
        var textViewValue: AppCompatTextView
        var textViewUser: AppCompatTextView
        var textViewSource: AppCompatTextView

        lateinit var databaseHelper:DatabaseHelper
        val activity = this@IncomeRecyclerAdapter
        init {
            textViewData = view.findViewById(R.id.textViewData) as AppCompatTextView
            textViewValue = view.findViewById(R.id.textViewValue) as AppCompatTextView
            textViewUser = view.findViewById(R.id.textViewUser) as AppCompatTextView
            textViewSource = view.findViewById(R.id.textViewSource) as AppCompatTextView

            //ButtonDelete!!.setOnClickListener(this)
            //ButtonUpdate!!.setOnClickListener(this)
            //initListeners()

        }
    }
}