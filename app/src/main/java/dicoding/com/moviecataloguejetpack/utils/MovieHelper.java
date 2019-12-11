package dicoding.com.moviecataloguejetpack.utils;

import android.content.Context;

import java.util.List;

import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.Genre;

public class MovieHelper {

    public static String convertDuration2String(Context c, int duration) {
        String resultDuration = "";
        resultDuration = (duration >= 60 ? duration / 60 + " " + c.getString(R.string.hour) + " " : "") +
                (duration % 60 > 0 ? duration % 60 + " " + c.getString(R.string.min) : "");
        return resultDuration;
    }

    public static String List2String(List<Genre> data) {
        String result = "";
        for (int i = 0; i < data.size(); i++) {
            result += data.get(i).getGenre();
            if (i < data.size() - 1) {
                result += " | ";
            }
        }
        return result;
    }
}
