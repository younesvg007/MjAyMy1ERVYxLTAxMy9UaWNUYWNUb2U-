package com.kata.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Player {
    X(1), O(2);
    private final Integer value;

}

