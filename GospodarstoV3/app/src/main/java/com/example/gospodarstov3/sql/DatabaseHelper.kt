package com.example.gospodarstov3.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gospodarstov3.adapters.IncomeRecyclerAdapter
import com.example.gospodarstov3.model.Expense
import com.example.gospodarstov3.model.Income
import com.example.gospodarstov3.model.Source
import com.example.gospodarstov3.model.User
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.List as List

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // create table sql query
    private val CREATE_TABLE_USER = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT," + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ");")

    private val CREATE_TABLE_SOURCE=( "CREATE TABLE "+ TABLE_SOURCE+"("
            + COLUMN_SOURCE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_SOURCE_NAME+" TEXT NOT NULL,"+
            COLUMN_SOURCE_DESCRIBE+" TEXT);")
    private val CREATE_TABLE_CATEGORY=( "CREATE TABLE "+ TABLE_CATEGORY+"("
            + COLUMN_CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_CATEGORY_NAME+" TEXT NOT NULL,"+
            COLUMN_CATEGORY_DESCRIBE+" TEXT);")
    private val CREATE_TABLE_INCOME=( "CREATE TABLE " + TABLE_INCOME + "("
            + COLUMN_INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_INCOME_VALUE + " INTEGER,"
            + COLUMN_INCOME_DATE + " TEXT," + COLUMN_INCOME_SOURCE + " TEXT, "
            + COLUMN_INCOME_USER + " TEXT, "+"FOREIGN KEY ("+ COLUMN_INCOME_USER+
            ") REFERENCES "+ TABLE_USER+"("+ COLUMN_USER_NAME+")"+"FOREIGN KEY ("+ COLUMN_INCOME_SOURCE+
        ") REFERENCES "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+")"+ ");")
    private val CREATE_TABLE_EXPENSE=( "CREATE TABLE " + TABLE_EXPENSE + "("
            + COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_EXPENSE_VALUE + " INTEGER,"
            + COLUMN_EXPENSE_DATE + " TEXT," + COLUMN_EXPENSE_CATEGORY + " TEXT, "
            + COLUMN_EXPENSE_USER + " TEXT, "+"FOREIGN KEY ("+ COLUMN_EXPENSE_USER+
    ") REFERENCES "+ TABLE_USER+"("+ COLUMN_USER_NAME+")"+"FOREIGN KEY ("+ COLUMN_EXPENSE_CATEGORY+
        ") REFERENCES "+ TABLE_CATEGORY+"("+ COLUMN_CATEGORY_NAME+")"+ ");")
    private val INSERT_DATA_SOURCE=("INSERT INTO "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+","+
            COLUMN_SOURCE_DESCRIBE+") VALUES ('Praca','przychody z pracy zarobkowej');")
    private val INSERT_DATA_SOURCE2=("INSERT INTO "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+","+
            COLUMN_SOURCE_DESCRIBE+") VALUES ('Pożyczka','przychody z pozyczek');")
    private val INSERT_DATA_SOURCE3=("INSERT INTO "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+","+
            COLUMN_SOURCE_DESCRIBE+") VALUES ('Wygrana','przychody z pozyczek');")
    private val INSERT_DATA_SOURCE4=("INSERT INTO "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+","+
            COLUMN_SOURCE_DESCRIBE+") VALUES ('Zwrot podatku','przychody z pozyczek');")
    private val INSERT_DATA_SOURCE5=("INSERT INTO "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+","+
            COLUMN_SOURCE_DESCRIBE+") VALUES ('Inwestycje','przychody z pozyczek');")
    private val INSERT_DATA_SOURCE6=("INSERT INTO "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+","+
            COLUMN_SOURCE_DESCRIBE+") VALUES ('Spadek','przychody z pozyczek');")
    private val INSERT_DATA_SOURCE7=("INSERT INTO "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_NAME+","+
            COLUMN_SOURCE_DESCRIBE+") VALUES ('Inne','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Żywność','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY1=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Opłaty mieszkaniowe','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY2=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Samochód','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY3=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Elektronika','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY4=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Wakacje','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY5=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Szkoła','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY6=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Ubrania','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY7=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('AGD','przychody z pozyczek');")
    private val INSERT_DATA_CATEGORY8=("INSERT INTO "+ TABLE_CATEGORY +"("+ COLUMN_CATEGORY_NAME+","+
            COLUMN_CATEGORY_DESCRIBE+") VALUES ('Inne','przychody z pozyczek');")


    // drop table sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
    private val DROP_INCOME_TABLE = "DROP TABLE IF EXISTS $TABLE_INCOME"
    private val DROP_SOURCE_TABLE = "DROP TABLE IF EXISTS $TABLE_SOURCE"
    private val DROP_EXPENSE_TABLE = "DROP TABLE IF EXISTS $TABLE_EXPENSE"
    private val DROP_PERMISSIONS_TABLE = "DROP TABLE IF EXISTS $TABLE_PERMISSIONS"
    private val DROP_CATEGORY_TABLE = "DROP TABLE IF EXISTS $TABLE_CATEGORY"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USER)
        db.execSQL(CREATE_TABLE_SOURCE)
        db.execSQL(CREATE_TABLE_CATEGORY)
        db.execSQL(CREATE_TABLE_INCOME)
        db.execSQL(CREATE_TABLE_EXPENSE)
        db.execSQL(INSERT_DATA_SOURCE)
        db.execSQL(INSERT_DATA_SOURCE2)
        db.execSQL(INSERT_DATA_SOURCE3)
        db.execSQL(INSERT_DATA_SOURCE4)
        db.execSQL(INSERT_DATA_SOURCE5)
        db.execSQL(INSERT_DATA_SOURCE6)
        db.execSQL(INSERT_DATA_SOURCE7)
        db.execSQL(INSERT_DATA_CATEGORY)
        db.execSQL(INSERT_DATA_CATEGORY1)
        db.execSQL(INSERT_DATA_CATEGORY2)
        db.execSQL(INSERT_DATA_CATEGORY3)
        db.execSQL(INSERT_DATA_CATEGORY4)
        db.execSQL(INSERT_DATA_CATEGORY5)
        db.execSQL(INSERT_DATA_CATEGORY6)
        db.execSQL(INSERT_DATA_CATEGORY7)
        db.execSQL(INSERT_DATA_CATEGORY8)



    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Drop User Table if exist
        db.execSQL(DROP_INCOME_TABLE)
        db.execSQL(DROP_USER_TABLE)
        db.execSQL(DROP_SOURCE_TABLE)
        db.execSQL(DROP_EXPENSE_TABLE)
        db.execSQL(DROP_PERMISSIONS_TABLE)
        db.execSQL(DROP_CATEGORY_TABLE)


        // Create tables again
        onCreate(db)
    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    @SuppressLint("Range")
    fun getAllUser(): List<User> {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_EMAIL, COLUMN_USER_NAME, COLUMN_USER_PASSWORD)
        // sorting orders
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList = ArrayList<User>()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(TABLE_USER, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val user = User(id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        // Inserting Row
        db.insert(TABLE_USER, null, values)
        db.close()
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    fun updateUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        // updating row
        db.update(TABLE_USER, values, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }
    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteUser(user: User) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_USER, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    fun checkUser(email: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ?"
        // selection argument
        val selectionArgs = arrayOf(email)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun checkUser(email: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        // selection arguments
        val selectionArgs = arrayOf(email, password)
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }
    @SuppressLint("Range")
    fun getUserID(email: String): String {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID,COLUMN_USER_NAME)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ?"
        // selection argument
        val selectionArgs = arrayOf(email)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val name = cursor.getColumnIndex(COLUMN_USER_NAME)
        val id = cursor.getColumnIndex(COLUMN_USER_ID)
        while(cursor.moveToNext())
        {
            columns[0]= cursor.getInt(id).toString()
            columns[1]=cursor.getString(name)
        }
        cursor.close()
        db.close()
        return columns[0]
    }
    fun getUserName(id: Int): String {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_NAME)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USER_ID = ?"
        // selection argument
        val selectionArgs = arrayOf(id.toString())
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val name = cursor.getColumnIndex(COLUMN_USER_NAME)

        while(cursor.moveToNext())
        {
            columns[0]=cursor.getString(name)
        }
        cursor.close()
        db.close()
        return columns[0]
    }
    //////////////////////////////////////////////////////////////
    //Przychod
    /*
    private val CREATE_INCOME_TABLE = ("CREATE TABLE " + TABLE_INCOME + "("
            + COLUMN_INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_INCOME_VALUE + " INTEGER,"
            + COLUMN_INCOME_DATE + " TEXT," + COLUMN_INCOME_SOURCE + " INTEGER, "+"FOREIGN KEY("+ COLUMN_INCOME_SOURCE+
            ") REFERENCES "+ TABLE_SOURCE+"("+ COLUMN_SOURCE_ID+")"+ COLUMN_INCOME_USER + " INTEGER, "+"FOREIGN KEY("+ COLUMN_INCOME_USER+
            ") REFERENCES "+ TABLE_USER+"("+ COLUMN_USER_ID+")"+ ")")
    // drop table sql query
    private val DROP_INCOME_TABLE = "DROP TABLE IF EXISTS $TABLE_INCOME"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_INCOME_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Drop User Table if exist
        db.execSQL(DROP_INCOME_TABLE)
        // Create tables again
        onCreate(db)
    }*/
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    @SuppressLint("Range")
    fun getAllIncome(): List<Income> {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_INCOME_ID, COLUMN_INCOME_VALUE, COLUMN_INCOME_DATE, COLUMN_INCOME_SOURCE, COLUMN_INCOME_USER)
        // sorting orders
        val sortOrder = "$COLUMN_INCOME_DATE DESC"
        val incomeList = ArrayList<Income>()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(TABLE_INCOME, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val income = Income(
                    ID_income = cursor.getString(cursor.getColumnIndex(COLUMN_INCOME_ID)).toInt(),
                    Income_value = cursor.getString(cursor.getColumnIndex(COLUMN_INCOME_VALUE)).toInt() ,
                    Date = cursor.getString(cursor.getColumnIndex(COLUMN_INCOME_DATE)),
                    User_id = cursor.getString(cursor.getColumnIndex(COLUMN_INCOME_USER)),
                    Source_ID = cursor.getString(cursor.getColumnIndex(COLUMN_INCOME_SOURCE)))
                incomeList.add(income)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return incomeList
    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun addIncome( income: Income) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_INCOME_USER, income.User_id)
        values.put(COLUMN_INCOME_SOURCE, income.Source_ID)
        values.put(COLUMN_INCOME_DATE, income.Date)
        values.put(COLUMN_INCOME_VALUE, income.Income_value)

        // Inserting Row
        db.insert(TABLE_INCOME, null, values)
        db.close()
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateIncome(user: User, income: Income,source: Source) {
        val db = this.writableDatabase
        val dateTime = LocalDateTime.now().toString()
        val values = ContentValues()
        values.put(COLUMN_INCOME_USER, user.id)
        values.put(COLUMN_INCOME_SOURCE, source.ID_source)
        values.put(COLUMN_INCOME_DATE, dateTime)
        values.put(COLUMN_INCOME_VALUE, income.Income_value)
        // updating row
        db.update(
            TABLE_INCOME, values, "$COLUMN_INCOME_ID = ?",
            arrayOf(income.ID_income.toString()))
        db.close()
    }
    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteIncome(income: Income) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(
            TABLE_INCOME, "$COLUMN_INCOME_ID = ?",
            arrayOf(income.ID_income.toString()))
        db.close()
    }
    @SuppressLint("Range")
    fun getAllSources(): List<String> {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_SOURCE_NAME)
        // sorting orders
        val sortOrder = "$COLUMN_SOURCE_ID ASC"
        val sourceList = ArrayList<String>()
        val db = this.readableDatabase
        // query the user table
        val string = "SELECT " + COLUMN_SOURCE_NAME+" FROM "  + TABLE_SOURCE
        val cursor = db.rawQuery(string,null)   //The sort order
        if (cursor.moveToFirst()) {
            do { val source_name = cursor.getString(cursor.getColumnIndex(COLUMN_SOURCE_NAME))


                sourceList.add(source_name)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return sourceList
    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun addSource(source: Source) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_SOURCE_NAME, source.source_name)
        values.put(COLUMN_SOURCE_DESCRIBE, source.source_describe)


        // Inserting Row
        db.insert(TABLE_SOURCE, null, values)
        db.close()
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateSource(source: Source) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_SOURCE_NAME, source.source_name)
        values.put(COLUMN_SOURCE_DESCRIBE, source.source_describe)
        // updating row
        db.update(
            TABLE_INCOME, values, "$COLUMN_INCOME_ID = ?",
            arrayOf(source.ID_source.toString()))
        db.close()
    }
    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteSource(source: Source) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(
            TABLE_SOURCE, "$COLUMN_SOURCE_ID = ?",
            arrayOf(source.ID_source.toString()))
        db.close()
    }
