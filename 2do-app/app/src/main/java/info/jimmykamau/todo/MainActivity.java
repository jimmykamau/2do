package info.jimmykamau.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import info.jimmykamau.todo.adapters.TodoListAdapter;
import info.jimmykamau.todo.models.TodoItem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> todoItemsList = new ArrayList<>();
    private ListView mTodoItemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoListAdapter todoListAdapter = new TodoListAdapter(this, todoItemsList);
        mTodoItemsListView = (ListView) findViewById(R.id.todo_items_list);
        mTodoItemsListView.setAdapter(todoListAdapter);
    }

    public void addTodoItem(View view) {
        EditText newItemInput = (EditText) findViewById(R.id.add_item_input);
        String itemTitle = newItemInput.getText().toString();
        TodoItem newItem = new TodoItem(itemTitle, "New item description");
        todoItemsList.add(newItem);
        ((BaseAdapter) mTodoItemsListView.getAdapter()).notifyDataSetChanged();
        newItemInput.setText("");
    }
}
