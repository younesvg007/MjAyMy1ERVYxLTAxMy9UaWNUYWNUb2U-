package com.kata.tictactoe.controller;

import com.kata.tictactoe.model.Game;
import com.kata.tictactoe.model.PlayerDTO;
import com.kata.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/tictactoe")
public class GameRest {

    @Autowired
    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Game> initialize() {
        return ResponseEntity.ok(gameService.initializeGame());
    }

    @PostMapping("/playing")
    public ResponseEntity<Game> playing(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(gameService.playingGame(playerDTO.getPlayer(), playerDTO.getIndex()));
    }

    @PostMapping("/finish")
    public ResponseEntity<Game> finish() {
        return ResponseEntity.ok(gameService.finishGame());
    }
}
