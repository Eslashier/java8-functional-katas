package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();
//
//        return movies.stream()
//                .flatMap(c ->c.getBoxarts().stream())
//                .reduce((a, b)-> a.getWidth() * b.getHeight() > b.getWidth() * b.getHeight() ? a : b)
//                .map(BoxArt::getUrl)
//                .toString();

        return movies.stream()
                .flatMap(c ->c.getBoxarts().stream())
                .reduce((a, b)-> a.getWidth() * b.getHeight() > b.getWidth() * b.getHeight() ? a : b)
                .map(BoxArt::getUrl)
                .toString();
    }
}
