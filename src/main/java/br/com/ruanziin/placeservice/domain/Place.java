package br.com.ruanziin.placeservice.domain;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public record Place (
        @Id Long id, @NotBlank String name, @NotBlank String slug, String state,
        @NotBlank String city,
        @CreatedDate LocalDateTime createdAt,
        @LastModifiedDate LocalDateTime updatedAt) {

    public Place withSlug(String slug) {
        return new Place(id, name, slug, state, city, createdAt, updatedAt);
    }
}
