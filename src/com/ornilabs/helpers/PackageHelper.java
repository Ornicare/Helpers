package com.ornilabs.helpers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageHelper {
    public static String[] performRegex(String inputString, String regex, boolean multiLine)
    {
        ArrayList<String> outputResults = new ArrayList<String>();

        Pattern pattern = multiLine ? Pattern.compile(regex, Pattern.DOTALL) : Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        while ( matcher.find() ) { outputResults.add(inputString.substring(matcher.start(), matcher.end())); }

        return outputResults.toArray(new String[outputResults.size()]);
    }
}
