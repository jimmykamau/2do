package info.jimmykamau.todo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

import info.jimmykamau.todo.MainActivity;
import info.jimmykamau.todo.R;
import info.jimmykamau.todo.fragments.EditTodoFragment;
import info.jimmykamau.todo.models.TodoItem;

/**
 * Created by jimmy on 30/04/2017.
 */

public class TodoListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<TodoItem> mTodoItemsList;
    private Context mContext;

    public TodoListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return (int) TodoItem.count(TodoItem.class);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mTodoItemsList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mTodoItemsList = new ArrayList<>(TodoItem.listAll(TodoItem.class));
        TodoItemViewHolder viewHolder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.todo_item, parent, false);
            viewHolder = new TodoItemViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TodoItemViewHolder) convertView.getTag();
        }

        // Get a to-do item
        final TodoItem currentItem = TodoItem.findById(TodoItem.class, mTodoItemsList.get(position).getId());

        // Set the item's title and description
        viewHolder.itemTitle.setText(currentItem.getTodoItemTitle());
        viewHolder.itemDescription.setText(currentItem.getTodoItemDescription());

        // Generate the item's colored circle
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int drawableBackground = generator.getRandomColor();
        TextDrawable drawable;
        if (currentItem.checkTodoComplete()) {
            drawable = TextDrawable.builder().buildRound(String.valueOf('\u2713'), Color.GREEN);
        } else {
            drawable = TextDrawable.builder().buildRound(
                    currentItem.getTodoItemTitle().substring(0, 1).toUpperCase(), drawableBackground);
        }
        viewHolder.itemDrawable.setImageDrawable(drawable);

        // Listen for the item's click events
        viewHolder.itemTextHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putLong("todoId", currentItem.getId());
                BottomSheetDialogFragment editItemDialogFragment = new EditTodoFragment();
                editItemDialogFragment.setArguments(args);
                editItemDialogFragment.show(((MainActivity)mContext).getSupportFragmentManager(),
                        editItemDialogFragment.getTag());
            }
        });

        return convertView;
    }

    /**
     * A class that holds the to-do items' views
     */
    private class TodoItemViewHolder {
        LinearLayout itemTextHolder;
        TextView itemTitle;
        TextView itemDescription;
        ImageView itemDrawable;
        View mView;

        public TodoItemViewHolder(View view) {
            mView = view;
            itemTextHolder = (LinearLayout) view.findViewById(R.id.to_do_item_text);
            itemTitle = (TextView) view.findViewById(R.id.todo_item_title);
            itemDescription = (TextView) view.findViewById(R.id.todo_item_description);
            itemDrawable = (ImageView) view.findViewById(R.id.todo_drawable);
        }
    }
}
