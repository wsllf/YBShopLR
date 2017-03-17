package com.android.leleyouba.ybshop.mine.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Environment;


import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.widget.model.CityModel;
import com.android.leleyouba.ybshop.mine.widget.model.DistrictModel;
import com.android.leleyouba.ybshop.mine.widget.model.ProvinceModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DBManager {
    private final int BUFFER_SIZE = 1024;
    public static final String DB_NAME = "province.db";
    public static final String PACKAGE_NAME = "com.android.leleyouba.ybshop";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"+ PACKAGE_NAME;
    private SQLiteDatabase database;
    private Context context;
    private File file=null;

   public DBManager(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDatabase() {

        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
        return this.database;
    }
//    public SQLiteDatabase getDatabase(){
//        return this.database;
//    }

    private SQLiteDatabase openDatabase(String dbfile) {
        try {
            file = new File(dbfile);
            if (!file.exists()) {

                InputStream is = context.getResources().openRawResource(R.raw.province);
//                if(is!=null){
//                }else{
//                }
                FileOutputStream fos = new FileOutputStream(dbfile);
//                if(is!=null){
//                }else{
//                }
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count =is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                    fos.flush();
                }
                fos.close();
                is.close();
            }
            database = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            return database;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
        }
        return null;
    }




    public void closeDatabase() {
        if(this.database!=null)
            this.database.close();
    }


    /**
     *
     * @param db
     * @return 查询所有的省
     */
    public List<ProvinceModel> getProvice(SQLiteDatabase db){
        String sql="SELECT * FROM t_address_province ORDER BY id";
        Cursor cursor = db.rawQuery(sql,null);
        List<ProvinceModel> list=new ArrayList<ProvinceModel>();

        if (cursor!=null&&cursor.getCount() > 0) {
            while (cursor.moveToNext()){
                ProvinceModel provinceModel=new ProvinceModel();
                provinceModel.ID=cursor.getString(cursor.getColumnIndex("id"));
                provinceModel.NAME=cursor.getString(cursor.getColumnIndex("name"));
                provinceModel.CODE = cursor.getString(cursor.getColumnIndex("code"));
                list.add(provinceModel);
            }
        }
        return list;
    }

    /**
     * 根据省code查询所有的市
     * @param db
     * @param code
     * @return
     */
    public List<CityModel> getCityByParentId(SQLiteDatabase db,String code){
        String sql="SELECT * FROM t_address_city WHERE provinceCode=? ORDER BY id";
        Cursor cursor = db.rawQuery(sql,new String[]{code});
        List<CityModel> list=new ArrayList<CityModel>();

        if (cursor!=null&&cursor.getCount() > 0) {

            while (cursor.moveToNext()){
                CityModel cityModel=new CityModel();
                cityModel.ID=cursor.getString(cursor.getColumnIndex("id"));
                cityModel.NAME=cursor.getString(cursor.getColumnIndex("name"));
                cityModel.CODE = cursor.getString(cursor.getColumnIndex("code"));
                list.add(cityModel);
            }
        }
        return list;
    }

    /**
     * 根据市code查询所有的区
     * @param db
     * @param code
     * @return
     */
    public List<DistrictModel> getDistrictById(SQLiteDatabase db,String code){
        String sql="SELECT * FROM t_address_town WHERE cityCode=? ORDER BY id ";
        Cursor cursor = db.rawQuery(sql,new String[]{code});
        List<DistrictModel> list=new ArrayList<DistrictModel>();
        if (cursor!=null&&cursor.getCount() > 0) {
            while (cursor.moveToNext()){
                DistrictModel districtModel=new DistrictModel();
                districtModel.ID=cursor.getString(cursor.getColumnIndex("id"));
                districtModel.NAME=cursor.getString(cursor.getColumnIndex("name"));
                districtModel.CODE = cursor.getString(cursor.getColumnIndex("code"));
                list.add(districtModel);
            }
        }
        return list;
    }

}
