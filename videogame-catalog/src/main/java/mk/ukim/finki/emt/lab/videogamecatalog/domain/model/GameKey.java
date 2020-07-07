package mk.ukim.finki.emt.lab.videogamecatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.lab.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="game_keys")
public class GameKey extends AbstractEntity<GameKeyId> {

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "videoGameId", nullable = false)))
    private VideoGameId videoGameId;

    @Version
    private Long version;

    @Column(name="product_key", nullable = false)
    private String productKey;

    @Column(name="owned")
    private boolean owned;

    protected GameKey() {
    }

    public GameKey(@NonNull VideoGameId videoGameId, @NonNull String productKey) {
        super(DomainObjectId.randomId(GameKeyId.class));
        this.videoGameId = videoGameId;
        this.productKey = productKey;
        owned = false;
    }

    public VideoGameId getVideoGameId() {
        return videoGameId;
    }

    public void setVideoGameId(VideoGameId videoGameId) {
        this.videoGameId = videoGameId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }
}
