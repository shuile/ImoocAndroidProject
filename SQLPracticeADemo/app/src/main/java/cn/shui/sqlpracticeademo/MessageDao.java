package cn.shui.sqlpracticeademo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    private static final String LEARNER = "learner";
    private static final String NAME = "name";
    private SQLiteDatabase db;

    MessageDao(Context context) {
        this.db = new DatabaseOpenHelper(context).getReadableDatabase();
    }

    //增
    void addMessage(Message msg) {
        int learner = msg.getLearner();
        String name = msg.getName();
        ContentValues values = new ContentValues();
        values.put(LEARNER, learner);
        values.put(NAME, name);
        db.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
    }

    //增
    public void addMessageList(List<Message> msgList) {
        for (Message msg : msgList) {
            addMessage(msg);
        }
    }

    //查
    public List<Message> getMessageList() {
        List<Message> msgList = new ArrayList<>();
        Cursor cursor = db.query(DatabaseOpenHelper.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            int learner = cursor.getInt(1);
            String name = cursor.getString(2);
            Message msg = new Message(_id, learner, name);
            msgList.add(msg);
        }
        cursor.close();
        return msgList;
    }

    //删
    public boolean deleteMessage(Message msg) {
        int _id = msg.getId();
        int count = db.delete(DatabaseOpenHelper.TABLE_NAME, "_id=?", new String[]{"" + _id});
        return count > 0;
    }

    //改
    public boolean updateMessage(Message msg) {
        int _id = msg.getId();
        int learner = msg.getLearner();
        String name = msg.getName();
        ContentValues values = new ContentValues();
        values.put(LEARNER, learner);
        values.put(NAME, name);
        int count = db.update(DatabaseOpenHelper.TABLE_NAME, values, "_id=?", new String[]{"" + _id});
        return count > 0;
    }
}
