package info.jimmykamau.todo.models;

/**
 * Created by jimmy on 30/04/2017.
 */

public class TodoItem {
    private String mTodoItemTitle;
    private String mTodoItemDescription;
    private boolean mComplete;

    public TodoItem(String todoItemTitle, String todoItemDescription) {
        this.mTodoItemTitle = todoItemTitle;
        this.mTodoItemDescription = todoItemDescription;
        this.mComplete = false;
    }
    public void markTodoComplete(boolean complete) { mComplete = complete; }

    public String getTodoItemTitle() { return mTodoItemTitle; }
    public String getTodoItemDescription() { return mTodoItemDescription; }
    public boolean checkTodoComplete() { return mComplete; }
}
