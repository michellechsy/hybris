package com.mi.backoffice.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Step 1c: create search service
 * Created by michelle on 16-5-30.
 */
public class MiSearchService {

    public List<String> search(final String text) {
        List<String> result = new ArrayList<>();
        result.add(text);
        result.add("Hello");
        result.add("Developer");
        return result;
    }
}
