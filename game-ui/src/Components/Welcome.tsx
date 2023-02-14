import axios, {AxiosResponse} from "axios";
import {Button} from "react-bootstrap";

export interface InitialGame {
    player1: "X";
    player2: "O";
    status: "NEW" | "IN_PROGRESS" | "FINISHED";
    total_INDEX: number;
    winner: "X" | "O";
}

type WelcomeProps = {
    setGameState: (status: string) => void;
}
export const Welcome = ({setGameState}: WelcomeProps) => {
    const startGame = () => {
        axios.post('http://localhost:3002/tictactoe/start').then(({data}: AxiosResponse<InitialGame>) => {
            setGameState(data.status)
        });
    }

    return (
        <div className="d-flex justify-content-center">
            <div className="title">
                <h3>Welcome to the FullStack Tic Tac Toe</h3>
            </div>
            <Button variant="success" className="center-screen" onClick={startGame}>Play Game</Button>
        </div>);

}