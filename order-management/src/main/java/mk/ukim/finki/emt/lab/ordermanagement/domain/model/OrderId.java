package mk.ukim.finki.emt.lab.ordermanagement.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class OrderId extends DomainObjectId {

    public OrderId() {
        super(DomainObjectId.randomId(OrderId.class).toString());
    }

    public OrderId(String id) {
        super(id);
    }
//    protected OrderId() {
//        super("");
//    }
//
//    public OrderId(@NonNull String id) {
//        super(id);
//    }

}
