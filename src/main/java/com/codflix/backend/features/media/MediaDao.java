package com.codflix.backend.features.media;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.Media;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MediaDao {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public List<Media> getAllMedias() {
        List<Media> medias = new ArrayList<>();

        Connection connection = Database.get().getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM media ORDER BY type ASC , release_date DESC");
            while (rs.next()) {
                medias.add(mapToMedia(rs));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return medias;
    }

    public List<Media> filterMedias(String title, String type, String genre, String year) {
        List<Media> medias = new ArrayList<>();

        Connection connection = Database.get().getConnection();
        if (genre == null){
            genre = "";
        }
        if (type == null){
            type = "";
        }
        try {
            String query;

            if (year == null ){
                System.out.println("pas d'année");
                query = "SELECT * FROM media WHERE title LIKE '%" + title + "%' AND type LIKE '%" + type + "%' AND genre_id LIKE '%" + genre + "%' ORDER BY release_date DESC";
                System.out.println("query = " + query);
            }
            else {
                System.out.println("alors?");
                query = "SELECT * FROM media WHERE title LIKE '%" + title + "%' AND type LIKE '%" + type + "%' AND genre_id LIKE '%" + genre + "%' AND release_date " + year + " ORDER BY release_date DESC";
                System.out.println(query);

            }

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                medias.add(mapToMedia(rs));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return medias;
    }

    public Media getMediaById(int id) {
        Media media = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM media WHERE id=?");

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                media = mapToMedia(rs);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return media;
    }

    private Media mapToMedia(ResultSet rs) throws SQLException, ParseException {
        return new Media(
                rs.getInt(1), // id
                rs.getInt(2), // genre_id
                rs.getString(3), // title
                rs.getString(4), // type
                rs.getString(5), // status
                DATE_FORMAT.parse(rs.getString(6)), // release_date
                rs.getString(7), // summary
                rs.getString(8), // trailer_url
                rs.getString(9) // poster_url
        );
    }
}
