package mk.ukim.finki.emt.lab.sharedkernel.domain.base;

import mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
