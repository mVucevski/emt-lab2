package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Currency;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GenreName implements ValueObject {

    @Column(name = "name")
    private final String name;

    protected GenreName() {
        name = "";
    }

    public GenreName(@NonNull String name) {
        if (name.length() <= 3  || name.length() >= 100) {
            throw new RuntimeException("Unsupported length of the genre name. " +
                    "The length must larger than 3 and smaller than 100.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
