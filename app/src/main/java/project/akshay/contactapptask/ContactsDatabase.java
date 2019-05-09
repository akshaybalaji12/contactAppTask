package project.akshay.contactapptask;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

public class ContactsDatabase implements BaseColumns {

    private static final int A_VERSION = 1;
    private static final String databaseName = "CONTACTS_DATA";
    private static final String k_id = BaseColumns._ID;
    private static final String tableName = "CONTACTS_TABLE";
    private static final String k_date_of_birth = "DATE";
    private static final String k_name = "NAME";
    private static final String k_email = "EMAIL";
    private static final String k_mobile = "MOBILE";
    private static final String k_address = "ADDRESS";
    private static final String k_pic = "PIC_ID";
    Context context;

    String CREATE_TABLE = "CREATE TABLE " + tableName + " (" + k_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            k_name + " TEXT, " + k_email + " TEXT, " + k_mobile + " TEXT, " + k_address + " TEXT, " + k_date_of_birth + " TEXT, "
            + k_pic + " INTEGER);";

    public ContactsDatabase(Context context) {
        this.context = context;
    }

    DataBase dataBase;

    public class DataBase extends SQLiteOpenHelper {

        public DataBase(Context context) {
            super(context, databaseName, null, A_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            onCreate(db);
        }
    }

    void addContact(Contacts contacts) {

        dataBase = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(k_name,contacts.getName());
        contentValues.put(k_email,contacts.getName());
        contentValues.put(k_date_of_birth,contacts.getName());
        contentValues.put(k_address,contacts.getName());
        contentValues.put(k_mobile,contacts.getName());
        contentValues.put(k_pic,contacts.getName());

        sqLiteDatabase.insert(tableName,null,contentValues);
        sqLiteDatabase.close();

        AppUtilities.printLogMessages("ADDED CONTACTS ",AppUtilities.STRING_SUCCESSFUL);

    }

}
