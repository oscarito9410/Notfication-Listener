package com.oscar.notficacionlistener.Utils.linkPreview;

/**
 * Created by oemy9 on 01/10/2017.
 */

import java.net.URL;
import java.util.ArrayList;

public class SearchUrls {

    public static final int ALL = 0;
    public static final int FIRST = 1;

    /** It finds urls inside the text and return the matched ones */
    public static ArrayList<String> matches(String text) {
        return matches(text, ALL);
    }

    /** It finds urls inside the text and return the matched ones */
    public static ArrayList<String> matches(String text, int results) {

        ArrayList<String> urls = new ArrayList<>();

        String[] splitString = (text.split(" "));
        for (String string : splitString) {

            try {
                URL item = new URL(string);
                urls.add(item.toString());
            } catch (Exception e) {
            }

            if (results == FIRST && urls.size() > 0)
                break;
        }

        return urls;
    }

}
