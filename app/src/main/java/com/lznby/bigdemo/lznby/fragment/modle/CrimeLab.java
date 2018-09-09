package com.lznby.bigdemo.lznby.fragment.modle;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Lznby
 * @time 2018/9/4 9:47
 * Class Note:单例设计模式,用于存放Crime集合的信息。
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    /**
     *  获取CrimeLab单例对象的方法
     * @param context
     * @return
     */
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    /**
     * 私有的构造方法
     * @param context
     */
    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setContent("Crime #" + i);
            mCrimes.add(crime);
        }
    }


    /**
     * 获得单例中存储的所有Crime对象
     * @return
     */
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    /**
     * 获取指定id的Crime对象
     * @param id
     * @return
     */
    public Crime getCrime (UUID id) {
        for (Crime crime : mCrimes) {

            if (crime.getUUID().equals(id)) {
                return crime;
            }
        }

        return null;
    }
}
