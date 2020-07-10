package mk.ukim.finki.emt.lab.videogamecatalog.port.rest;

import mk.ukim.finki.emt.lab.videogamecatalog.application.VideoGameCatalog;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.GameKey;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.GameKeyId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGame;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGameId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
class VideoGameCatalogController {

    private final VideoGameCatalog productCatalog;

    VideoGameCatalogController(VideoGameCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    // Please note: in a real-world application it would be better to have separate DTO classes that are serialized
    // to JSON. However, to save time, we're using the entity classes directly in this example.

    @GetMapping("/{id}")
    public ResponseEntity<VideoGame> findById(@PathVariable("id") String productId) {
        return productCatalog.findById(new VideoGameId(productId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/game-key/{gameKeyId}")
    public ResponseEntity<GameKey> findGameKeyById(@PathVariable("gameKeyId") String gameKeyId) {
        return productCatalog.findGameKeyById(new GameKeyId(gameKeyId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<VideoGame> findAll() {
        return productCatalog.findAll();
    }
}
