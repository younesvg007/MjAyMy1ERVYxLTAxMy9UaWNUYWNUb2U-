import React, {useState} from "react";
import axios from "axios";

type BoardProps = {
    board: number[];
    setGameState: (status: string) => void;
    setWinner: (status: string) => void;

}
export const Board = ({board, setGameState, setWinner}: BoardProps) => {

    const [player, setPLayer] = useState('X');
    const [cells, setCells] = useState(Array(9).fill(''));

    const playingGame = (value: number) => {
        if (cells[value] !== '') {
            return;
        }

        let squares = [...cells];

        if (player === 'X') {

            squares[value] = 'X';
            const payload = {
                index: value,
                player: 'X'
            };
            axios.post('http://localhost:3002/tictactoe/playing', payload).then(({data}) => {
                setPLayer('O');
                if(data.status === 'FINISHED') {
                    setGameState(data.status);
                    setWinner(data.winner);
                }
            });
        }

        if (player === 'O') {
            squares[value] = 'O';
            const payload = {
                index: value,
                player: 'O'
            };
            axios.post('http://localhost:3002/tictactoe/playing', payload).then(({data}) => {
                setPLayer('X');
                if(data.status === 'FINISHED') {
                    setGameState(data.status);
                    setWinner(data.winner)
                }
            });

        }

        setCells(squares);
    }

    return (
        <>
            <div className="board">
                {
                    board && board.map((value) => {
                        return (
                            <button key={value} className="cell" onClick={() => playingGame(value)}>{cells[value]}</button>)
                    })
                }
            </div>
        </>
    )
}