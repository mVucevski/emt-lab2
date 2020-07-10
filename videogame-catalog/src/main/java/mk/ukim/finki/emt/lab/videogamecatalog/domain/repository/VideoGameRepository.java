package mk.ukim.finki.emt.lab.videogamecatalog.domain.repository;

import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGame;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.VideoGameId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, VideoGameId> {
}
