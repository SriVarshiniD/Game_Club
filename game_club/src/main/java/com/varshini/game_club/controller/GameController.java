package com.varshini.game_club.controller;
import java.util.Optional;
import com.varshini.game_club.services.GameService;
import com.varshini.game_club.Exceptions.IdNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.varshini.game_club.model.Game;
import com.varshini.game_club.repository.GameRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path="/game")
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private GameService service;
     @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        log.info("Creating new game: {}", game);
        Game savedGame = service.create(game);
        log.info("Game created with ID: {}", savedGame.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() throws IdNotPresentException {
        log.info("Fetching all games");
        List<Game> games = service.findAll();
        log.info("Total games found: {}", games.size());
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }
     @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) throws IdNotPresentException {
        log.info("Fetching game with ID: {}", id);
        Game game = service.findById(id);
        if (game != null) {
            log.info("Game found: {}", game);
        } else {
            log.warn("Game not found with ID: {}", id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody Game game  ) throws Exception {
        log.info("Updating game with ID: {}", id);
        Game updatedGame = service.update(id, game);
        log.info("Game updated: {}", updatedGame);
        return ResponseEntity.status(HttpStatus.OK).body(updatedGame);
    }
   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable String id) throws IdNotPresentException {
        log.info("Deleting game with ID: {}", id);
        boolean deleted = service.delete(id);
        if (deleted) {
            log.info("Game with ID {} deleted successfully", id);
            return ResponseEntity.status(HttpStatus.OK).body("Game with ID " + id + " deleted successfully");
        } else {
            log.error("Failed to delete Game with ID {}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Game with ID " + id);
        }
    }
}