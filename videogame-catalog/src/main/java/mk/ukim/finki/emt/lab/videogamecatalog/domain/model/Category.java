package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.AbstractEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Table(name="categories")
public class Category extends AbstractEntity<CategoryId>{

    @EmbeddedId
    private CategoryId id;

    @Version
    private Long version;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="platform_url")
    private String platformURL;

    @Column(name="description")
    private String description;

    public Category() {
    }

    public Category(CategoryId id, String name, String platformURL, String description) {
        super(id);
        this.id = id;
        this.name = name;
        this.platformURL = platformURL;
        this.description = description;
    }

    @Override
    public CategoryId id() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlatformURL(String platformURL) {
        this.platformURL = platformURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
