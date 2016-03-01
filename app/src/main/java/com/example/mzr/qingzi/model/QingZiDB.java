package com.example.mzr.qingzi.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mzr.qingzi.db.QingZiOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class QingZiDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME="qing_zi";

    /**
     * 数据库版本
     */
    public static final int VERSION=1;
    private static QingZiDB qingZiDB;
    private SQLiteDatabase db;

    /**
     * 构造方法私有化
     */
    private QingZiDB(Context context){
        QingZiOpenHelper dbHelper =new QingZiOpenHelper(context,DB_NAME,null,VERSION);
        db =dbHelper.getWritableDatabase();
    }
    /**
     * 获取QingZiDB实例
     */
    public synchronized static QingZiDB getInstance(Context context){
        if (qingZiDB==null){
            qingZiDB = new QingZiDB(context);
        }
        return qingZiDB;
    }
    /**
     * 将Province实例存到数据库
     */
    public void saveProvince(Province province){
        if (province!=null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinveName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    /**
     * 从数据库读取省份信息
     */
    public List<Province>loadProvinces(int provinceId) {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinveName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }
            while (cursor.moveToNext());
        }   return list;
    }
    /**
     * 将City实例存到数据库
     */
    public void saveCity(City city){
        if (city!=null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityName());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }
    /**
     * 从数据库读取某省下的城市信息
     */
    public List<Province>loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null,"province_id=?", new String[]{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }
            while (cursor.moveToNext());
        }   return list;
}
    /**
     * 将country实例存储到数据库
     */
    public void saveCountry(Country country){
        if (country!=null){
            ContentValues values = new ContentValues();
            values.put("country_name",country.getCountryName());
            values.put("country_code",country.getCountryName());
            values.put("city_id",country.getCityId());
            db.insert("Country",null,values);
        }
    }
    /**
     * 从数据库读取某城市下所有县级信息
     */
    public List<Province>loadCities(int cityId) {
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query("Country", null,"city_id=?", new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
                country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
                country.setCityId(cityId);
                list.add(country);
            }
            while (cursor.moveToNext());
        }   return list;
    }
}
