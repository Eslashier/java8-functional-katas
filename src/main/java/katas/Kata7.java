package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(c -> c.getVideos().stream())
                .map((movie -> Map.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "boxart", movie.getBoxarts()
                                .stream()
                                .reduce((a, b)-> a.getWidth() * b.getHeight() > b.getWidth() * b.getHeight() ? b : a)
                                .map(BoxArt::getUrl)
                )))
                .collect(toUnmodifiableList());

//        return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", "url"));
    }
}
