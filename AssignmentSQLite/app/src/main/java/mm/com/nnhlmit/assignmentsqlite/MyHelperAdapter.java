package mm.com.nnhlmit.assignmentsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class MyHelperAdapter {
    private Context context;
    private MyHelper helper;
    private SQLiteDatabase db;

    public MyHelperAdapter(Context context) {
        this.context = context;
    }

    public void dbOpen() {
        helper = new MyHelper(context);
        db = helper.getWritableDatabase();
    }

    public void dbClose() {
        db.close();
    }

    public long dataInsert(String fName, String lName, String nrc, String pass, String phno, String address, String city, int gender) {
        ContentValues cv = new ContentValues();
        cv.put(MyHelper.fName, fName);
        cv.put(MyHelper.lName, lName);
        cv.put(MyHelper.Nrc, nrc);
        cv.put(MyHelper.Gender, gender);
        cv.put(MyHelper.Pass, pass);
        cv.put(MyHelper.Address, address);
        cv.put(MyHelper.City, city);
        cv.put(MyHelper.Phno, phno);
        long id = db.insert(MyHelper.TB_Name, null, cv);
        return id;
    }

    public Student dataQuery(String userName, String pass) {
        String[] columns = {MyHelper.fName, MyHelper.lName, MyHelper.Nrc, MyHelper.Gender, MyHelper.Pass, MyHelper.Address, MyHelper.City, MyHelper.Phno};
        Cursor c = db.query(MyHelper.TB_Name, columns, MyHelper.fName
                + " = '" + userName + "' AND " + MyHelper.Pass + " = '" + pass + "'", null, null, null, null);
        Student student = new Student();
        if (c.getCount() == 1) {
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                int fNameIndex = c.getColumnIndex(MyHelper.fName);
                int lNameIndex = c.getColumnIndex(MyHelper.lName);
                int nrcIndex = c.getColumnIndex(MyHelper.Nrc);
                int genderIndex = c.getColumnIndex(MyHelper.Gender);
                int passIndex = c.getColumnIndex(MyHelper.Pass);
                int addressIndex = c.getColumnIndex(MyHelper.Address);
                int cityIndex = c.getColumnIndex(MyHelper.City);
                int phnoIndex = c.getColumnIndex(MyHelper.Phno);


                student.setfName(c.getString(fNameIndex));
                student.setlName(c.getString(lNameIndex));
                student.setNrc(c.getString(nrcIndex));
                student.setGender(c.getInt(genderIndex));
                student.setPass(c.getString(passIndex));
                student.setAddress(c.getString(addressIndex));
                student.setCity(c.getString(cityIndex));
                student.setPhno(c.getString(phnoIndex));
            }
            student.setfName("Success");
        } else {
            student.setfName("No Result");
        }
        return student;
    }

    public ArrayList<Student> dataUpload() {
        String[] columns = {MyHelper.fName, MyHelper.lName, MyHelper.Nrc, MyHelper.Gender, MyHelper.Pass, MyHelper.Address, MyHelper.City, MyHelper.Phno};
        Cursor c = db.query(MyHelper.TB_Name, columns, null, null, null, null, null);
        ArrayList<Student> stdList = new ArrayList<Student>();

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            int fNameIndex = c.getColumnIndex(MyHelper.fName);
            int lNameIndex = c.getColumnIndex(MyHelper.lName);
            int nrcIndex = c.getColumnIndex(MyHelper.Nrc);
            int genderIndex = c.getColumnIndex(MyHelper.Gender);
            int passIndex = c.getColumnIndex(MyHelper.Pass);
            int addressIndex = c.getColumnIndex(MyHelper.Address);
            int cityIndex = c.getColumnIndex(MyHelper.City);
            int phnoIndex = c.getColumnIndex(MyHelper.Phno);

            Student student = new Student();
            student.setfName(c.getString(fNameIndex));
            student.setlName(c.getString(lNameIndex));
            student.setNrc(c.getString(nrcIndex));
            student.setGender(c.getInt(genderIndex));
            student.setPass(c.getString(passIndex));
            student.setAddress(c.getString(addressIndex));
            student.setCity(c.getString(cityIndex));
            student.setPhno(c.getString(phnoIndex));
            stdList.add(student);
        }
        return stdList;
    }
}
