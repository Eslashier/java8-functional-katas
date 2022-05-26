package katas;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Combine videos and bookmarks by index (StreamUtils.zip) (https://github.com/poetix/protonpack)
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("videoId", "5", "bookmarkId", "3")
*/
public class Kata8 {
    public static List<String> execute() {
        List<Movie> movies = DataUtil.getMovies();
        List<Bookmark> bookMarks = DataUtil.getBookMarks();


        var movie = movies.stream().map(
                movie1 -> Map.of("videoId",movie1.getId()));
        var bookMark = bookMarks.stream().map(
                bookmark1 -> Map.of("videoId",bookmark1.getId()));
        // StreamUtils.zip()
        var zip = StreamUtils.zip(movie,
                        bookMark,
                        (a, b) -> a +""+ b)
                        .collect(Collectors.toList());

        return zip;
    }
}
