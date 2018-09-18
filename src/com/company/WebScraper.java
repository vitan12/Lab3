package com.company;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.sort;

public class WebScraper {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */

    public static String urlToString(final String url) {
            Scanner urlScanner;
            try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
            } catch (IOException e) {
            return "";
            }
            String contents = urlScanner.useDelimiter("\\A").next();
            urlScanner.close();
            return contents;
            }

    public static void main (String[] args) {
        System.out.println("Please input the URL you wish to scan");
        Scanner thing = new Scanner(System.in);
        String URL = thing.nextLine();
        String search = "";
        System.out.println("Please input the search query");
        while (search == "") {
            search = thing.nextLine();
            if (search == "")
                System.out.println("Please enter a search query");
        }
        String website = urlToString(URL);
        String[] words = website.split("\\W+");
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(search)) {
                count ++;
            }
        }
        if (count == 0) {
            System.out.println("The search term does not appear in the selected website");
        }
        else
            System.out.println("The search term appears "+count+" time(s)");

        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(words));
        sort(arrayList);
        ArrayList<String> unique = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (arrayList.get(j).equalsIgnoreCase(words[i]) && !unique.contains(words[i])) {
                    unique.add(words[i]);
                }
            }
        }
        int countUniqueTerms = unique.size();
        System.out.println("This website has " +countUniqueTerms+ " unique terms.");
    }
}
