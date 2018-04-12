package Lab5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by quynh on 2018-02-26.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Message.db";
    public static int VERSION_NUM = 13;
    public static String TABLE_NAME = "Messenger";
    public static final String KEY_ID = "id";
    public static final String KEY_MESSAGE = "message";

    private static final String SQL = "CREATE TABLE " + TABLE_NAME + " ( " +
            KEY_ID + " integer primary key autoincrement, "
            + KEY_MESSAGE + " TEXT ); ";
    private static final String ACTIVITY_NAME = "ChatDatabaseHelper";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion=" + newVer);
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id);
    }

    public Cursor selectQuery() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}
