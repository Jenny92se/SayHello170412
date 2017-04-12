package ideallions.sayhello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {

    String lang_code, lang_tab_name;
    private Menu listMenu;

    RecyclerView rv;
    NationAdapter adapter;
    EditText et_search;
    ArrayList<SingleItem> NationInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        /* 1. 언어설정 가져와서 언어 테이블 컬럼명 가지고 오기  */
        lang_code = Locale.getDefault().getLanguage();

        databaseAccess.open();
        lang_tab_name = databaseAccess.getLangTableName(lang_code);
        Log.d("getchosenlanguage0", lang_tab_name);
        /* 2. 언어에 따른 국가명 가져오기  */
        NationInfoList = databaseAccess.getNationName(lang_tab_name);
        databaseAccess.close();

        /* 3. 가지고 온 국가정보 테이블로 리사이클러뷰 생성 */
        rv = (RecyclerView) findViewById(R.id.rvNation);
        adapter = new NationAdapter(this, NationInfoList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new SlideInUpAnimator());

        /* 4. 검색어 처리  */
        et_search = (EditText) findViewById(R.id.search);
        et_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = et_search.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        listMenu=menu;
        //    Log.d("word_action", "here");

        listMenu.getItem(0).setEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId(); // 현재 이벤트 발생 메뉴의 id 추출.

        if(id==R.id.setting_language) //언어 선택
        {
            Intent dialogIntent=new Intent(this,InvokedActivity.class);
            dialogIntent.putExtra("lang", CodeToLang());
          /*  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("디버깅");
            alertDialogBuilder
                    .setMessage("code to lang" + CodeToLang())
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    // 프로그램을 종료한다
                                    finish();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();*/

            startActivityForResult(dialogIntent, LANNGUAGE_CHOSEN);
        }

        return super.onOptionsItemSelected(item);
    }
    private String LangToCode(String lang)
    {
        switch (lang)
        {
            case "Korean":
                lang_code=Locale.KOREAN.getLanguage();
                break;
            case "American":
                lang_code=Locale.ENGLISH.getLanguage();
                break;
            case "Japanese":
                lang_code=Locale.JAPANESE.getLanguage();
                break;
            case "Spanish":
                lang_code="es_ES";
                break;
            case "German":
                lang_code=Locale.GERMANY.getLanguage();
                break;
            case "French":
                lang_code=Locale.FRENCH.getLanguage();
                break;
        }
        return lang_code;
    }
    private String CodeToLang()
    {
        //   Log.d("Code",lang_code);
        //     Log.d("KoreanCode",Locale.KOREAN.getLanguage());
        String lang;
        if(lang_code.equals(Locale.KOREAN.getLanguage()))
            lang="Korean";
        else if(lang_code.equals(Locale.ENGLISH.getLanguage()))
            lang="American";
        else if(lang_code.equals(Locale.JAPANESE.getLanguage()))
            lang="Japanese";
        else if(lang_code.equals("es_ES"))
            lang="Spanish";
        else if(lang_code.equals(Locale.GERMANY.getLanguage()))
            lang="German";
        else if(lang_code.equals(Locale.FRENCH.getLanguage()))
            lang="French";
        else
            lang="Korean";

           Log.d("codeToLang", lang);
        return lang;
    }

    static final int LANNGUAGE_CHOSEN=100;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode==RESULT_OK)
        {
            if(requestCode==LANNGUAGE_CHOSEN){
                String result=data.getExtras().getString(InvokedActivity.LANGUAGE_RESULT);
                        Log.d("getchosenlanguage", result);

                LangToCode(result);
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
                databaseAccess.open();
                lang_tab_name = databaseAccess.getLangTableName(lang_code);

                NationInfoList.clear();
                NationInfoList = databaseAccess.getNationName(lang_tab_name);

                adapter.changeLanguage(NationInfoList);
                databaseAccess.close();
            }
        }

    }
}