package mk.ukim.finki.emt.lab.ordermanagement.domain.event;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGameId;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class OrderItemAdded implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty
    private final OrderItemId orderItemId;

    @JsonProperty
    private final VideoGameId productId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderItemAdded(OrderId orderId, OrderItemId orderItemId, VideoGameId productId, int quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }


}

