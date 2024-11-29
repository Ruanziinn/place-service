package br.com.ruanziin.placeservice.web;

import br.com.ruanziin.placeservice.api.PlaceResponse;
import br.com.ruanziin.placeservice.domain.Place;

public class PlaceMapper {
    public static PlaceResponse fromPlaceToResponse(Place place) {
        return new PlaceResponse(place.name(), place.slug(),
                place.state(), place.createdAt(), place.updatedAt());
    }
}
