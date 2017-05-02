package info.jimmykamau.todo.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.jimmykamau.todo.MainActivity;
import info.jimmykamau.todo.R;
import info.jimmykamau.todo.models.TodoItem;

/**
 * Created by jimmy on 01/05/2017.
 */

public class CreateTodoFragment extends BottomSheetDialogFragment {

    private View mCreateTodoView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCreateTodoView = View.inflate(getContext(), R.layout.create_todo_item, null);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        dialog.setContentView(mCreateTodoView);

        Button addItemButton = (Button) mCreateTodoView.findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newItemTitle = (EditText) mCreateTodoView.findViewById(R.id.add_item_title_input);
                EditText newItemDescription = (EditText) mCreateTodoView.findViewById(R.id.add_item_description_input);
                String itemTitle = newItemTitle.getText().toString();
                String itemDescription = newItemDescription.getText().toString();
                TodoItem newItem = new TodoItem(itemTitle, itemDescription, false);
                newItem.save();
                ((BaseAdapter) ((MainActivity) getContext()).mTodoItemsListView.getAdapter()).notifyDataSetChanged();
                Toast.makeText(getContext(), getString(R.string.todo_create_success), Toast.LENGTH_SHORT).show();
                newItemTitle.setText("");
                newItemDescription.setText("");
                dismiss();
                ((MainActivity) getContext()).updateProgress();
            }
        });
    }
}
