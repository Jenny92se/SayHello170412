package ideallions.sayhello;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

    private TextView text1, text2, text3;
    private TextView subText1, subText2, subText3;
    ObjectAnimator aniSplash;
    ObjectAnimator aniClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        String ncode;
        HelloItem item = new HelloItem();

        Intent intent = getIntent();
        ncode = intent.getStringExtra("nation_code");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        item = databaseAccess.getHelloItem(ncode);
        databaseAccess.close();

        item.setNcode(item.getNcode());

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        subText1 = (TextView) findViewById(R.id.text1sub);
        subText2 = (TextView) findViewById(R.id.text2sub);
        subText3 = (TextView) findViewById(R.id.text3sub);

        text1.setText(item.getTitle1());
        text2.setText(item.getTitle2());
        text3.setText(item.getTitle3());
        text1.setAlpha(0f);
        text2.setAlpha(0f);
        text3.setAlpha(0f);

        subText1.setText(item.getSub1());
        subText2.setText(item.getSub2());
        subText3.setText(item.getSub3());
        subText1.setAlpha(0f);
        subText2.setAlpha(0f);
        subText3.setAlpha(0f);

        aniSplash = ObjectAnimator.ofFloat(text1, "alpha", 0f, 1f);
        aniSplash.setDuration(400);
        aniSplash.start();

        aniSplash.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                aniSplash = ObjectAnimator.ofFloat(text2, "alpha", 0f, 1f);
                aniSplash.setDuration(400);
                aniSplash.start();
                aniSplash.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        aniSplash = ObjectAnimator.ofFloat(text3, "alpha", 0f, 1f);
                        aniSplash.setDuration(400);
                        aniSplash.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.animate().scaleX(0.6f).scaleY(0.6f).setDuration(400).start();

                aniClick = ObjectAnimator.ofFloat(subText1, "alpha", 0f, 1f);
                aniClick.setDuration(400);
                aniClick.start();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text2.animate().scaleX(0.6f).scaleY(0.6f).setDuration(400).start();

                aniClick = ObjectAnimator.ofFloat(subText2, "alpha", 0f, 1f);
                aniClick.setDuration(400);
                aniClick.start();
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text3.animate().scaleX(0.6f).scaleY(0.6f).setDuration(400).start();

                aniClick = ObjectAnimator.ofFloat(subText3, "alpha", 0f, 1f);
                aniClick.setDuration(400);
                aniClick.start();
            }
        });

        subText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AssetFileDescriptor afd = getAssets().openFd("audioSpeech/recorded.mp3");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                } catch (Exception ex) {
                    Log.d("playException", "occured");
                }
            }
        });

        subText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AssetFileDescriptor afd = getAssets().openFd("audioSpeech/recorded.mp3");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                } catch (Exception ex) {
                    Log.d("playException", "occured");
                }
            }
        });

        subText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AssetFileDescriptor afd = getAssets().openFd("audioSpeech/recorded.mp3");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                } catch (Exception ex) {
                    Log.d("playException", "occured");
                }
            }
        });
    }
}
