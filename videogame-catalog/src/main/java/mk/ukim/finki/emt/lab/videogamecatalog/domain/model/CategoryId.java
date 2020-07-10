package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId extends DomainObjectId{

    public CategoryId(String id) {
        super(id);
    }

    public CategoryId() {
        super(DomainObjectId.randomId(CategoryId.class).toString());
    }

}
