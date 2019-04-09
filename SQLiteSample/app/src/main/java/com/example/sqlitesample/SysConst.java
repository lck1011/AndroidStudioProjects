package com.example.sqlitesample;

public interface SysConst {
    //文件名
    String DATABASE_NAME = "Health.db";
    //健康表名
    String TABLE_NAME = "Health";
    //应用的数据版本
    int DATABASE_VERSION = 2;
    //日期
    String TABLE_FIELD_DATE = "_id";
    //摄入热量
    String TABLE_FIELD_INPUT = "input";
    //消耗热量
    String TABLE_FIELD_OUTPUT = "output";
    //体重
    String TABLE_FIELD_WEIGHT = "weight";
    //运动情况
    String TABLE_FIELD_AMOUNTEXERCISE = "amountExercise";
    //日期格式
    String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

}