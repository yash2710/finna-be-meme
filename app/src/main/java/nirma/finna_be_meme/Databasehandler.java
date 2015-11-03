package nirma.finna_be_meme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajiv on 10/26/2015.
 */
public class Databasehandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "id_name";

    // Contacts table name
    private static final String TABLE_NAME = "info";

    // Contacts Table Columns names
    private static final String P_ID = "id";
    private static final String P_NAME = "name";
    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + P_ID + " INTEGER PRIMARY KEY," + P_NAME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
    void addPatient(Data Data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(P_ID, Data.getPatient_id());
        values.put(P_NAME, Data.getPatient_name());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    public List<Data> getAllData() {
        List<Data> contactList = new ArrayList<Data>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setPatient_id(Integer.parseInt(cursor.getString(0)));
                data.setPatient_name(cursor.getString(1));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
