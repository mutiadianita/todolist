package com.example.todolist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {
    private var todoListItems = ArrayList()
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        listView = findViewById(R.id.list_view)
        populateListView()

        fab.setOnClickListener { showNewTaskUI()}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        Snackbar.make(fab, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        todoListItems.add(task)
        listAdapter?.notifyDataSetChanged()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun populateListView() {
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoListItems)
        listView?.adapter = listAdapter
    }

    fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_new_task_dialog_title)
        newFragment.show(fragmentManager,"newtask")
    }

}
