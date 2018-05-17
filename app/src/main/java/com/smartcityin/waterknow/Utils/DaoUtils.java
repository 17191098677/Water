package com.smartcityin.waterknow.Utils;

import android.database.sqlite.SQLiteDatabase;

import com.smartcityin.waterknow.Dao.DaoMaster;
import com.smartcityin.waterknow.Dao.DaoSession;
import com.smartcityin.waterknow.Dao.UserEntityDao;
import com.smartcityin.waterknow.Entity.UserEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;

/**
 * Author : Mr.老王
 * Created on 2018/4/25
 * E-mail : wkz123011@gmail.com
 */
public class DaoUtils {
    private static DaoUtils daoUtils;
    private final DaoMaster.DevOpenHelper openHelper;
    private final SQLiteDatabase writableDatabase;
    private DaoUtils(){
        openHelper = new DaoMaster.DevOpenHelper(WaterKnowApp.WATER_CONTEXT,"User.db",null);
        writableDatabase = openHelper.getWritableDatabase();
    }
    public static DaoUtils getInstance(){
        if (daoUtils==null)
            synchronized (DaoUtils.class){
                if (daoUtils==null)
                    daoUtils=new DaoUtils();

            }
            return daoUtils;
    }
    public DaoMaster getDaoMaster(){
        return new DaoMaster(writableDatabase);
    }
    public DaoSession getDaoSession(){
        return getDaoMaster().newSession();
    }

    public UserEntityDao getDaos(){
        UserEntityDao shopDao = getDaoSession().getUserEntityDao();
        return shopDao;
    }
}
