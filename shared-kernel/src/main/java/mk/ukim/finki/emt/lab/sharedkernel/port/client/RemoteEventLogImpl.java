package mk.ukim.finki.emt.lab.sharedkernel.port.client;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.RemoteEventLog;
import mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

class RemoteEventLogImpl implements RemoteEventLog {

    private final List<StoredDomainEvent> events;

    public RemoteEventLogImpl(List<StoredDomainEvent> events) {
        this.events = events;
    }

    @Override
    public List<StoredDomainEvent> events() {
        return events;
    }
}
