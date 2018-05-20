package lr31301207.zucc.com.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class HistoryActivity extends ListActivity {
    private Db db;
    private SQLiteDatabase dbRead,dbwrite;
    private SimpleCursorAdapter adapter;
    private static HistoryActivity historyActivity=null;


    public static HistoryActivity getSearchData() {
        return historyActivity;
    }

    public HistoryActivity() {
        historyActivity=this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_history);

        db=new Db(this);
        dbRead=db.getReadableDatabase();
        dbwrite=db.getWritableDatabase();
        adapter=new SimpleCursorAdapter(this, R.layout.list, null, new String []{"ForCode","HomCode","nowTime"}, new int[]{R.id.tv_forCode,R.id.tv_homCode,R.id.tv_time});
        setListAdapter(adapter);
        refreshListView();

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {          /* 长按事件*/

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                new AlertDialog.Builder(HistoryActivity.this).setTitle("提醒").setMessage("确定删除吗？").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Cursor c=adapter.getCursor();
                        c.moveToPosition(arg2);
                        int itemId=c.getInt(c.getColumnIndex("_id"));
                        dbwrite.delete("user","_id=?", new String[]{itemId+""});
                        refreshListView();

                    }
                }).show();


                return true;
            }
        });

    }


    /* public void addName(String forCode, String homCode,String time){
         ContentValues cv=new ContentValues();
         cv.put("ForCode",forCode);
         cv.put("HomCode",homCode);
         cv.put("nowTime",time);
         dbwrite.insert("user", null, cv);
        // refreshListView();
     }*/
    private void refreshListView(){
        Cursor c=dbRead.query("user", null, null, null, null, null, null);
        c = dbRead.rawQuery("select * from user", null);
        adapter.changeCursor(c);
    }
}

