package com.kata.tictactoe.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Game {

    private Player player1;
    private Player player2;
    private GameStatus status;
    private Player winner;
    private final Integer TOTAL_INDEX = 9;

}
