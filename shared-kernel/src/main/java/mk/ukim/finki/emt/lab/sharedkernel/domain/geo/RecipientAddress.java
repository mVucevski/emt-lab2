package mk.ukim.finki.emt.lab.sharedkernel.domain.geo;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class RecipientAddress extends Address {

    //@Column(name = "recipient_name")
    private String name;

    private String eMail;

    @SuppressWarnings("unused") // Used by JPA only.
    protected RecipientAddress() {
    }

    public RecipientAddress(@NonNull String name, @NonNull String address,
                            @NonNull CityName city, @NonNull Country country, @NonNull String eMail) {
        super(address, city, country);
        this.name = Objects.requireNonNull(name, "name must not be null");

        Objects.requireNonNull(eMail, "E-Mail must not be null");
        if(!eMail.matches("^.+@.+\\..+$")){
            throw new RuntimeException("E-Mail must be valid e-mail address");
        }

        this.eMail = eMail;
    }

    @NonNull
    //@JsonProperty("name")
    public String name() {
        return name;
    }

    @NonNull
    //@JsonProperty("e_mail")
    public String geteMail() {
        return eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RecipientAddress that = (RecipientAddress) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

}
