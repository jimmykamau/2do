package info.jimmykamau.todo.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import info.jimmykamau.todo.MainActivity;
import info.jimmykamau.todo.R;
import info.jimmykamau.todo.models.TodoItem;

/**
 * Created by jimmy on 02/05/2017.
 */

public class EditTodoFragment extends BottomSheetDialogFragment {

    private View mEditTodoView;
    private TodoItem todoItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditTodoView = View.inflate(getContext(), R.layout.todo_actions, null);

        Bundle args = getArguments();
        long itemId = args.getLong("todoId");
        todoItem = TodoItem.findById(TodoItem.class, itemId);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        dialog.setContentView(mEditTodoView);

        // Initialize fragment with selected item's details
        final EditText todoTitle = (EditText) mEditTodoView.findViewById(R.id.edit_item_title_input);
        final EditText todoDescription = (EditText) mEditTodoView.findViewById(R.id.edit_item_description_input);
        Switch todoDoneSwitch = (Switch) mEditTodoView.findViewById(R.id.todo_complete_switch);
        todoTitle.setText(todoItem.getTodoItemTitle());
        todoDescription.setText(todoItem.getTodoItemDescription());
        todoDoneSwitch.setChecked(todoItem.checkTodoComplete());

        // Save changes made to to-do item
        Button saveTodoChanges = (Button) mEditTodoView.findViewById(R.id.edit_item_button_save);
        saveTodoChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = todoTitle.getText().toString();
                String newDescription = todoDescription.getText().toString();
                todoItem.updateItemTitle(newTitle);
                todoItem.updateItemDescription(newDescription);
                todoItem.save();
                ((BaseAdapter) ((MainActivity) getContext()).mTodoItemsListView.getAdapter()).notifyDataSetChanged();
                Toast.makeText(getContext(), getString(R.string.todo_edit_success), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        // Delete to-do item
        Button deleteTodo = (Button) mEditTodoView.findViewById(R.id.edit_item_button_delete);
        deleteTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoItem.delete();
                ((BaseAdapter) ((MainActivity) getContext()).mTodoItemsListView.getAdapter()).notifyDataSetChanged();
                Toast.makeText(getContext(), getString(R.string.todo_delete_success), Toast.LENGTH_SHORT).show();
                ((MainActivity) getContext()).updateProgress();
                dismiss();
            }
        });

        // Change `done` status of to-do item
        todoDoneSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                todoItem.updateItemComplete(isChecked);
                todoItem.save();
                ((BaseAdapter) ((MainActivity) getContext()).mTodoItemsListView.getAdapter()).notifyDataSetChanged();
                ((MainActivity) getContext()).updateProgress();
            }
        });
    }
}
