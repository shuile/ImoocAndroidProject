package com.shui.imoocexpandablelistview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.shui.imoocexpandablelistview.bean.Chapter;
import com.shui.imoocexpandablelistview.bean.ChapterItem;

import java.util.ArrayList;
import java.util.List;

public class ChapterDao {

    private static final String TAG = "ChapterDao";

    public List<Chapter> loadFromDb(Context context) {
        ChapterDbHelper dbHelper = ChapterDbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<Chapter> chapterList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + Chapter.TABLE_NAME, null);
        Chapter chapter = null;
        while (cursor.moveToNext()) {
            chapter = new Chapter();
            int id = cursor.getInt(cursor.getColumnIndex(Chapter.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(Chapter.COL_NAME));
            chapter.setId(id);
            chapter.setName(name);
            chapterList.add(chapter);
        }
        cursor.close();

        // query chapter items
        ChapterItem chapterItem = null;
        for (Chapter parent : chapterList) {
            int pid = parent.getId();
            cursor = db.rawQuery("select * from " + ChapterItem.TABLE_NAME + " where " + ChapterItem.COL_PID + " = ?",
                    new String[]{pid + ""});
            while (cursor.moveToNext()) {
                chapterItem = new ChapterItem();
                int id = cursor.getInt(cursor.getColumnIndex(chapterItem.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(chapterItem.COL_NAME));
                chapterItem.setId(id);
                chapterItem.setName(name);
                chapterItem.setPid(pid);
                parent.addChild(chapterItem);
            }
            cursor.close();
        }
        return chapterList;
    }

    public void insert2Db(Context context, List<Chapter> chapterList) {
        Log.d(TAG, "insert2Db: " + chapterList);
        if (context == null) {
            throw new IllegalArgumentException("context can not be null.");
        }
        if (chapterList == null || chapterList.isEmpty()) {
            return;
        }

        ChapterDbHelper dbHelper = ChapterDbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();

        ContentValues contentValues = null;

        for (Chapter chapter : chapterList) {
            contentValues= new ContentValues();
            contentValues.put(Chapter.COL_ID, chapter.getId());
            contentValues.put(Chapter.COL_NAME, chapter.getName());

            long l = db.insertWithOnConflict(Chapter.TABLE_NAME,
                    null,
                    contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);
            Log.d(TAG, "insert2Db: chapter: " + l);
            List<ChapterItem> children = chapter.getChildren();
            for (ChapterItem chapterItem : children) {
                contentValues= new ContentValues();
                contentValues.put(ChapterItem.COL_ID, chapterItem.getId());
                contentValues.put(ChapterItem.COL_NAME, chapterItem.getName());
                contentValues.put(ChapterItem.COL_PID, chapter.getId());
                long l1 = db.insertWithOnConflict(ChapterItem.TABLE_NAME,
                        null,
                        contentValues,
                        SQLiteDatabase.CONFLICT_REPLACE);
                Log.d(TAG, "insert2Db: chapteritem: " + l1);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
