package mk.ukim.finki.emt.lab.videogamecatalog.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog.RemoteEventTranslator;
import mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class OrderCreatedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    OrderCreatedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals("mk.ukim.finki.emt.lab.videogamecatalog.integration.OrderCreatedEvent");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, OrderCreatedEvent.class));
    }
}
