package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(c -> c.getVideos().stream())
                .map((movie -> Map.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "boxart", movie.getBoxarts()
                                .stream()
                                .filter(boxArt -> boxArt.getWidth()==150)
                                .map((boxArt -> Map.of(
                                        "width",boxArt.getWidth(),
                                        "height", boxArt.getHeight(),
                                        "url",boxArt.getUrl()
                                )))
                                .collect(Collectors.toList())
                )))
                .collect(toUnmodifiableList());
    }
}
