package mk.ukim.finki.emt.lab.ordermanagement.application;

import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGame;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGameId;

import java.util.List;

public interface VideoGameCatalog {

    List<VideoGame> findAll();

    VideoGame findById(VideoGameId id);


}
