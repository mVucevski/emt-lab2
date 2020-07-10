package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.AbstractEntity;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Getter
@Table(name="genres")
public class Genre extends AbstractEntity<GenreId> {
    @EmbeddedId
    private GenreId id;

    @Version
    private Long version;

    @Embedded
    private GenreName name;

    @Column(name="description")
    private String description;

    public Genre() {
    }

    public Genre(@NonNull GenreId id, @NonNull GenreName genreName, @NonNull String description) {
        super(id);
        this.id = id;
        this.name = genreName;
        this.description = description;
    }

    public void setName(@NonNull String name) {
        this.name = new GenreName(name);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(GenreId id) {
        this.id = id;
    }
}
