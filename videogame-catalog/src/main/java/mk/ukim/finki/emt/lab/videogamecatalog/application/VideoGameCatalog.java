package mk.ukim.finki.emt.lab.videogamecatalog.application;

import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGame;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGameId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.VideoGameRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.integration.OrderItemAddedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class VideoGameCatalog {

    private final VideoGameRepository VideoGameRepository;

    public VideoGameCatalog(VideoGameRepository VideoGameRepository) {
        this.VideoGameRepository = VideoGameRepository;
    }

    @NonNull
    public List<VideoGame> findAll() {
        return VideoGameRepository.findAll();
    }

    @NonNull
    public Optional<VideoGame> findById(@NonNull VideoGameId productId) {
        Objects.requireNonNull(productId, "productId must not be null");
        return VideoGameRepository.findById(productId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderCreatedEvent(OrderItemAddedEvent event) {

        System.out.println("onOrderCreatedEvent CALLED!");

        VideoGame p = VideoGameRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        p.subtractQuantity(event.getQuantity());
        VideoGameRepository.save(p);
    }
}
