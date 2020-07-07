package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
    //Will be included in the final version
    //private String description;
    //private String imageURL;

    public VideoGame(){

    }

    public VideoGame(VideoGameId id, String name, Money price, int quantity) {
        super(id);
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public VideoGame(String name, Money price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
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

}
