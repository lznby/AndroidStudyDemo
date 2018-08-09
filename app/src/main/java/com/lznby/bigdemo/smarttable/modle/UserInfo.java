package com.lznby.bigdemo.smarttable.modle;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * @author Lznby
 * @time 2018/7/25 21:46
 * Class Note:
 */
@SmartTable(name = "用户信息列表")
public class UserInfo {
    @SmartColumn(id = 1, name = "姓名")
    private String name;
    @SmartColumn(id = 2, name = "年龄")
    private String age;
    @SmartColumn(id = 3, name = "更新时间",minWidth = 100)
    private String update;
    @SmartColumn(id = 4, name = "选中状态")
    private String time;

    public UserInfo(String name, String age, String update, String time) {
        this.name = name;
        this.age = age;
        this.update = update;
        this.time = time;
    }

    public UserInfo() {
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

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
