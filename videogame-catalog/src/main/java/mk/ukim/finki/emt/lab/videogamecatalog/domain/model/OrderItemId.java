package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId extends DomainObjectId {

    private String id;

    protected OrderItemId() {
        super(DomainObjectId.randomId(OrderItemId.class).toString());
    }

    @JsonCreator
    public OrderItemId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
