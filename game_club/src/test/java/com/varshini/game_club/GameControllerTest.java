package com.varshini.game_club;

import com.varshini.game_club.model.Game;
import com.varshini.game_club.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepo;

    private Game savedGame;

    @BeforeEach
    void setup() {
        // Clear repo before each test
        gameRepo.deleteAll();

        // Insert one game to test GET/PUT/DELETE
        Game game = new Game();
        game.setName("Chess");
        game.setDescription("Classic strategy game");
        game.setPrice(50.0);
        savedGame = gameRepo.save(game);
    }

    @Test
    void testCreateGame() throws Exception {
        mockMvc.perform(post("/game")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Ludo King\",\"description\":\"Fun game\",\"price\":30.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ludo King"))
                .andExpect(jsonPath("$.description").value("Fun game"))
                .andExpect(jsonPath("$.price").value(30.0));
    }

    @Test
    void testGetAllGames() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Chess"));
    }

    @Test
    void testGetGameById() throws Exception {
        mockMvc.perform(get("/game/" + savedGame.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chess"))
                .andExpect(jsonPath("$.price").value(50.0));
    }

    @Test
    void testUpdateGame() throws Exception {
        mockMvc.perform(put("/game/" + savedGame.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Chess\",\"description\":\"Updated desc\",\"price\":75.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Chess"))
                .andExpect(jsonPath("$.price").value(75.0));
    }

    @Test
    void testDeleteGame() throws Exception {
        mockMvc.perform(delete("/game/" + savedGame.getId()))
                .andExpect(status().isOk());
    }
}
