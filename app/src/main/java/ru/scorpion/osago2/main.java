package ru.scorpion.osago2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import ru.scorpion.osago2.json.JsonLoad;
import ru.scorpion.osago2.json.JsonSpinnerLoader;


public class main extends ActionBarActivity {

    private Spinner owner; // Физическое или юр. лицо
    private CheckBox access; // Без ограничений к допуску управления
    private Spinner type_ts; // тип ТС
    private CheckBox forreg; // Страхование на срок следования к месту регистрации
    private Spinner region; // Регион
    private Spinner city; // Город
    private Spinner power; // Мощность двигателя
    private Spinner malus; // Бонусный/малусный класс
    private Spinner longevity; // Минимальный возраст и водительский стаж лиц, допущенных к управлению ТС
    private Spinner deadline; // Срок договора
    private CheckBox foreign; // ТС зарегистрировано в иностранном государстве
    private CheckBox foul; // Имеются грубые нарушения условий страхования

    private JSONObject tarif; // Города и тарифы

    private double kbm = 1; // КБМ
    private double km = 1; // КМ
    private double kn = 1; // КН
    private double ko = 1; // КН
    private double kp = 1; // КП
    private double ks = 1; // КС
    private double kt = 1; // КТ
    private double kvs = 1; // КВС



    private void initParam() {
        owner = (Spinner) findViewById(R.id.owner);
        access = (CheckBox) findViewById(R.id.access);
        type_ts = (Spinner) findViewById(R.id.type_ts);
        forreg = (CheckBox) findViewById(R.id.forreg);
        region = (Spinner) findViewById(R.id.region);
        city = (Spinner) findViewById(R.id.city);
        power = (Spinner) findViewById(R.id.power);
        malus = (Spinner) findViewById(R.id.malus);
        longevity = (Spinner) findViewById(R.id.longevity);
        deadline = (Spinner) findViewById(R.id.deadline);
        foreign = (CheckBox) findViewById(R.id.foreign);
        foul = (CheckBox) findViewById(R.id.foul);


    }

    private void loadDataFromJson() throws JSONException {
        JsonSpinnerLoader jsonSpinnerLoader = new JsonSpinnerLoader(this);
        type_ts.setAdapter(jsonSpinnerLoader.getItemsByKey("type_ts"));
        owner.setAdapter(jsonSpinnerLoader.getItemsByKey("owner"));
        region.setAdapter(jsonSpinnerLoader.getItemsByKey("region"));
        city.setAdapter(jsonSpinnerLoader.getItemsByKey("city"));
        power.setAdapter(jsonSpinnerLoader.getItemsByKey("power"));
        malus.setAdapter(jsonSpinnerLoader.getItemsByKey("malus"));
        longevity.setAdapter(jsonSpinnerLoader.getItemsByKey("longevity"));
        deadline.setAdapter(jsonSpinnerLoader.getItemsByKey("deadline"));


        malus.setSelection(4);
        region.setSelection(29);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initParam();
        try {
            loadDataFromJson();
            tarif = JsonLoad.load(this, R.raw.tarif);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("osago", e.getMessage());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
