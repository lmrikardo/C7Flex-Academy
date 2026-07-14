package br.com.c7flex.academia.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeUtils {

    private static final Pattern PATTERN = Pattern.compile(
            "(?:youtu\\.be/|youtube\\.com(?:/embed/|/v/|.*v=))([^?&\"'>]+)"
    );

    public static String extrairVideoId(String url) {

        Matcher matcher = PATTERN.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("URL do YouTube inválida.");
    }

    public static String thumbnail(String id) {
        return "https://img.youtube.com/vi/" + id + "/hqdefault.jpg";
    }
}