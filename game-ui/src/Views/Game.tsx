import React, {Fragment, useState} from "react";
import './Game.css';
import {Welcome} from "../Components/Welcome";
import {Board} from "../Components/Board";
import {Button} from "react-bootstrap";

export const Game = () => {
    //const squareIndexes = [0, 1, 2, 3, 4, 5, 6, 7, 8];
    const [gameState, setGameState] = useState<string>('')
   // const [winner, setWinner] = useState<string>('');


    return (
        <Fragment>
            {!['NEW', 'IN_PROGRESS', 'FINISHED'].includes(gameState) && <Welcome setGameState={setGameState}/>}
        </Fragment>
    )
}