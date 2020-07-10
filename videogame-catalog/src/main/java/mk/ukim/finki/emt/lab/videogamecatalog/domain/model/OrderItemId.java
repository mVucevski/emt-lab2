package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

public class OrderItemId extends DomainObjectId {

    private String id;

    public OrderItemId() {
        super("");
    }

    public OrderItemId(String uuid){
        super(uuid);
        this.id=uuid;
    }

    public String getId(){
        return id;
    }
}
