package com.kata.tictactoe;

import com.kata.tictactoe.model.Game;
import com.kata.tictactoe.model.GameStatus;
import com.kata.tictactoe.model.Player;
import com.kata.tictactoe.service.GameService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    public void testInitialiseGame(){
        final int TOTAL_COMBIN = 8;
        Game game = gameService.initializeGame();

        Assert.assertNotNull(game);
        Assert.assertNotNull(gameService.getCombinWin());
        Assert.assertEquals(TOTAL_COMBIN, gameService.getCombinWin().size());
    }


    @Test
    public void testClearGame(){
        final int EMPTY_LIST = 0;
        Game game = new Game ();
        game.setStatus(GameStatus.FINISHED);
        game.setPlayer1(Player.X);
        game.setPlayer2(Player.O);
        game.setWinner(Player.O);
        gameService.setGame(game);
        gameService.setListIndexPerPlayer(new HashMap<>(Map.of(
                gameService.getGame().getPlayer1(), Arrays.asList(2, 5, 1, 4),
                gameService.getGame().getPlayer2(), Arrays.asList(0, 8, 3, 7, 6)
        )));

        gameService.clearGame();

        Assert.assertEquals(GameStatus.NEW, gameService.getGame().getStatus());
        Assert.assertEquals(Player.X, gameService.getGame().getPlayer1());
        Assert.assertEquals(Player.O, gameService.getGame().getPlayer2());
        Assert.assertNull(gameService.getGame().getWinner());
        Assert.assertEquals(EMPTY_LIST, gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer1()).size());
        Assert.assertEquals(EMPTY_LIST, gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer2()).size());
    }
}
