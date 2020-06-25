package com.codflix.backend.features.media;

import com.codflix.backend.core.Template;
import com.codflix.backend.models.Media;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaController {
    private final MediaDao mediaDao = new MediaDao();

    public String list(Request request, Response response) {
        List<Media> medias;

        String title = request.queryParams("title");
        System.out.println("title = " + title);
        String type = request.queryParams("type");
        System.out.println("type = " + type);
        String genre = request.queryParams("genre");
        System.out.println("genre = " + genre);
        String year = request.queryParams("releaseDate");
        System.out.println("date = " + year);

        if(genre == null && type == null && year == null && title == null){
            System.out.println("tt est null");
            medias = mediaDao.getAllMedias();
        } else {
            System.out.println("on passe à la suite");
            medias = mediaDao.filterMedias(title, type, genre, year);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("medias", medias);
        return Template.render("media_list.html", model);
    }

    public String detail(Request request, Response res) {
        int id = Integer.parseInt(request.params(":id"));
        Media media = mediaDao.getMediaById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("media", media);
        return Template.render("media_detail.html", model);
    }
}
