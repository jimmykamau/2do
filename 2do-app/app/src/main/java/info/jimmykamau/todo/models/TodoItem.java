package info.jimmykamau.todo.models;

import com.orm.SugarRecord;

/**
 * Created by jimmy on 30/04/2017.
 */

public class TodoItem extends SugarRecord {
    private String itemTitle;
    private String itemDescription;
    private boolean itemComplete;

    // Empty constructor needed by Sugar ORM
    public TodoItem() {

    }

    public TodoItem(String itemTitle, String itemDescription, boolean itemComplete) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemComplete = itemComplete;
    }

    // Setter methods
    public void updateItemTitle(String itemTitle) { this.itemTitle = itemTitle; }
    public void updateItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
    public void updateItemComplete(boolean itemComplete) { this.itemComplete = itemComplete; }

    // Getter methods
    public String getTodoItemTitle() { return itemTitle; }
    public String getTodoItemDescription() { return itemDescription; }
    public boolean checkTodoComplete() { return itemComplete; }
}
