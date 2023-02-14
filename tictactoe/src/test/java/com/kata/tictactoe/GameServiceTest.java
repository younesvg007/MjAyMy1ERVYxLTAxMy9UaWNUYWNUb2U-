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

    @Test
    public void testPlayingGameInCaseInProgress(){
        gameService.initializeGame();

        gameService.playingGame(Player.X, 0);

        Assert.assertNotNull(gameService.getGame());
        Assert.assertEquals(GameStatus.IN_PROGRESS, gameService.getGame().getStatus());
    }

    @Test
    public void testPlayingGameInCaseWinnerFound(){
        gameService.initializeGame();
        gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer1()).addAll(Arrays.asList(0, 4, 3));
        gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer2()).addAll(Arrays.asList(1, 8, 7));

        gameService.playingGame(Player.X, 6);

        Assert.assertNotNull(gameService.getGame());
        Assert.assertNotEquals(GameStatus.IN_PROGRESS, gameService.getGame().getStatus());
    }

    @Test
    public void testCheckWinnerInCaseGamePlayer1StartToProgress(){
        Game game = gameService.initializeGame();
        game.setStatus(GameStatus.IN_PROGRESS);
        gameService.setGame(game);

        boolean isPlayerXWinner = gameService.checkWinner(gameService.getGame().getPlayer1(), 0);

        Assert.assertFalse(isPlayerXWinner);
        Assert.assertNull(gameService.getGame().getWinner());
    }

    @Test
    public void testCheckWinnerInCaseGamePlayer2Win(){
        Game game = gameService.initializeGame();
        game.setStatus(GameStatus.IN_PROGRESS);
        gameService.setGame(game);
        gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer1()).addAll(Arrays.asList(1, 8, 7));
        gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer2()).addAll(Arrays.asList(0, 4, 3));

        boolean isPlayerYWinner = gameService.checkWinner(gameService.getGame().getPlayer2(), 6);

        Assert.assertTrue(isPlayerYWinner);
        Assert.assertEquals(Player.O, gameService.getGame().getWinner());
    }


    @Test
    public void testCheckDrawInCaseGameNotFinished(){
        Game game = gameService.initializeGame();
        game.setStatus(GameStatus.IN_PROGRESS);
        gameService.setGame(game);
        gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer1()).addAll(Arrays.asList(1, 7, 2));
        gameService.getListIndexPerPlayer().get(gameService.getGame().getPlayer2()).addAll(Arrays.asList(4, 3));

        gameService.checkDraw();

        Assert.assertNotEquals(GameStatus.FINISHED, gameService.getGame().getStatus());
    }

    @Test
    public void testFinishGame(){
        //TODO
    }

}
