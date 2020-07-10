package mk.ukim.finki.emt.lab.ordermanagement.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.GameKeyId;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGameId;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class GameKeyAddedToOrderEvent implements DomainEvent {
    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty
    private final OrderItemId orderItemId;

    @JsonProperty
    private final VideoGameId productId;

    @JsonProperty
    private final GameKeyId gameKeyId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public GameKeyAddedToOrderEvent(OrderId orderId, OrderItemId orderItemId, VideoGameId productId, GameKeyId gameKeyId, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.occurredOn = occurredOn;
        this.gameKeyId = gameKeyId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}