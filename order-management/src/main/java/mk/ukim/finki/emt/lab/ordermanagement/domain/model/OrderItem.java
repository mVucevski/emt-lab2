package mk.ukim.finki.emt.lab.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="order_items")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "videoGameId", nullable = false)))
    private VideoGameId videoGameId;

    @Embedded
    private Money itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    protected OrderItem(){}

    public OrderItem(@NonNull VideoGameId videoGameId, @NonNull Money itemPrice, @NonNull int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.videoGameId = videoGameId;
        this.itemPrice = itemPrice;
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }else if(quantity > 1){
            throw new IllegalArgumentException("Max quantity of the same video game in one order is 1.");
        }

        this.quantity = quantity;
    }

    public void setVideoGameId(VideoGameId videoGameId) {
        this.videoGameId = videoGameId;
    }

    public void setItemPrice(Money itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }else if(quantity > 1){
            throw new IllegalArgumentException("Max order quantity of the same video game is 1.");
        }

        this.quantity = quantity;
    }

    @NonNull
    @JsonProperty("qty")
    public int quantity() {
        return quantity;
    }

    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }


}
