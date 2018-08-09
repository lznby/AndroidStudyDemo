package com.lznby.bigdemo.smarttable.modle;

/**
 * @author Lznby
 * @time 2018/7/25 22:34
 * Class Note:
 */
public class UserInfo2{
    private String name;
    private String age;
    private String time;
    private String note;

    public UserInfo2(String name, String age, String time, String note) {
        this.name = name;
        this.age = age;
        this.time = time;
        this.note = note;
    }

    public UserInfo2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
