package info.jimmykamau.todo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import info.jimmykamau.todo.MainActivity;
import info.jimmykamau.todo.R;
import info.jimmykamau.todo.models.TodoItem;

/**
 * Created by jimmy on 30/04/2017.
 */

public class TodoListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<TodoItem> mTodoItemsList;
    private Context mContext;

    public TodoListAdapter(Context context, ArrayList<TodoItem> todoItems) {
        inflater = LayoutInflater.from(context);
        this.mTodoItemsList = todoItems;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mTodoItemsList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        TodoItemViewHolder viewHolder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.todo_item, parent, false);
            viewHolder = new TodoItemViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TodoItemViewHolder) convertView.getTag();
        }

        final TodoItem currentItem = mTodoItemsList.get(position);
        viewHolder.itemTitle.setText(currentItem.getTodoItemTitle());
        viewHolder.itemDescription.setText(currentItem.getTodoItemDescription());

        viewHolder.itemCompleteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).markItemAsComplete(position);
            }
        });

        viewHolder.itemTextHolder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) mContext).removeTodoItem(position);
                return true;
            }
        });

        return convertView;
    }

    private class TodoItemViewHolder {
        LinearLayout itemTextHolder;
        TextView itemTitle;
        TextView itemDescription;
        CheckBox itemCompleteStatus;
        View mView;

        public TodoItemViewHolder(View view) {
            mView = view;
            itemTextHolder = (LinearLayout) view.findViewById(R.id.to_do_item_text);
            itemTitle = (TextView) view.findViewById(R.id.todo_item_title);
            itemDescription = (TextView) view.findViewById(R.id.todo_item_description);
            itemCompleteStatus = (CheckBox) view.findViewById(R.id.todo_item_complete);
        }
    }
}
