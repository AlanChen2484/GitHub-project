package lr31301207.zucc.com.myapplication;

/**
 * Created by Administrator on 2016/7/7.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper{

    public Db(Context context) {
        super(context, "db", null, 1);

    }

    @Override
      /* 创建表*/
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ForCode TEXT DEFAULT \"\","+
                "HomCode TEXT DEFAULT \"\","+
                "nowTime TEXT DEFAULT \"\")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
