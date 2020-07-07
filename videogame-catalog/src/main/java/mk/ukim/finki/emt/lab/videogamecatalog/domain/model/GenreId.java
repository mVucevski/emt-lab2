package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class GenreId extends DomainObjectId{

    public GenreId(String id) {
        super(id);
    }

    public GenreId(){
        super(DomainObjectId.randomId(CategoryId.class).toString());
    }
}