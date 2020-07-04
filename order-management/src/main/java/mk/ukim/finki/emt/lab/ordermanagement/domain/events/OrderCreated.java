package mk.ukim.finki.emt.lab.ordermanagement.domain.events;

import lombok.Getter;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class OrderCreated implements DomainEvent {

    private final OrderId orderId;

    private final Instant occurredOn;

    public OrderCreated(OrderId orderId, Instant occurredOn) {
        this.orderId = orderId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
