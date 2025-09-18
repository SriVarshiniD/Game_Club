package com.varshini.game_club.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.varshini.game_club.model.Game;
@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    
}


