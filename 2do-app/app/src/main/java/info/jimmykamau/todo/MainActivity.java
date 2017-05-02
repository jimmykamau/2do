package info.jimmykamau.todo;

import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import info.jimmykamau.todo.adapters.TodoListAdapter;
import info.jimmykamau.todo.fragments.CreateTodoFragment;
import info.jimmykamau.todo.models.TodoItem;

public class MainActivity extends AppCompatActivity {

    public ListView mTodoItemsListView;
    private ProgressBar mTodoListProgress;
    private TextView mTodoListProgressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Remove shadow below action bar
        getSupportActionBar().setElevation(0);

        // Initiate progress bar and progress text
        mTodoListProgressText = (TextView) findViewById(R.id.completion_progress_text);
        mTodoListProgress = (ProgressBar) findViewById(R.id.completion_progress_bar);
        mTodoListProgress.setMax(100);

        // Initiate to-do list adapter
        TodoListAdapter todoListAdapter = new TodoListAdapter(this);
        mTodoItemsListView = (ListView) findViewById(R.id.todo_items_list);
        mTodoItemsListView.setAdapter(todoListAdapter);

        updateProgress();

        // Initiate BottomSheetDialogFragment for creating a new to-do item and listen for
        // floating button's click events
        final BottomSheetDialogFragment newItemDialogFragment = new CreateTodoFragment();
        FloatingActionButton mNewTodoButton = (FloatingActionButton) findViewById(R.id.new_todo_floating);
        mNewTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItemDialogFragment.show(getSupportFragmentManager(), newItemDialogFragment.getTag());
            }
        });
    }

    /**
     * Update the progress bar according to the number of items added and the ones marked as done
     */
    public void updateProgress() {
        List<TodoItem> todoItemsList = TodoItem.listAll(TodoItem.class);
        int completedItems = 0;
        for (TodoItem item : todoItemsList) {
            if(item.checkTodoComplete()) {
                completedItems++;
            }
        }
        int numberOfItems = todoItemsList.size();
        int currentProgress = 0;
        if (numberOfItems != 0) {
            currentProgress = (completedItems*100)/numberOfItems;
        }
        mTodoListProgress.setProgress(currentProgress);
        mTodoListProgressText.setText(getApplicationContext().getString(R.string.progress_text, currentProgress));
    }
}
