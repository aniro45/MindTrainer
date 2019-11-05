package com.example.admin.mindtrainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "aj.db";

    Cursor sqlScore;
    private static final String TABLE_NAME = "HighScore";
    private static final String col_id = "id";
    private static final String col_name = "name";
    private static final String col_highScore = "highScore";

    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_HIGHSCORE = "create table HighScore(id integer primary key autoincrement, col_name varchar(55) not null, col_highScore int(11));";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE_HIGHSCORE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS highScore");

//        database.execSQL("INSERT INTO highScore VALUES('', 'aniket', score)");

        onCreate(database);


    }


    boolean addHighScore(int highScore) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(col_name, "aniket");
        contentValues.put(col_highScore, highScore);
        SQLiteDatabase db = getWritableDatabase();

        return db.insert(DATABASE_CREATE_HIGHSCORE, null, contentValues) != -1;

    }

    Cursor getHighScore() {

        {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery("SELECT col_highScore FROM " + TABLE_NAME, null);

        }
    }
}


