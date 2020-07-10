package mk.ukim.finki.emt.lab.videogamecatalog.application;

import mk.ukim.finki.emt.lab.videogamecatalog.domain.event.GameKeyAddedToOrder;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.*;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.GameKeyRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.VideoGameRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.integration.OrderItemAddedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
    private final GameKeySent gameKeySent;

    public VideoGameCatalog(VideoGameRepository VideoGameRepository, GameKeyRepository gameKeyRepository, ApplicationEventPublisher applicationEventPublisher, GameKeySent gameKeySent) {
        this.VideoGameRepository = VideoGameRepository;
        this.gameKeyRepository = gameKeyRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.gameKeySent = gameKeySent;
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
    //@EventListener
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
        gameKeySent.onGameKeyAdded(event.getOrderId(), event.getOrderItemId(), event.getProductId(), gameKey.getId());
        //return new GameKeyAddedToOrder(event.getOrderId(), event.getOrderItemId(), event.getProductId(), gameKey.getId(),Instant.now());
        //onGameKeyAdded(event.getOrderId(), event.getOrderItemId(), event.getProductId(), gameKey.getId());
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onGameKeyAdded(OrderId orderId, OrderItemId orderItemId, VideoGameId videoGameId, GameKeyId gameKeyId){

//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        applicationEventPublisher.publishEvent(new GameKeyAddedToOrder(orderId,orderItemId,videoGameId,gameKeyId,Instant.now()));
//                        System.out.println("onOrderCreatedEvent - PUBLISHED NEW EVENT");
//                    }
//                },
//                5000
//        );

        applicationEventPublisher.publishEvent(new GameKeyAddedToOrder(orderId,orderItemId,videoGameId,gameKeyId,Instant.now()));
        System.out.println("onOrderCreatedEvent - PUBLISHED NEW EVENT");
    }

//    @TransactionalEventListener(fallbackExecution = true,phase = TransactionPhase.AFTER_COMMIT)
//    public void testEvent(OrderItemAddedEvent event){
//        //applicationEventPublisher.publishEvent(new GameKeyAddedToOrder(event.getOrderId(), event.getOrderItemId(), event.getProductId(), ,Instant.now()));
//
//        System.out.println("testEvent CALLED");
//        VideoGame videoGame = VideoGameRepository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
//        GameKey gameKey = videoGame.findAvailableGameKey();
//        gameKey.setOwned(true);
//        VideoGame videoGameSAVED = VideoGameRepository.saveAndFlush(videoGame);
//        onGameKeyAdded(event.getOrderId(), event.getOrderItemId(), event.getProductId(), gameKey.getId());
//
//        //VideoGame videoGameSAVED = VideoGameRepository.saveAndFlush(videoGame);
//    }

}
