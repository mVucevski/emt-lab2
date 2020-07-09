package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import mk.ukim.finki.emt.lab.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProductKey implements ValueObject {

    @Column(name = "product_key")
    private String productKey;

    public ProductKey() {
    }

    public ProductKey(@NonNull String productKey){
        validateProductKey(productKey);

        this.productKey = productKey;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        validateProductKey(productKey);

        this.productKey = productKey;
    }

    private void validateProductKey(String productKey){
        Objects.requireNonNull(productKey, "Product key must not be null");

        //Allowed format: XXXX-XXXX-XXXX
        boolean isCorrectFormat = productKey.matches("^(\\w|\\d){4}-(\\w|\\d){4}-(\\w|\\d){4}$");

        if(!isCorrectFormat){
            throw new RuntimeException("Product key must be in correct format. Ex: XXXX-XXXX-XXXX");
        }

    }

    public boolean hasValidProductKey(){
        return !productKey.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductKey)) return false;
        ProductKey that = (ProductKey) o;
        return productKey.equals(that.productKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productKey);
    }

    @Override
    public String toString() {
        return productKey;
    }
}
