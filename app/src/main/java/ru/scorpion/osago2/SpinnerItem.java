package ru.scorpion.osago2;

/**
 * Created by scorpion on 17.10.14.
 * Объект элемента spinner
 */
public class SpinnerItem {

    public String key;
    public String value;
    public String custom;

    public SpinnerItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return this.value;
    }


}
