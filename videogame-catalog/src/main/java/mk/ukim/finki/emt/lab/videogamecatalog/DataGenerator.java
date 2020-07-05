package mk.ukim.finki.emt.lab.videogamecatalog;

import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGame;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGameId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.VideoGameRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
class DataGenerator {

    private final VideoGameRepository productRepository;

    DataGenerator(VideoGameRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        if (productRepository.findAll().size()==0) {
            var products = new ArrayList<VideoGame>();
            products.add(createProduct(new VideoGameId("1"),"Flashlight L",  new Money(Currency.MKD, 5642),10));
            products.add(createProduct(new VideoGameId("2"), "Flashlight M",  new Money(Currency.MKD, 4029),10));
            products.add(createProduct(new VideoGameId("3"),"Flashlight S",  new Money(Currency.MKD, 2416),10));
            productRepository.saveAll(products);
        }

    }

    private VideoGame createProduct(VideoGameId productId, String name, Money price, int qnt) {
        var product = new VideoGame(productId,name, price, qnt );
        return product;
    }
}
