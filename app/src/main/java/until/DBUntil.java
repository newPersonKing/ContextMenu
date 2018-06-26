package until;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dao.DaoMaster;
import com.dao.DaoSession;
import com.dao.MovieDao;
import com.gy.smartbardemo.Movie;

import java.util.List;

public class DBUntil  {
    public static DaoMaster.DevOpenHelper openHelper;
    public static DaoSession mDaoSession;
    public static void init(Context context){
        openHelper = new DaoMaster.DevOpenHelper(context, "notes-db", null);

        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    public static void insert(Movie movie){
        mDaoSession.getMovieDao().insert(movie);
    }

    public static List<Movie> query(){
       return mDaoSession.getMovieDao().loadAll();
    }

    public static void delete(Long movie){
         mDaoSession.getMovieDao().deleteByKey(movie);
    }

    public static  void upDate(Movie movie){
        mDaoSession.getMovieDao().update(movie);
    }

}
