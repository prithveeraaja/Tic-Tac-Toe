package Models;


import Exceptions.InvalidGameDimensionException;
import Strategy.GameWinningStrategy;
import Strategy.orderOneGameWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private Board board;

    private List<Player> players;

    private List<Move> moves;

    private GameStatus gameStatus;

    private int nextPlayerIndex;

    private Player winningPlayer;

    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public  void makeNextMove(){
        Player playerWhoMoveItis = players.get(nextPlayerIndex);
        System.out.println("It is"+ playerWhoMoveItis.getName()+"s turn");

        Move move = playerWhoMoveItis.decideMove(board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
               board.applyMove(move);
               moves.add(move);
               //check winner
            if (gameWinningStrategy.checkWinner(board,move)){
                gameStatus = GameStatus.ENDED;
                winningPlayer = playerWhoMoveItis;
            }
              //DRAW
            if(moves.size() ==board.getSize() * board.getSize()){
                gameStatus = GameStatus.DRAW;
            }

            nextPlayerIndex +=1;
            nextPlayerIndex %= players.size();
        }else{
            //throw an exception
        }
    }

    public static class Builder{

        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build (){
            // validation
            try {
                isValid();
            } catch (InvalidGameDimensionException e){
                return null;
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setPlayers(players);
            game.setMoves(new LinkedList<>());
            game.setNextPlayerIndex(0);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setGameWinningStrategy(new orderOneGameWinningStrategy(dimension));

            return game;
        }

        private boolean isValid() throws InvalidGameDimensionException {
            if (dimension < 3){
                //return false;
                throw new InvalidGameDimensionException("Dimension should be greater than 2");

            }
            //TODO TO ADD MORE VALIDATION

            return true;
        }
    }


}
