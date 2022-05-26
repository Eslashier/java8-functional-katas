package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(c -> c.getVideos().stream())
                .map(video -> Map.of(
                        "id", video.getId(),
                        "title", video.getTitle(),
                        "time", video.getInterestingMoments()
                                .stream()
                                .filter(a -> a.getTime().equals("Middle"))
                                .collect(Collectors.toList()),
                        "url" , video.getBoxarts()
                                .stream()
                                .reduce((a, b)-> a.getWidth() * b.getHeight() > b.getWidth() * b.getHeight() ? b : a)
                                .map(BoxArt::getUrl)
                ))
                .collect(Collectors.toList());

    }
}
