package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Getter
@Table(name="genres")
public class Genre extends AbstractEntity<GenreId> {
    @EmbeddedId
    private GenreId id;

    @Version
    private Long version;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    public Genre() {
    }

    public Genre(GenreId id, String name, String description) {
        super(id);
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
