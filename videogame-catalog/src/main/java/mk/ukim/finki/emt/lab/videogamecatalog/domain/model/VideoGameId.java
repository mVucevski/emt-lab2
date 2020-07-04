package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class VideoGameId extends DomainObjectId {

    public VideoGameId() {
        super(DomainObjectId.randomId(VideoGameId.class).toString());
    }

    public VideoGameId(String id) {
        super(id);
    }


}
