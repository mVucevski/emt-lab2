package mk.ukim.finki.emt.lab.videogamecatalog.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog.RemoteEventTranslator;
import mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemAddedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public OrderItemAddedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent storedDomainEvent) {
        return storedDomainEvent.domainEventClassName().equals("mk.ukim.finki.emt.lab.ordermanagement.domain.event.OrderItemAdded");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, OrderItemAddedEvent.class));
    }
}
