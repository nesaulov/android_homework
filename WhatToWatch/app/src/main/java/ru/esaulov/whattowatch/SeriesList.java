package ru.esaulov.whattowatch;

/**
 * Created by nesaulov on 22/01/2018.
 */

import java.util.ArrayList;
import java.util.List;

public class SeriesList {
    List<String> getGenre (String spinner) {
        List<String> genre = new ArrayList<String>();
        if(spinner.equals("комедия")) {
            genre.add("Офис");
            genre.add("Друзья");
        } if (spinner.equals("фэнтези")){
            genre.add("Игра престолов");
        } if (spinner.equals("ужасы")){
            genre.add("Ходячие мертвецы");
            genre.add("Ужасы 2");
        } if (spinner.equals("фантастика")){
            genre.add("Футурама");
            genre.add("Фантастика 2");
        } if (spinner.equals("криминал")){
            genre.add("Во все тяжкие");
        }
        return genre;
    }
}
