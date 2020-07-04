package mk.ukim.finki.emt.lab.ordermanagement.domain.model;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

public class OrderItemId extends DomainObjectId {

    private String id;

    public OrderItemId() {
        super("");
    }

    public OrderItemId(String uuid){
        super(uuid);
        this.id=uuid;
    }
}
