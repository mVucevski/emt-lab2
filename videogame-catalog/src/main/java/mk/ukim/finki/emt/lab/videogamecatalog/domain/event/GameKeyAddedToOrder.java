package mk.ukim.finki.emt.lab.videogamecatalog.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.GameKeyId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.OrderId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.OrderItemId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGameId;
import org.springframework.lang.NonNull;

import java.time.Instant;

@Getter
public class GameKeyAddedToOrder implements DomainEvent {
    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("orderItemId")
    private final OrderItemId orderItemId;

    @JsonProperty("productId")
    private final VideoGameId productId;

    @JsonProperty("gameKeyId")
    private final GameKeyId gameKeyId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public GameKeyAddedToOrder(@JsonProperty("orderId") @NonNull OrderId orderId, @JsonProperty("orderItemId") @NonNull OrderItemId orderItemId,
                               @JsonProperty("productId") @NonNull VideoGameId productId, @JsonProperty("gameKeyId") @NonNull GameKeyId gameKeyId, @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.occurredOn = occurredOn;
        this.gameKeyId = gameKeyId;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }
}
