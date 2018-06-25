package com.daimao.bluebubble.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimao.bluebubble.BaseApplication;
import com.daimao.bluebubble.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * Created by Ant on 2018/6/24.
 */

public class AddPwdActivity extends XActivity {

    private static final String[] groupNameStr = {"社交", "工作", "学习", "生活", "娱乐", "其他"};
    private ArrayAdapter<String> adapter;

    private AlertDialog groupDialog;

    //    @BindView(R.id.spin_group)
    Spinner spinner;

    @BindView(R.id.et_group)
    EditText groupEditText;

    @Override
    public void initData(Bundle savedInstanceState) {
        /* ----使用 spinner 下拉框---- */
        // 将可选内容与ArrayAdapter连接起来
        // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupNameStr);
        // 设置下拉列表的风格
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        // spinner.setAdapter(adapter);
        // 添加事件Spinner事件监听
        // spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        /* ----使用 AlertDialog---- */
        groupEditText.setFocusable(false);
        groupEditText.setOnClickListener(new GroupSelectedListener());
    }

    // Spinner 使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            BaseApplication.getInstance().showTip(groupNameStr[i]);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    // 分组 选择监听器
    class GroupSelectedListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(AddPwdActivity.this);
            builder.setTitle("分组");
            builder.setCancelable(true); //设置是否可以点击对话框外部取消
            builder.setSingleChoiceItems(groupNameStr, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int item) {
                    // 所有选项索引都大于0，按钮索引都小于0
                    if (item >= 0) {
                        BaseApplication.getInstance().showTip(groupNameStr[item]);
                        // 关闭
                        groupDialog.dismiss();
                        groupEditText.setText(groupNameStr[item]);
                    } else if (item == DialogInterface.BUTTON_POSITIVE) {
                        // 确定按钮
                        BaseApplication.getInstance().showTip("已选择：" + groupNameStr[item]);

                    } else if (item == DialogInterface.BUTTON_NEGATIVE) {
                        // 取消按钮
                        BaseApplication.getInstance().showTip("没有选择");
                    }
                }
            });
            groupDialog = builder.show();
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_passwordbook_detail;
    }

    @Override
    public Object newP() {
        return null;
    }
}
