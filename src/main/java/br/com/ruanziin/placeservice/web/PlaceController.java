package br.com.ruanziin.placeservice.web;


import br.com.ruanziin.placeservice.api.PlaceRequest;
import br.com.ruanziin.placeservice.api.PlaceResponse;
import br.com.ruanziin.placeservice.domain.PlaceMapper;
import br.com.ruanziin.placeservice.domain.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request) {
        var placeResponse = placeService.create(request).map(PlaceMapper::toResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
    }

    @PatchMapping("{id}")
    public Mono<PlaceResponse> edit(@PathVariable("id") Long id, @RequestBody PlaceRequest request) {
        return placeService.edit(id, request).map(PlaceMapper::toResponse);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<PlaceResponse>> get(@PathVariable("id") Long id) {
        return placeService.get(id)
                .map(place -> ResponseEntity.ok(PlaceMapper.toResponse(place)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<PlaceResponse> list(@RequestParam(required = false) String name) {
        return placeService.list(name).map(PlaceMapper::toResponse);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return placeService.delete(id);
    }

}
