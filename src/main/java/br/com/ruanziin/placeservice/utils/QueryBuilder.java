package br.com.ruanziin.placeservice.utils;

import br.com.ruanziin.placeservice.domain.Place;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder {
    private QueryBuilder() {
    }

    public static Example<Place> makeQuery(Place planet) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
        return Example.of(planet, exampleMatcher);
    }
}
