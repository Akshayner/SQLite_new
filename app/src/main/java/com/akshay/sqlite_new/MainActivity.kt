package com.akshay.sqlite_new

import android.content.ContentValues
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db= openOrCreateDatabase("employee",Context.MODE_PRIVATE,null)
        db.execSQL("create table if not exists employee(_id integer primary key autoincrement,emp_id number,name varchar(100),desig varchar(100),dept varchar(100))")


        insert.setOnClickListener{
            var cv = ContentValues()
            cv.put("emp_id",et1.text.toString().toInt())
            cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())
            cv.put("dept",et4.text.toString())
            var status = db.insert("employee",null,cv)
            if(status!=-1.toLong()){
                Toast.makeText(this@MainActivity,"Inserted Sucessfully",Toast.LENGTH_LONG).show()
                et1.setText("");et2.setText("");et3.setText("");et4.setText("")
            }
            else{
                Toast.makeText(this@MainActivity,"Insertion Failed",Toast.LENGTH_LONG).show()
            }
        }

        read.setOnClickListener {
            var cursor = db.query("employee",null,null,null,null,null,null)
            var from = arrayOf("emp_id","name","desig","dept")
            var to = intArrayOf(R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4)
            lv.adapter = SimpleCursorAdapter(this@MainActivity,R.layout.iview,cursor,from,to,0)
        }

        update.setOnClickListener {
            var cv = ContentValues()
            cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())
            cv.put("dept",et4.text.toString())
            var status = db.update("employee",cv,"emp_id=?", arrayOf(et1.text.toString()))
            if(status!=0){
                Toast.makeText(this@MainActivity,"Updated Sucessfully",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this@MainActivity,"Updation Failed",Toast.LENGTH_LONG).show()
            }
        }

        delete.setOnClickListener {
            db.delete("employee","emp_id=?",arrayOf(et1.text.toString()))
        }


    }
}
