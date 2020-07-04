package mk.ukim.finki.emt.lab.sharedkernel.domain.base;

import org.springframework.lang.NonNull;

import java.time.Instant;

public interface DomainEvent extends DomainObject{

    @NonNull
    Instant occurredOn();
}
