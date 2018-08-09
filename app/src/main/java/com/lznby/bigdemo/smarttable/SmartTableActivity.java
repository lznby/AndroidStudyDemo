package com.lznby.bigdemo.smarttable;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.bin.david.form.utils.DensityUtils;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.smarttable.modle.PeopleModel;
import com.lznby.bigdemo.smarttable.modle.UserInfo;
import com.lznby.bigdemo.smarttable.modle.UserInfo2;
import com.lznby.bigdemo.tools.ARouterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lznby
 * @time 2018/7/16 19:20
 * Class Note: SmartTable控件使用
 */
@Route(path = ARouterTools.SmartTableActivity)
public class SmartTableActivity extends AppCompatActivity {

    //1.使用黄油刀绑定视图

    @BindView(R.id.table)
    SmartTable mTable;
    @BindView(R.id.table2)
    SmartTable mTable2;
    @BindView(R.id.table3)
    SmartTable mTable3;

    /**
     * 未注解的方式时需要创建TableData
     */
    TableData<UserInfo2> tableData;
    TableData<PeopleModel> tableData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_table);

        //2.将黄油刀框架绑定到这个Activity上
        ButterKnife.bind(this);

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        initTableOne();
        initTableTwo();
        initTableThree();
    }

    /**
     * 以注解的方式创建表格(一)
     */
    private void initTableOne() {
        mTable.setData(initTableOneData());
    }

    /**
     * 普通方式创建表格(二)
     */
    private void initTableTwo() {
        //定义表格中的列：参数一：列名;参数二：表格对应实体类中的成员变量名
        Column<String> nameColumn = new Column<String>("姓名", "name");
        Column<String> ageColumn = new Column<String>("年龄", "age");
        Column<String> timeColumn = new Column<String>("时间", "time");
        Column<String> noteColumn = new Column<String>("备注", "note");

        //将多个列归属于同一列
        Column groupColumn = new Column("个人信息", nameColumn, ageColumn);

        //设置列点击事件监听
        nameColumn.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String o, int position) {
                Toast.makeText(SmartTableActivity.this, "点击了！" + "value:" + value + ";position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //设置列点击事件监听
        ageColumn.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String o, int position) {
                Toast.makeText(SmartTableActivity.this, "点击了！" + "value:" + value + ";position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //设置tableData数据
        tableData = new TableData<UserInfo2>("测试", initTableTwoData(), groupColumn, timeColumn, noteColumn);
        //设置行点击事件监听
        tableData.setOnRowClickListener(new TableData.OnRowClickListener<UserInfo2>() {
            @Override
            public void onClick(Column column, UserInfo2 userInfo2, int col, int row) {
                Toast.makeText(SmartTableActivity.this, "RowOnClickListener" + ";column:" + col + ";row:" + row, Toast.LENGTH_SHORT).show();
            }
        });
        //不显示计数
        tableData.setShowCount(false);
        //是否显示x-y轴的序列
        mTable2.getConfig().setShowXSequence(false).setShowYSequence(false);
        //是否固定标题栏
        mTable2.getConfig().setFixedTitle(false);
        //为表格设置数据
        mTable2.setTableData(tableData);
    }

    /**
     * 普通方式创建表格(三)
     * 关于点击事件的问题:如果几列合并到同一列下,则这几列的行(Raw)点击事件则失效,需要一个一个的设置列点击事件
     */
    private void initTableThree() {
        mTable3 = (SmartTable<PeopleModel>) findViewById(R.id.table3);

        Column<String> name = new Column<String>("姓名", "name");
        Column<String> democraticTime = new Column<String>("时间", "democraticTime");
        Column<String> democraticMeetingShouldCount = new Column<String>("应到", "democraticMeetingShouldCount");
        Column<String> democraticMeetingActualCount = new Column<String>("实到", "democraticMeetingActualCount");
        Column<String> democraticMeetingValidCount = new Column<String>("有效", "democraticMeetingValidCount");
        Column<String> democraticMeetingRank = new Column<String>("得票/\n名次", "democraticMeetingRank");
        Column<String> democraticTalkValidCount = new Column<String>("应到", "democraticTalkValidCount");
        Column<String> democraticTalkActualCount = new Column<String>("实到", "democraticTalkActualCount");
        Column<String> democraticTalkRank = new Column<String>("得票/名次", "democraticTalkRank");
        Column<String> opinionTime = new Column<String>("时间", "opinionTime");
        Column<String> opinionEmitCount = new Column<String>("发出", "opinionEmitCount");
        Column<String> opinionWithdrawCount = new Column<String>("回收", "opinionWithdrawCount");
        Column<String> opinionAgreeCountAgreeCount = new Column<String>("同意", "opinionAgreeCountAgreeCount");
        Column<String> opinionAgreeCountDisagreeCount = new Column<String>("不同意", "opinionAgreeCountDisagreeCount");
        Column<String> evaluationTime = new Column<String>("时间", "evaluationTime");
        Column<String> evaluationEmitCount = new Column<String>("发出", "evaluationEmitCount");
        Column<String> evaluationWithdrawCount = new Column<String>("回收", "evaluationWithdrawCount");
        Column<String> evaluationExcellentCount = new Column<String>("优秀", "evaluationExcellentCount");
        Column<String> evaluationQualified = new Column<String>("称职", "evaluationQualified");
        Column<String> evaluationBasicQualified = new Column<String>("基本称职", "evaluationBasicQualified");
        Column<String> evaluationUnqualified = new Column<String>("不称职", "evaluationUnqualified");
        Column<String> assessInfo = new Column<String>("年度考核情况", "assessInfo");
        Column meeting = new Column("会议", democraticMeetingShouldCount, democraticMeetingActualCount, democraticMeetingValidCount, democraticMeetingRank);
        Column talk = new Column("谈话", democraticTalkValidCount, democraticTalkActualCount, democraticTalkRank);
        Column democratic = new Column("民主推荐", democraticTime, meeting, talk);
        Column opinion = new Column("征求意见（是否同意提任或正式任职）", opinionTime, opinionEmitCount, opinionWithdrawCount, opinionAgreeCountAgreeCount, opinionAgreeCountDisagreeCount);
        Column appraise = new Column("综合评价", evaluationQualified, evaluationBasicQualified, evaluationUnqualified);
        Column evaluation = new Column("民主评测", evaluationTime, evaluationEmitCount, evaluationWithdrawCount, evaluationExcellentCount, appraise);

        //列属性设置文字内容对齐方式
        assessInfo.setTextAlign(Paint.Align.LEFT);

        //列属性设置宽度
        assessInfo.setWidth(320);

        //设置是否固定某一列
        name.setFixed(true);

        //设置表格数据(包含的行、数据及标题)
        tableData2 = new TableData<PeopleModel>("民主测评表", initTableThreeData(), name, democratic, opinion, evaluation, assessInfo);

        //不显示计数
        tableData2.setShowCount(false);

        //设置行点击事件
        tableData2.setOnRowClickListener(new TableData.OnRowClickListener<PeopleModel>() {
            @Override
            public void onClick(Column column, PeopleModel userInfo2, int col, int row) {
                Toast.makeText(SmartTableActivity.this, "RowOnClickListener" + ";column:" + col + ";row:" + row, Toast.LENGTH_SHORT).show();
            }
        });

        //设置列点击事件
        democraticTime.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this, "测试点击事件" + ";position:" + position + ";value:" + value + ";s:" + s, Toast.LENGTH_SHORT).show();
            }
        });

        //是否显示x-y轴的序列
        mTable3.getConfig().setShowXSequence(false).setShowYSequence(false);

        //是否固定标题栏
        mTable3.getConfig().setFixedTitle(true);

        //设置大小是否可以缩放
        mTable3.setZoom(true, 1.0f, 0.5f);

        //FontStyle设置全局font属性;设置全局字体大小
        FontStyle.setDefaultTextSize(DensityUtils.sp2px(this, 12));

        //设置表格的背景颜色
        ICellBackgroundFormat<CellInfo> backgroundFormat = new BaseCellBackgroundFormat<CellInfo>() {
            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                if (cellInfo.row % 2 == 0) {
                    return ContextCompat.getColor(SmartTableActivity.this, R.color.backgroundGray);
                }
                return TableConfig.INVALID_COLOR;
            }
        };

        //设置表格的背景颜色
        mTable3.getConfig().setContentCellBackgroundFormat(backgroundFormat);

        //设置表格的标题字体样式
        FontStyle titleFontStyle = new FontStyle(20, Color.BLACK);
        mTable3.getConfig().setTableTitleStyle(titleFontStyle);
        //不显示表格标题
        mTable3.getConfig().setShowTableTitle(false);

        //设置Column的标题字体样式
        mTable3.getConfig().setColumnTitleStyle(titleFontStyle);

        //设置列标题的内边距
        mTable3.getConfig().setSequenceHorizontalPadding(1);
        mTable3.getConfig().setColumnTitleVerticalPadding(5);
        mTable3.getConfig().setColumnTitleVerticalPadding(4);
        mTable3.getConfig().setColumnTitleHorizontalPadding(4);


        //设置行的上下内边差距
        mTable3.getConfig().setVerticalPadding(10);
        mTable3.getConfig().setHorizontalPadding(10);

        //设置表格数据
        mTable3.setTableData(tableData2);

    }

    /**
     * 构建表格一的模拟数据
     *
     * @return
     */
    private List<UserInfo> initTableOneData() {
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new UserInfo("姓名" + i, "年龄" + i, "乱起八糟" + i, "状态" + i));
        }
        return list;
    }

    /**
     * 构建表格二的模拟数据
     *
     * @return
     */
    private List<UserInfo2> initTableTwoData() {
        List<UserInfo2> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new UserInfo2("姓名" + i, "年龄" + i, "时间" + i, "备注" + i));
        }
        return list;
    }

    /**
     * 构建表格三的模拟数据
     *
     * @return
     */
    private List<PeopleModel> initTableThreeData() {
        List<PeopleModel> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            PeopleModel peopleModel = new PeopleModel();
            peopleModel.setName("姓名姓名");
            peopleModel.setDemocraticTime("2018.02");
            peopleModel.setDemocraticMeetingShouldCount("应到");
            peopleModel.setDemocraticMeetingActualCount("100");
            peopleModel.setDemocraticMeetingValidCount("有效");
            peopleModel.setDemocraticMeetingRank("得票/名次");
            peopleModel.setDemocraticTalkValidCount("应到");
            peopleModel.setDemocraticTalkActualCount("实到");
            peopleModel.setDemocraticTalkRank("190/999");
            peopleModel.setOpinionTime("2018.02");
            peopleModel.setOpinionEmitCount("发出");
            peopleModel.setOpinionWithdrawCount("回收");
            peopleModel.setOpinionAgreeCountAgreeCount("同意");
            peopleModel.setOpinionAgreeCountDisagreeCount("不同意");
            peopleModel.setEvaluationTime("2018.02");
            peopleModel.setEvaluationEmitCount("发出");
            peopleModel.setEvaluationWithdrawCount("回收");
            peopleModel.setEvaluationExcellentCount("优秀");
            peopleModel.setEvaluationQualified("称职");
            peopleModel.setEvaluationBasicQualified("基本称职");
            peopleModel.setEvaluationUnqualified("不称职");
            peopleModel.setAssessInfo("年度考核情况年度考核情况年度考核情况年度考核情况年度考核情况年度考核情况年度考核情况年度考核情况年度考核情况年度考核情况");
            list.add(peopleModel);
        }

        return list;
    }
}
