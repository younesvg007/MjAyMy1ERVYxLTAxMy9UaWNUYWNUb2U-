package com.kata.tictactoe;

import com.kata.tictactoe.controller.GameRest;
import com.kata.tictactoe.model.Game;
import com.kata.tictactoe.model.GameStatus;
import com.kata.tictactoe.model.Player;
import com.kata.tictactoe.service.GameService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class GameRestTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameRest gameRest;

    @Test
    public void testInitialize() {
        Game game = new Game();
        game.setPlayer1(Player.X);
        game.setPlayer2(Player.O);
        game.setStatus(GameStatus.NEW);
        when(gameService.initializeGame()).thenReturn(game);

        ResponseEntity<Game> response = gameRest.initialize();

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(game, response.getBody());
    }
}
