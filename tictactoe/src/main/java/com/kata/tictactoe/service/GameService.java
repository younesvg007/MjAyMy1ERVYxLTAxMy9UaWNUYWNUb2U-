package com.kata.tictactoe.service;

import com.kata.tictactoe.model.Game;
import com.kata.tictactoe.model.GameStatus;
import com.kata.tictactoe.model.Player;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
public class GameService {

    private Map<String, List<Integer>> combinWin;
    private Map<Player, List<Integer>> listIndexPerPlayer;
    private Game game;

    public Game initializeGame() {
        clearGame();

        combinWin = new HashMap<>(Map.of(
                "1", Arrays.asList(0, 1, 2),
                "2", Arrays.asList(3, 4, 5),
                "3", Arrays.asList(6, 7, 8),
                "4", Arrays.asList(0, 3, 6),
                "5", Arrays.asList(1, 4, 7),
                "6", Arrays.asList(2, 5, 8),
                "7", Arrays.asList(0, 4, 8),
                "8", Arrays.asList(2, 4, 6)
        ));
        return game;
    }

    public void clearGame() {
        game = new Game();
        game.setStatus(GameStatus.NEW);
        game.setPlayer1(Player.X);
        game.setPlayer2(Player.O);

        listIndexPerPlayer = new HashMap<>(Map.of(
                game.getPlayer1(), new ArrayList<>(),
                game.getPlayer2(), new ArrayList<>()
        ));
    }

    public Game playingGame(Player player, Integer index) {
        game.setStatus(GameStatus.IN_PROGRESS);

        if (checkWinner(player, index)) {
            finishGame();
        }
        return game;
    }

    public boolean checkWinner(Player player, Integer index) {
        final int INITIAL_COUNTER = 0;
        final int TOTAL_COUNTER = 3;

        listIndexPerPlayer.get(player).add(index);

        for (Map.Entry<String, List<Integer>> set : combinWin.entrySet()) {
            int counter = INITIAL_COUNTER;
            for (Integer indexCombin : combinWin.get(set.getKey())) {

                for (Integer indexPlayer : listIndexPerPlayer.get(player)) {
                    if (Objects.equals(indexPlayer, indexCombin)) {
                        counter++;
                    }
                }
                if (counter == TOTAL_COUNTER) {
                    game.setWinner(player);
                    return true;
                }
            }
        }
        checkDraw();
        return false;
    }

    public void checkDraw() {
        if (listIndexPerPlayer.get(game.getPlayer1()).size() + listIndexPerPlayer.get(game.getPlayer2()).size() == game.getTOTAL_INDEX()) {
            finishGame();
        }
    }

    public Game finishGame() {
        game.setStatus(GameStatus.FINISHED);
        return game;
    }
}
