package bhsoft.retirement.calculator.poc.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // looping through all rows and adding to list
    // adding to Students list
    val allUserList: ArrayList<String>
        get() {
            val userArrayList = ArrayList<String>()
            var name: String
            val selectQuery = "SELECT  * FROM $TABLE_USERS"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    name = c.getString(c.getColumnIndex(KEY_FIRSTNAME))
                    userArrayList.add(name)
                } while (c.moveToNext())
                Log.d("array", userArrayList.toString())
            }
            return userArrayList
        }

    init {

        Log.d("table", CREATE_TABLE_USERS)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USERS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_USERS'")
        onCreate(db)
    }

    fun addUserDetail(email: String,password:String,question:String,answer:String,firstname:String,lastname:String): Long {
        val db = this.writableDatabase
        // Creating content values
        val values = ContentValues()
        values.put(KEY_FIRSTNAME, firstname)
        values.put(KEY_LASTNAME,lastname)
        values.put(KEY_EMAIL,email)
        values.put(KEY_PASSWORD,password)
        values.put(KEY_QUESTION,question)
        values.put(KEY_ANSWER,answer)
        // insert row in students table

        return db.insert(TABLE_USERS, null, values)
    }
    fun checkUser(email: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(KEY_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$KEY_EMAIL = ?"

        // selection argument
        val selectionArgs = arrayOf(email)
        val cursor = db.query(
            TABLE_USERS, //Table to query
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

    fun checkUser(email: String, password: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(KEY_ID)

        val db = this.readableDatabase

        // selection criteria
        val selection = "$KEY_EMAIL = ? AND $KEY_PASSWORD = ?"

        // selection arguments
        val selectionArgs = arrayOf(email, password)

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(
            TABLE_USERS, //Table to query
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
    companion object {

        var DATABASE_NAME = "user_database"
        private val DATABASE_VERSION = 1
        private val TABLE_USERS = "users"
        private val KEY_ID = "_id"
        private val KEY_FIRSTNAME = "firstname"
        private val KEY_LASTNAME = "lastname"
        private val KEY_EMAIL = "email"
        private val KEY_PASSWORD = "password"
        private val KEY_QUESTION = "question"
        private val KEY_ANSWER = "answer"

        /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/
        private val CREATE_TABLE_USERS = ("CREATE TABLE "
                + TABLE_USERS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT,"
                + KEY_LASTNAME + " TEXT," + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_QUESTION + " TEXT,"
                + KEY_ANSWER + " TEXT);")
        //+
    }
}