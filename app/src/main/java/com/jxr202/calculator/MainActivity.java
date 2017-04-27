package com.jxr202.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit)
    TextView edit;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.del)
    Button del;
    @BindView(R.id.divide)
    Button divide;
    @BindView(R.id.multiply)
    Button multiply;
    @BindView(R.id.btn_7)
    Button btn7;
    @BindView(R.id.btn_8)
    Button btn8;
    @BindView(R.id.btn_9)
    Button btn9;
    @BindView(R.id.minus)
    Button minus;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;
    @BindView(R.id.btn_6)
    Button btn6;
    @BindView(R.id.plus)
    Button plus;
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_0)
    Button btn0;
    @BindView(R.id.point)
    Button point;
    @BindView(R.id.equal)
    Button equal;

    private boolean needClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.clear, R.id.del, R.id.divide, R.id.multiply, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.minus, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.plus, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_0, R.id.point, R.id.equal})
    public void onClick(View view) {
        String val = edit.getText().toString();
        switch (view.getId()) {
            case R.id.clear: {
                edit.setText("");
                break;
            }
            case R.id.del: {
                if (!val.equals("")) {
                    edit.setText(val.substring(0, val.length() - 1));
                }
                break;
            }
            case R.id.plus:
            case R.id.minus:
            case R.id.multiply:
            case R.id.divide: {
                if (needClear) {
                    edit.setText("");
                    needClear = false;
                }
                edit.setText(val + " " + ((Button) view).getText() + " ");
                break;
            }
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.point: {
                if (needClear) {
                    val = "";
                    edit.setText("");
                    needClear = false;
                }
                edit.setText(val + ((Button) view).getText());
                break;
            }
            case R.id.equal: {
                getResult();
                break;
            }
        }
    }

    private void getResult() {
        needClear = true;
        String exp = edit.getText().toString();
        double r = 0;
        int space = exp.indexOf(' ');//用于搜索空格位置
        String s1 = exp.substring(0, space);//s1用于保存第一个运算数
        String op = exp.substring(space + 1, space + 2);//op用于保存运算符
        String s2 = exp.substring(space + 3);//s2用于保存第二个运算数

        double arg1 = Double.parseDouble(s1);//将运算数从string转换为Single
        double arg2 = Double.parseDouble(s2);
        if (op.equals("＋")) {
            r = arg1 + arg2;
        } else if (op.equals("－")) {
            r = arg1 - arg2;
        } else if (op.equals("×")) {
            r = arg1 * arg2;
        } else if (op.equals("÷")) {
            if (arg2 == 0) {
                r = 0;
            } else {
                r = arg1 / arg2;
            }
        }
        if (!s1.contains(".") && !s2.contains(".")) {
            int result = (int) r;
            edit.setText(result + "");
        } else {
            edit.setText(r + "");
        }
    }
}
