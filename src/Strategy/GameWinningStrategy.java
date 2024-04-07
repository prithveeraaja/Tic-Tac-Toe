package Strategy;

import Models.Board;
import Models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board borad, Move move);
}
