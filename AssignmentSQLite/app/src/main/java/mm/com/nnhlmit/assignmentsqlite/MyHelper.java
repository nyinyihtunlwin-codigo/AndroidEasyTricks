package mm.com.nnhlmit.assignmentsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MIT69 on 12/23/2016.
 */

public class MyHelper extends SQLiteOpenHelper {
    public static final String DB_Name = "Student";
    public static final String TB_Name = "info";
    public static final String fName = "fName";
    public static final String lName = "lName";
    public static final String Gender = "gender";
    public static final String Nrc = "nrc";
    public static final String Pass = "pass";
    public static final String Phno = "phno";
    public static final String City = "city";
    public static final String Address = "address";
    public static final String Student_ID = "_id";
    public static final int DB_VERSION = 1;

    public MyHelper(Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TB_Name + " (" + Student_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + fName
                + " VARCHAR(225)," + lName
                + " VARCHAR(225)," + Address
                + " VARCHAR(225)," + Nrc
                + " VARCHAR(225)," + Phno
                + " VARCHAR(225)," + Pass
                + " VARCHAR(225)," + City
                + " VARCHAR(225)," + Gender
                + " INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_Name);
        onCreate(db);
    }
}