/////////////////////////////////////////////////////////
    //Wydatki
    ///////////////////////////
@SuppressLint("Range")
fun getAllExpense(): List<Expense> {
    // array of columns to fetch
    val columns = arrayOf(COLUMN_EXPENSE_ID, COLUMN_EXPENSE_VALUE, COLUMN_EXPENSE_DATE, COLUMN_EXPENSE_CATEGORY, COLUMN_EXPENSE_USER)
    // sorting orders
    val sortOrder = "$COLUMN_EXPENSE_DATE DESC"
    val expenseList = ArrayList<Expense>()
    val db = this.readableDatabase
    // query the user table
    val cursor = db.query(TABLE_EXPENSE, //Table to query
        columns,            //columns to return
        null,     //columns for the WHERE clause
        null,  //The values for the WHERE clause
        null,      //group the rows
        null,       //filter by row groups
        sortOrder)         //The sort order
    if (cursor.moveToFirst()) {
        do {
            val expense = Expense(
                ID_expense = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_ID)).toInt(),
                expense_value = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_VALUE)).toInt() ,
                Date = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_DATE)),
                User_id = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_USER)),
                Category_ID = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_CATEGORY)))
            expenseList.add(expense)
        } while (cursor.moveToNext())
    }
    cursor.close()
    db.close()
    return expenseList
}
    /**
     * This method is to create user record
     *
     * @param user
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun addExpense( expense: Expense) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_EXPENSE_USER, expense.User_id)
        values.put(COLUMN_EXPENSE_CATEGORY, expense.Category_ID)
        values.put(COLUMN_EXPENSE_DATE, expense.Date)
        values.put(COLUMN_EXPENSE_VALUE, expense.expense_value)

        // Inserting Row
        db.insert(TABLE_EXPENSE, null, values)
        db.close()
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateExpense(expense: Expense) {
        val db = this.writableDatabase
        val dateTime = LocalDateTime.now().toString()
        val values = ContentValues()
        values.put(COLUMN_EXPENSE_USER, expense.User_id)
        values.put(COLUMN_EXPENSE_CATEGORY, expense.Category_ID)
        values.put(COLUMN_EXPENSE_DATE, expense.Date)
        values.put(COLUMN_EXPENSE_VALUE, expense.expense_value)
        // updating row
        db.update(
            TABLE_EXPENSE, values, "$COLUMN_INCOME_ID = ?",
            arrayOf(expense.ID_expense.toString()))
        db.close()
    }
    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteExpense(expense: Expense) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(
            TABLE_INCOME, "$COLUMN_INCOME_ID = ?",
            arrayOf(expense.ID_expense.toString()))
        db.close()
    }
    fun getSumIncomeByName(name:String):Int
    {
        var sum=0
        val columns = arrayOf(COLUMN_INCOME_VALUE)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_INCOME_USER = ?"
        // selection argument
        val selectionArgs = arrayOf(name)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_INCOME, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order

        val value = cursor.getColumnIndex(COLUMN_INCOME_VALUE)
        while(cursor.moveToNext())
        {
            columns[0]= cursor.getInt(value).toString()
            sum=sum+columns[0].toInt()

        }
        cursor.close()
        db.close()
        return sum
    }
    fun getSumIncomeBySource(source:String):Int
    {
        var sum=0
        val columns = arrayOf(COLUMN_INCOME_VALUE)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_INCOME_SOURCE = ?"
        // selection argument
        val selectionArgs = arrayOf(source)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_INCOME, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order

        val value = cursor.getColumnIndex(COLUMN_INCOME_VALUE)
        while(cursor.moveToNext())
        {
            columns[0]= cursor.getInt(value).toString()
            sum=sum+columns[0].toInt()

        }
        cursor.close()
        db.close()
        return sum
    }
    fun getSumExpenseByName(name:String):Int
    {
        var sum=0
        val columns = arrayOf(COLUMN_EXPENSE_VALUE)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_EXPENSE_USER = ?"
        // selection argument
        val selectionArgs = arrayOf(name)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_EXPENSE, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order

        val value = cursor.getColumnIndex(COLUMN_EXPENSE_VALUE)
        while(cursor.moveToNext())
        {
            columns[0]= cursor.getInt(value).toString()
            sum=sum+columns[0].toInt()

        }
        cursor.close()
        db.close()
        return sum
    }
    fun getSumExpenseByCategory(category:String):Int
    {
        var sum=0
        val columns = arrayOf(COLUMN_EXPENSE_VALUE)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_EXPENSE_CATEGORY = ?"
        // selection argument
        val selectionArgs = arrayOf(category)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_EXPENSE, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order

        val value = cursor.getColumnIndex(COLUMN_EXPENSE_VALUE)
        while(cursor.moveToNext())
        {
            columns[0]= cursor.getInt(value).toString()
            sum=sum+columns[0].toInt()

        }
        cursor.close()
        db.close()
        return sum
    }
    companion object {
        // Database Version
        private val DATABASE_VERSION = 35
        // Database Name
        private val DATABASE_NAME = "Gospodarstwo3.db"
        //table names
        private val TABLE_USER = "osoba"
        private val TABLE_INCOME = "przychod"
        private val TABLE_EXPENSE = "wydatek"
        private val TABLE_SOURCE = "zrodlo_przychodu"
        private val TABLE_CATEGORY = "kategoria_wydatku"
        private val TABLE_PERMISSIONS = "prawa_uzytkownika"
        // User Table Columns names
        private val COLUMN_USER_ID = "id_osoby"
        private val COLUMN_USER_NAME = "imie"
        private val COLUMN_USER_EMAIL = "Email"
        private val COLUMN_USER_PASSWORD = "haslo"
        //Przychód

        private val COLUMN_INCOME_ID = "id_przychodu"
        private val COLUMN_INCOME_VALUE = "kwota_przychodu"
        private val COLUMN_INCOME_DATE = "data_przychodu"
        private val COLUMN_INCOME_USER = "id_uzytkownika"
        private val COLUMN_INCOME_SOURCE = "zrodlo_przychodu"


        //Zrodlo
        private val COLUMN_SOURCE_ID = "zrodlo_id"
        private val COLUMN_SOURCE_NAME = "nazwa_przychodu"
        private val COLUMN_SOURCE_DESCRIBE = "zrodlo_opis"
        //Kategoria
        private val COLUMN_CATEGORY_ID = "kategoria_id"
        private val COLUMN_CATEGORY_NAME = "nazwa_kategorii"
        private val COLUMN_CATEGORY_DESCRIBE = "kategoria_opis"
        //Wydatek
        private val COLUMN_EXPENSE_ID = "wydatek_id"
        private val COLUMN_EXPENSE_VALUE = "wartosc_wydatku"
        private val COLUMN_EXPENSE_DATE = "data_wydatku"
        private val COLUMN_EXPENSE_USER = "id_uzytkownika"
        private val COLUMN_EXPENSE_CATEGORY = "kategoria_wydatku"

    }
}


