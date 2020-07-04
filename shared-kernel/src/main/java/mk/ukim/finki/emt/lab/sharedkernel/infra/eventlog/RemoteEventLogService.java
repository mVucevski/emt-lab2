package mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
