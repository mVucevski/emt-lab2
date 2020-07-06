package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

@Getter
@EqualsAndHashCode
public class OrderId extends DomainObjectId {

    protected OrderId() {
        super("");
    }

    public OrderId(String id) {
        super(id);
    }
}
