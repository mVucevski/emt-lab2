package mk.ukim.finki.emt.lab.sharedkernel.domain.base;

import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

@Getter
public class DomainObjectId implements ValueObject{

    private String id;

    public DomainObjectId(String id) {
        this.id = id;
    }

    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }

}
