package com.websarva.wings.android.a4taku;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //どのメソッドからでも使えるための準備
    private TextView tvCount;
    private TextView tvQuestion;
    private Button ansBtn1;
    private Button ansBtn2;
    private Button ansBtn3;
    private Button ansBtn4;
    private Button nextBtn;

    private int i =0;

    //１）クイズデータを準備
    String quizData[][] = {
            // {"問題", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"問題A", "A1", "A2", "A3", "A4"},
            {"問題B", "B1", "B2", "B3", "B4"},
            {"問題C", "C1", "C2", "C3", "C4"},
            {"問題D", "D1", "D2", "D3", "D4"},
            {"問題E", "E1", "E2", "E3", "E4"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //２）idを取得
        getId();


        //３）クイズを表示(練習)
//        tvCount.setText("残り5問");
//        tvQuestion.setText(quizData[0][0]);
//        ansBtn1.setText(quizData[0][1]);
//        ansBtn2.setText(quizData[0][2]);
//        ansBtn3.setText(quizData[0][3]);
//        ansBtn4.setText(quizData[0][4]);


        showQuiz();

    }


    //まずidを取得して準備
    public void getId(){
        tvCount = findViewById(R.id.tvCount);
        tvQuestion = findViewById(R.id.tvQuestion);
        ansBtn1 = findViewById(R.id.ansBtn1);
        ansBtn2 = findViewById(R.id.ansBtn2);
        ansBtn3 = findViewById(R.id.ansBtn3);
        ansBtn4 = findViewById(R.id.ansBtn4);
        nextBtn = findViewById(R.id.nextBtn);
    }

    //３）クイズを表示
    public void showQuiz(){

        //４）シャッフル
        List<Integer> num  = Arrays.asList(1,2,3,4);
        Collections.shuffle(num);

        tvCount.setText("残り" + (5-i) + "問");
        tvQuestion.setText(quizData[i][0]);
        ansBtn1.setText(quizData[i][num.get(0)]);
        ansBtn2.setText(quizData[i][num.get(1)]);
        ansBtn3.setText(quizData[i][num.get(2)]);
        ansBtn4.setText(quizData[i][num.get(3)]);
    }

    //５）ボタンが押されたときの正誤判定
    public void onButton(View view){
        //押されたボタン
        Button clickedBtn =(Button)view;
        String clickedAns =clickedBtn.getText().toString();

        if(clickedAns.equals(quizData[i][1])){
            clickedBtn.setText("正解！");

            //ボタンを無効化、ネクストボタンを有効化
            ansBtn1.setEnabled(false);
            ansBtn2.setEnabled(false);
            ansBtn3.setEnabled(false);
            ansBtn4.setEnabled(false);
            nextBtn.setEnabled(true);

            //i++;
            if(i ==4){
                //７）アクティビティ画面作成
                //８）画面遷移
                Intent intent = new Intent(this,Result.class);
                startActivity(intent);
                finish();

            }else{
                i++;
            }

        }else{
            clickedBtn.setText("不正解！");
           // tvQuestion.setText("Game over");

            //ボタンを無効化
            ansBtn1.setEnabled(true);
            ansBtn2.setEnabled(true);
            ansBtn3.setEnabled(true);
            ansBtn4.setEnabled(true);
            nextBtn.setEnabled(false);
        }
    }

    //６）Nextボタンが押された時の処理
    public void onNext(View view){
        showQuiz();

        ansBtn1.setEnabled(true);
        ansBtn2.setEnabled(true);
        ansBtn3.setEnabled(true);
        ansBtn4.setEnabled(true);
    }

}