package mk.ukim.finki.emt.lab.videogamecatalog;

import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.*;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.CategoryRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.GameKeyRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.GenreRepository;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.repository.VideoGameRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
class DataGenerator {

    private final VideoGameRepository productRepository;
    private final GameKeyRepository gameKeyRepository;
    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;

    DataGenerator(VideoGameRepository productRepository, GenreRepository genreRepository, CategoryRepository categoryRepository, GameKeyRepository gameKeyRepository) {
        this.productRepository = productRepository;
        this.genreRepository = genreRepository;
        this.categoryRepository = categoryRepository;
        this.gameKeyRepository = gameKeyRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {

        if(genreRepository.findAll().size()==0){
            var genres = new ArrayList<Genre>();
            genres.add(new Genre(new GenreId("1"),new GenreName("Action"), "Fighting or shooter games"));
            genres.add(new Genre(new GenreId("2"),new GenreName("Open World"), "The player can go anywhere he wants to."));
            genreRepository.saveAll(genres);
        }

        if(categoryRepository.findAll().size()==0){
            var categories = new ArrayList<Category>();
            categories.add(new Category(new CategoryId("1"),"Steam", "https://store.steampowered.com/", "Steam is a video game digital distribution service by Valve."));
            categories.add(new Category(new CategoryId("2"),"UPlay", "https://uplay.ubisoft.com/", "Uplay is a digital distribution, digital rights management, multiplayer and communications service developed by Ubisoft."));
            categoryRepository.saveAll(categories);
        }


        if (productRepository.findAll().size()==0) {
            var products = new ArrayList<VideoGame>();

            VideoGame videoGame1 = createVideoGame(new VideoGameId("1"),"Far Cry 4",  new Money(Currency.EUR, 30),"Action game where the player is the main hero", "https://i.imgur.com/XFkUnkU.png", new GenreId("1"), new CategoryId("2"));
            VideoGame videoGame2 = createVideoGame(new VideoGameId("2"),"Grand Theft Auto V",  new Money(Currency.EUR, 49),"Take control over Franklin, Trevor, and Michael", "https://i.imgur.com/lw629I7.png", new GenreId("2"), new CategoryId("2"));

            GameKey gameKey1 = new GameKey(new VideoGameId("1"), new ProductKey("A23C-YT7A-ZZT1"));
            GameKey gameKey2 = new GameKey(new VideoGameId("1"), new ProductKey("TWXA-QQQQ-WESR"));

            videoGame1.addGameKey(gameKey1);
            videoGame1.addGameKey(gameKey2);

            GameKey gameKey3 = new GameKey(new VideoGameId("2"), new ProductKey("TTTT-ZZZZ-QQQQ"));
            GameKey gameKey4 = new GameKey(new VideoGameId("2"), new ProductKey("1234-ASD2-SDA2"));
            GameKey gameKey5 = new GameKey(new VideoGameId("2"), new ProductKey("YT7A-ZZT1-A23C"));

            videoGame2.addGameKey(gameKey3);
            videoGame2.addGameKey(gameKey4);
            videoGame2.addGameKey(gameKey5);


            products.add(videoGame1);
            products.add(videoGame2);

            productRepository.saveAll(products);
        }

//        if(gameKeyRepository.findAll().size()==0){
//            var products = new ArrayList<GameKey>();
//
//        }

    }

    private VideoGame createVideoGame(VideoGameId productId, String name, Money price, String desc, String imageURL, GenreId genreId, CategoryId categoryId) {
        //(VideoGameId id, String name, Money price, int quantity, String description, String imageURL) {
        //"Action game where the player is the main hero"
        var product = new VideoGame(productId,name, price, desc, imageURL, genreId, categoryId);
        return product;
    }
}
