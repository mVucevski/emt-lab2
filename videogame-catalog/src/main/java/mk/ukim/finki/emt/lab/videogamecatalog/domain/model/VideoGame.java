package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;

import javax.naming.directory.InvalidAttributesException;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

@Entity
@Getter
@Table(name="videogames")
@Where(clause = "deleted=false")
public class VideoGame extends AbstractEntity<VideoGameId> {

    @EmbeddedId
    private VideoGameId id;

    @Version
    private Long version;

    @Column(name="name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amount")),
            @AttributeOverride(name="currency", column=@Column(name="currency"))
    })
    private Money price;

    private int quantity;

    private boolean deleted = false;

    private String description;

    private String imageURL;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "genreId", nullable = false)))
    private GenreId genreId;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "categoryId", nullable = false)))
    private CategoryId categoryId;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<GameKey> gameKeys;

    public Stream<GameKey> getAllGameKeys() {
        return gameKeys.stream();
    }

    public GameKey addGameKey(@NonNull GameKey gameKey) {
        Objects.requireNonNull(gameKey, "Game key must not be null");
        if(!gameKey.getProductKey().hasValidProductKey()){
            throw new RuntimeException("Game key must contain valid product key.");
        }
        gameKeys.add(gameKey);
        addQuantity(1);
        return gameKey;
    }

    // Returns the first game key that isn't already bought (owned)
    public GameKey findAvailableGameKey(){
        return gameKeys.stream().filter(e->!e.isOwned()).findFirst()
                .orElseThrow(() ->new RuntimeException("No available keys for the game with ID: " + id));
    }

    public VideoGame(){

    }

    public VideoGame(VideoGameId id, String name, Money price, String description, String imageURL) {
        super(id);
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.description = description;
        this.imageURL = imageURL;
        this.gameKeys = new HashSet<>();
    }

    public VideoGame(VideoGameId id, String name, Money price, String description, String imageURL, GenreId genreId, CategoryId categoryId) {
        super(id);
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.description = description;
        this.imageURL = imageURL;
        this.genreId = genreId;
        this.categoryId = categoryId;
        this.gameKeys = new HashSet<>();
    }

    @Override
    public VideoGameId id() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    @JsonProperty("available_keys")
    public long getAvailableKeys(){
        return gameKeys.stream().filter(e->!e.isOwned()).count();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void subtractQuantity(int qnt) {
        if (qnt>this.quantity) {
            throw new RuntimeException("unsupported quantity");
        }
        this.quantity -= qnt;
    }

    public void addQuantity(int qnt) {
        this.quantity +=qnt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setGenreId(GenreId genreId) {
        this.genreId = genreId;
    }

    public void setCategoryId(CategoryId categoryId) {
        this.categoryId = categoryId;
    }
}
