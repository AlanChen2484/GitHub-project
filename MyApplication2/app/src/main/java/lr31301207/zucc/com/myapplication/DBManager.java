package lr31301207.zucc.com.myapplication;

/**
 * Created by Administrator on 2016/7/7.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/7/7.
 */
public class DBManager {


    private Db db;
    private SQLiteDatabase dbwrite;

    public DBManager(Context context) {
        db = new Db(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        dbwrite = db.getWritableDatabase();
    }


    public void addName(String forCode, String homCode,String time){
        ContentValues cv=new ContentValues();
        cv.put("ForCode",forCode);
        cv.put("HomCode",homCode);
        cv.put("nowTime",time);
        dbwrite.insert("user", null, cv);
        // refreshListView();
    }

    public void delete(){

    }

}
