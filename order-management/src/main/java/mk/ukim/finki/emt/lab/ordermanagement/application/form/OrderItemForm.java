package mk.ukim.finki.emt.lab.ordermanagement.application.form;

import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGame;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OrderItemForm implements Serializable {

    @NotNull
    private VideoGame product;

    @Min(1)
    private int quantity = 1;

    public VideoGame getProduct() {
        return product;
    }

    public void setProduct(VideoGame product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
