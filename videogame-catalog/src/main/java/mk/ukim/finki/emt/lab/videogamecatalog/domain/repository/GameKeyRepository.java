package mk.ukim.finki.emt.lab.videogamecatalog.domain.repository;

import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.Category;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.CategoryId;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.GameKey;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.GameKeyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameKeyRepository extends JpaRepository<GameKey, GameKeyId> {
}
