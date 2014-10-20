package ru.scorpion.osago2.json;

import android.app.Activity;
import android.util.Log;
import org.json.JSONObject;
import java.io.InputStream;

/**
 * Created by scorpion on 20.10.14.
 * Загружаем json из raw ресурсов
 */
public class JsonLoad {

    public static JSONObject load(Activity activity, Integer fileId) {
        JSONObject json = null;
        try {
            InputStream localInputStream = activity.getResources().openRawResource(fileId);
            byte[] arrayOfByte = new byte[localInputStream.available()];
            while (localInputStream.read(arrayOfByte) != -1) {}
            json = new JSONObject(new String(arrayOfByte));
        } catch (Exception e) {
            Log.e("osago", e.getMessage());
        }
        return json;
    }
}
