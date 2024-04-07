package Models;

public class Cell {

    private Player player;

    private int row;

    private int col;

    private CellState cellstate;

    public Cell (int i,int j){
        this.row = i;
        this.col = j;
        this.cellstate = CellState.EMPTY;
    }

    public Player getPlayer() {

        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {

        return row;
    }

    public void setRow(int row) {

        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellState() {

        return cellstate;
    }

    public void setCellState(CellState cellstate) {

        this.cellstate = cellstate;
    }


}
