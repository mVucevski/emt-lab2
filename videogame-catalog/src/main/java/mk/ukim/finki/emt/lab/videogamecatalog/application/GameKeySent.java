package mk.ukim.finki.emt.lab.videogamecatalog.application;

import mk.ukim.finki.emt.lab.videogamecatalog.domain.event.GameKeyAddedToOrder;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.GameKeyId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.OrderId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.OrderItemId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGameId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.GameKeyRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class GameKeySent {

    private  ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public GameKeySent(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onGameKeyAdded(OrderId orderId, OrderItemId orderItemId, VideoGameId videoGameId, GameKeyId gameKeyId) {

        System.out.println("onOrderCreatedEvent - PUBLISHED NEW EVENT");
        applicationEventPublisher.publishEvent(new GameKeyAddedToOrder(orderId,orderItemId,videoGameId,gameKeyId,Instant.now()));

    }
}
