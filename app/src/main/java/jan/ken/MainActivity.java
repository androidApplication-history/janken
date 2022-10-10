package jan.ken;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int winCnt = 0;
    private int loseCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton pRock = findViewById(R.id.p_rock);
        ImageButton pScissors = findViewById(R.id.p_scissors);
        ImageButton pPaper = findViewById(R.id.p_paper);
        Button undoBtn = findViewById(R.id.undo);
        Button resetBtn = findViewById(R.id.cntReset);

        //ImageViewのインスタンス化(いらない？)
        ImageView img = findViewById(R.id.imageView);

        pRock.setOnClickListener(view -> {
            dojanken(view);
        });
        pScissors.setOnClickListener(view -> dojanken(view));
        pPaper.setOnClickListener(view -> dojanken(view));
        undoBtn.setOnClickListener(view -> viewUndo(view));
        resetBtn.setOnClickListener(view -> reset(view));


        //クリックしたら画像を変える
        //img.setOnClickListener(view -> changeImg());

    }

    //最初から
    private void viewUndo(View view) {
        ;
        findViewById(R.id.p_rock).setVisibility(View.VISIBLE);
        findViewById(R.id.p_scissors).setVisibility(View.VISIBLE);
        findViewById(R.id.p_paper).setVisibility(View.VISIBLE);

        Bitmap img_pass;

        ImageView myImage = findViewById(R.id.imageView);
        img_pass = BitmapFactory.decodeResource(getResources(), R.drawable.jankenboys);
        myImage.setImageBitmap(img_pass);

        TextView afterText = findViewById(R.id.valueText);
        afterText.setText("");

    }

    /*手が押されたらじゃんけんする*/
    private void dojanken(View view) {

        ImageView player = (ImageView) view;
        //リソースIDを取得して判断する
        //String str = "";
        int myJankenInt = 0;
        switch (player.getId()) {
            case R.id.p_rock:
                myJankenInt = 0;
                //画像を非表示にする
                break;
            case R.id.p_scissors:
                myJankenInt = 2;
                break;
            case R.id.p_paper:
                myJankenInt = 1;
                break;
        }

        //とぐる
        //Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();

        //全部消す
        findViewById(R.id.p_rock).setVisibility(View.INVISIBLE);
        findViewById(R.id.p_scissors).setVisibility(View.INVISIBLE);
        findViewById(R.id.p_paper).setVisibility(View.INVISIBLE);

        //消した中から押されたものを再表示
        player.setVisibility(View.VISIBLE);


        Random rand = new Random();
        int randnum = rand.nextInt(3);
        //npcの挙動
        int randJanken;
        if (randnum == 0) {
            randJanken = R.drawable.rockcpu;
        } else if (randnum == 1) {
            randJanken = R.drawable.papercpu;
        } else {
            randJanken = R.drawable.scissorscpu;
        }

        TextView afterText = findViewById(R.id.valueText);
        if (randnum == myJankenInt) {
            afterText.setText("ドロー");

        } else if (!(randnum == myJankenInt)) {
            if (((myJankenInt == 0) && (randnum == 2)) ||
                    ((myJankenInt == 1) && (randnum == 0)) ||
                    ((myJankenInt == 2) && (randnum == 1))) {
                afterText.setText("You WIN!!");
                winCnt++;
            } else {
                afterText.setText("You LOSE!!");
                loseCnt++;
            }
        }

        TextView cnt = findViewById(R.id.winLoeCnt);
        cnt.setText(winCnt + "勝" + loseCnt + "敗");


        Bitmap img_pass;
        //画像の切り替え
        ImageView myImage = findViewById(R.id.imageView);
        img_pass = BitmapFactory.decodeResource(getResources(), randJanken);
        myImage.setImageBitmap(img_pass);
    }

    //勝敗数のリセット
    private void reset(View view) {
        winCnt = 0;
        loseCnt = 0;
        TextView cnt = findViewById(R.id.winLoeCnt);
        cnt.setText(winCnt + "勝" + loseCnt + "敗");
    }
}