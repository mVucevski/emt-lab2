package mk.ukim.finki.emt.lab.ordermanagement.domain.model;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class GameKeyId extends DomainObjectId {

    public GameKeyId() {
        super(DomainObjectId.randomId(GameKeyId.class).toString());
    }

    public GameKeyId(String id) {
        super(id);
    }
}

