package info.jimmykamau.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import info.jimmykamau.todo.adapters.TodoListAdapter;
import info.jimmykamau.todo.models.TodoItem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> todoItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoItem firstItem = new TodoItem("First item", "First item description");
        TodoItem secondItem = new TodoItem("Second item", "Second item description");
        todoItemsList.add(firstItem);
        todoItemsList.add(secondItem);

        TodoListAdapter todoListAdapter = new TodoListAdapter(this, todoItemsList);
        ListView todoItemsListView = (ListView) findViewById(R.id.todo_items_list);
        todoItemsListView.setAdapter(todoListAdapter);
    }
}
