package com.example.todoapp

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var rvItems: RecyclerView
    lateinit var rvAdapptor: ToDoAdaptor
    lateinit var items: ArrayList<ToDoItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = arrayListOf()

        rvItems = findViewById(R.id.rvMain)
        rvAdapptor = ToDoAdaptor(items)
        rvItems.adapter = rvAdapptor
        rvItems.layoutManager = LinearLayoutManager(this)

        var addButton = findViewById<FloatingActionButton>(R.id.fabAdd)
        addButton.setOnClickListener{ customDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemsDeleted = 0
        for(i in items){
            if(i.checked){itemsDeleted++}
        }

        if(itemsDeleted > 0){
            Toast.makeText(this, "$itemsDeleted items deleted", Toast.LENGTH_LONG).show()
            Log.d("ABC","$itemsDeleted items deleted")
        }else{
            Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
            Log.d("ABC","No items selected")
        }
//        items.removeAll{ item -> item.checked }
//        rvAdapptor.notifyDataSetChanged()
        rvAdapptor.deleteItems()
        return super.onOptionsItemSelected(item)
    }

    private fun customDialog(){
        val dialogBuilder = AlertDialog.Builder(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val toDo = EditText(this)
        toDo.hint = "Enter to-do item"
        layout.addView(toDo)

        dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
                dialog, id -> items.add(ToDoItem(toDo.text.toString()))
        })
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(layout)
        alert.show()
    }
}