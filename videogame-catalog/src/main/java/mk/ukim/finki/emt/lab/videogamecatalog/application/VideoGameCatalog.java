package mk.ukim.finki.emt.lab.videogamecatalog.application;

import mk.ukim.finki.emt.lab.videogamecatalog.domain.event.GameKeyAddedToOrder;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.*;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.GameKeyRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.VideoGameRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.integration.OrderItemAddedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class VideoGameCatalog {

    private final VideoGameRepository VideoGameRepository;
    private final GameKeyRepository gameKeyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public VideoGameCatalog(VideoGameRepository VideoGameRepository, GameKeyRepository gameKeyRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.VideoGameRepository = VideoGameRepository;
        this.gameKeyRepository = gameKeyRepository;
        this.applicationEventPublisher = applicationEventPublisher;
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

    @NonNull
    public Optional<GameKey> findGameKeyById(@NonNull GameKeyId gameKeyId) {
        Objects.requireNonNull(gameKeyId, "gameKeyId must not be null");
        return gameKeyRepository.findById(gameKeyId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderCreatedEvent(OrderItemAddedEvent event) {

        System.out.println("onOrderCreatedEvent CALLED!");

//        VideoGame videoGame = VideoGameRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
//        videoGame.subtractQuantity(event.getQuantity());

        // ------------
        VideoGame videoGame = VideoGameRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        GameKey gameKey = videoGame.findAvailableGameKey();
        gameKey.setOwned(true);
        videoGame.subtractQuantity(1);
        VideoGame videoGameSAVED = VideoGameRepository.saveAndFlush(videoGame);
        onGameKeyAdded(event.getOrderId(), event.getOrderItemId(), event.getProductId(), gameKey.getId());
    }

    @Transactional(propagation = Propagation.NESTED)
    public void onGameKeyAdded(OrderId orderId, OrderItemId orderItemId, VideoGameId videoGameId, GameKeyId gameKeyId){
        applicationEventPublisher.publishEvent(new GameKeyAddedToOrder(orderId,orderItemId,videoGameId,gameKeyId,Instant.now()));
        System.out.println("onOrderCreatedEvent - PUBLISHED NEW EVENT");
    }

}
