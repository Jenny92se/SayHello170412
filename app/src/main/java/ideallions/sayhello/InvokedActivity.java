package ideallions.sayhello;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ActionMode;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class InvokedActivity extends Activity implements View.OnClickListener {

    ActionMode mActionMode;

    Button choose_language;
    Button cancel_language;
    ListView language_listview;
    ArrayList language_list;
    String initLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.invoke_dialog);
        Intent intent = getIntent();
        initLang = intent.getExtras().getString("lang");
     //   Log.d("getLanguage",initLang);
        showDialog();

    }

    public void showDialog()
    {
        DisplayMetrics disp = getApplicationContext().getResources().getDisplayMetrics();
        int width = (int) (disp.widthPixels * 0.7); //Display 사이즈의 70%
        int height = (int) (disp.heightPixels * 0.4);  //Display 사이즈의 90%
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        choose_language=(Button)findViewById(R.id.choose_language);
        cancel_language=(Button)findViewById(R.id.cancel_language);
        choose_language.setOnClickListener(this);
        cancel_language.setOnClickListener(this);

        language_list = new ArrayList<String>();
        language_list.add("Korean");
        language_list.add("American");
        language_list.add("Spanish");
        language_list.add("French");
        language_list.add("German");
        language_list.add("Japanese");

        language_listview=(ListView)findViewById(R.id.language_listview);

        language_listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, language_list));
        language_listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
      //  Log.d("indexoflanguage",""+language_list.indexOf(initLang));
        language_listview.setItemChecked(language_list.indexOf(initLang), true);
    }
    static String LANGUAGE_RESULT="language_result";

    @Override
    public void onClick(View v) {
        if(v==choose_language)
        {
            String chosen_language=language_list.get(language_listview.getCheckedItemPosition()).toString();
          //  Log.d("chosenlanguage",chosen_language);
            Intent intent = new Intent();
            intent.putExtra(LANGUAGE_RESULT,chosen_language);
            setResult(RESULT_OK, intent); // 성공했다는 결과값을 보내면서 데이터 꾸러미를 지고 있는 intent를 함께 전달한다.
            finish();
            return;
        }
        else if(v==cancel_language)
        {
                 setResult(RESULT_CANCELED);
                finish();
                return;
        }
    }

}
