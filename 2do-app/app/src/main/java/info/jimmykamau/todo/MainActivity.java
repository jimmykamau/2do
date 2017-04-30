package info.jimmykamau.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import info.jimmykamau.todo.adapters.TodoListAdapter;
import info.jimmykamau.todo.models.TodoItem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> todoItemsList = new ArrayList<>();
    private int completedItems = 0;
    private ListView mTodoItemsListView;
    private ProgressBar mTodoListProgress;
    private TextView mTodoListProgressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoListProgressText = (TextView) findViewById(R.id.completion_progress_text);
        mTodoListProgress = (ProgressBar) findViewById(R.id.completion_progress_bar);
        mTodoListProgress.setMax(100);

        TodoListAdapter todoListAdapter = new TodoListAdapter(this, todoItemsList);
        mTodoItemsListView = (ListView) findViewById(R.id.todo_items_list);
        mTodoItemsListView.setAdapter(todoListAdapter);
    }

    public void addTodoItem(View view) {
        EditText newItemInput = (EditText) findViewById(R.id.add_item_input);
        String itemTitle = newItemInput.getText().toString();
        TodoItem newItem = new TodoItem(itemTitle, "New item description");
        todoItemsList.add(newItem);
        newItemInput.setText("");
        updateProgress();
    }

    public void removeTodoItem(int position) {
        todoItemsList.remove(position);
        updateProgress();
    }

    public void markItemAsComplete(int position) {
        TodoItem selectedItem = todoItemsList.get(position);
        selectedItem.markTodoComplete(!selectedItem.checkTodoComplete());
        updateProgress();
    }

    private void updateProgress() {
        ((BaseAdapter) mTodoItemsListView.getAdapter()).notifyDataSetChanged();
        completedItems = 0;
        for (TodoItem item : todoItemsList) {
            if(item.checkTodoComplete()) {
                completedItems++;
            }
        }
        int numberOfItems = todoItemsList.size();
        int currentProgress = (completedItems*100)/numberOfItems;
        mTodoListProgress.setProgress(currentProgress);
        mTodoListProgressText.setText(getApplicationContext().getString(R.string.progress_text, currentProgress));
    }
}
