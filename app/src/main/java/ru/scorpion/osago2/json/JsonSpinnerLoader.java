package ru.scorpion.osago2.json;

import android.app.Activity;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ru.scorpion.osago2.R;
import ru.scorpion.osago2.SpinnerItem;

/**
 * Created by scorpion on 17.10.14.
 * Загружаем данные для spinner из raw ресурсов
 */
public class JsonSpinnerLoader {

    private JSONObject json;
    private Activity activity;

    public JsonSpinnerLoader(Activity activity) {
        this.activity = activity;
        json = JsonLoad.load(activity, R.raw.spinners_data);
    }

    public ArrayAdapter<SpinnerItem> getItemsByKey(String key) throws JSONException {
        ArrayAdapter<SpinnerItem> adapter = new ArrayAdapter<SpinnerItem>(activity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (!json.has(key)) {
            return adapter;
        }
        JSONArray data = json.getJSONArray(key);
        for (int i = 0; i < data.length(); i++) {
            JSONObject tmp = data.getJSONObject(i);
            SpinnerItem item = new SpinnerItem(tmp.getString("key"), tmp.getString("value"));
            if (tmp.has("custom")) {
                item.custom = tmp.getString("custom");
            }
            adapter.add(item);
        }
        return adapter;
    }
}
