package mk.ukim.finki.emt.lab.videogamecatalog.port.rest;

import mk.ukim.finki.emt.lab.videogamecatalog.application.VideoGameCatalog;
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
class ProductCatalogController {

    private final VideoGameCatalog productCatalog;

    ProductCatalogController(VideoGameCatalog productCatalog) {
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

    @GetMapping
    public List<VideoGame> findAll() {
        return productCatalog.findAll();
    }
}
