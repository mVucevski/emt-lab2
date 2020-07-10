package mk.ukim.finki.emt.lab.videogamecatalog.domain.repository;

import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.Genre;
import mk.ukim.finki.emt.lab.videogamecatalog.domain.model.GenreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, GenreId> {
}
