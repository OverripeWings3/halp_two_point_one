package com.iteso.is699367.halp_3.Constants;

import java.util.ArrayList;

/**
 * Created by sjacobus on 24/04/18.
 */

public class Constants {
    public static final String KEY_ENG_LANG = "button_change_eng";
    public static final String KEY_SPAN_LANG = "button_change_span";
    public static final String KEY_CHANGE_COLOR_NORM = "button_change_color_norm";
    public static final String KEY_CHANGE_COLOR_INVR = "button_change_color_inv";
    public static final String KEY_CHANGE_COLOR = "AppTheme";
    public static final String KEY_LOG_OUT = "button_logout";
    public static final String USER_PREFERENCES = "com.iteso.is699367.halp_two_point_one";

    public static ArrayList<String> getConstants() {

        ArrayList<String> arrString = new ArrayList<String>();
        arrString.add(KEY_CHANGE_COLOR_NORM);
        arrString.add(KEY_CHANGE_COLOR_INVR);
        arrString.add(KEY_LOG_OUT);
        return arrString;
    }

}
