package com.varshini.game_club.services; 
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.varshini.game_club.Exceptions.BussinessException;
import com.varshini.game_club.Exceptions.IdNotPresentException;
import com.varshini.game_club.model.Game;
import com.varshini.game_club.repository.GameRepository;
@Service
public class GameService {
    @Autowired
    private GameRepository gamerepo;

    // Create a new Game
    public Game create(Game game) {
        game.setId(null); // ensure new entry
        Game savGame = gamerepo.save(game);
        return savGame;
    }

    // Get all Games
    public List<Game> findAll() {
        List<Game> games = gamerepo.findAll();
        return games;
    }

    // Get a Game by ID
    public Game findById(String id) throws IdNotPresentException {
        Optional<Game> OptionalGame = gamerepo.findById(id);
        if (OptionalGame.isEmpty()) {
            throw new IdNotPresentException("Game with ID " + id + " not found");
        }
        return OptionalGame.get();
    }

    // Update a Game
    public Game update(String id, Game game) throws IdNotPresentException {
        Optional<Game> OptionalGame = gamerepo.findById(id);
        if (OptionalGame.isEmpty()) {
            throw new IdNotPresentException("Game with ID " + id + " not found");
        }
        Game oldGame = OptionalGame.get();
        oldGame.setName(game.getName());
        oldGame.setDescription(game.getDescription());
        oldGame.setPrice(game.getPrice());
        Game updatedGame = gamerepo.save(oldGame);
        return updatedGame;
    }

    // Delete a Game by ID
    public boolean delete(String id) throws IdNotPresentException {
        Optional<Game> OptionalGame = gamerepo.findById(id);
        if (OptionalGame.isEmpty()) {
            throw new IdNotPresentException("Game with ID " + id + " not found");
        }
        gamerepo.deleteById(id);
        return true;
    }
}
