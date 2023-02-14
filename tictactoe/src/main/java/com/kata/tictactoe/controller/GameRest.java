package com.kata.tictactoe.controller;

import com.kata.tictactoe.model.Game;
import com.kata.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
