package id.co.kakaroto.iak_sqlitedemo.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.kakaroto.iak_sqlitedemo.model.Person;

/**
 * Created by kakaroto on 19/11/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "student";
    private static final String TABLE_PERSON = "person";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_HOBY = "hoby";
    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_PERSON
                + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
                + " TEXT," + KEY_HOBY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_PERSON;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_HOBY, person.getHoby());
        db.insert(TABLE_PERSON, null, values);
        db.close();
    }


    public Person getPersonById(int id) {
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PERSON, new String[]{KEY_ID, KEY_NAME, KEY_HOBY},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null,
                null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Person person = new Person();
        person.setId(Integer.parseInt(cursor.getString(0)));
        person.setName(cursor.getString(1));
        person.setHoby(cursor.getString(2));
        return person;
    }

    public Person getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PERSON, new String[]{KEY_ID,
                        KEY_NAME, KEY_HOBY}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Person person = new Person(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return person;
    }

    public List<Person> getAllPerson() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Person> persons = new ArrayList<>();
        String selectAllUser = "SELECT * FROM " + TABLE_PERSON + " ORDER BY " + KEY_ID + " DESC";

        Cursor cursor = sqLiteDatabase.rawQuery(selectAllUser, null);
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(cursor.getInt(0));
                person.setName(cursor.getString(1));
                person.setHoby(cursor.getString(2));
                persons.add(person);
            } while (cursor.moveToNext());
        }
        return persons;
    }

    public void deletePerson(Person person) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_PERSON, KEY_ID + " = ?",
                new String[]{String.valueOf(person.getId())});
    }


}
