import React, {Fragment, useState} from "react";
import './Game.css';
import {Welcome} from "../Components/Welcome";
import {Board} from "../Components/Board";
import {Button} from "react-bootstrap";


export const Game = () => {

    const squareIndexes = [0, 1, 2, 3, 4, 5, 6, 7, 8];
    const [gameState, setGameState] = useState<string>('')
    const [winner, setWinner] = useState<string>('');

    return (
        <Fragment>
            {!['NEW', 'IN_PROGRESS', 'FINISHED'].includes(gameState) && <Welcome setGameState={setGameState}/>}

            {gameState === 'NEW' &&
                <Board board={squareIndexes} setGameState={setGameState} setWinner={setWinner}/>}

            {gameState === 'FINISHED' &&
                <div className="d-flex justify-content-center">
                    <div className="title">
                        {(winner && winner.length > 0) ? (<h3>Winner : {winner}</h3>) : (<h3>Draw. No winner</h3>)}
                        <Button variant="success" className="center-screen" onClick={() => setGameState('')}>Go Home
                            Page</Button>
                    </div>
                </div>}

        </Fragment>
    )
}