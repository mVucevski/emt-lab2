package mk.ukim.finki.emt.lab.ordermanagement.domain.model;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {

    public OrderId() {
        super(DomainObjectId.randomId(OrderId.class).toString());
    }

    public OrderId(String id) {
        super(id);
    }
}
