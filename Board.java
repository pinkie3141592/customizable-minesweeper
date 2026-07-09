import java.util.Random;

public class Board{

    private Random rand = new Random();

    private Cell[][] board;

    private boolean gameOver = false;

    public Board(int rows, int columns, int row, int col, double mineChance){

        if(mineChance < 0 || mineChance > 1)
            throw new IllegalArgumentException(
                "mine Chance must be between 0 and 1"
            );

        board = new Cell[rows][columns];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                board[i][j] = new Cell();
                board[i][j].revealed = false;

                if(i == row && j == col)
                    board[i][j].hasMine = false;

                else
                    if(rand.nextDouble() < mineChance)
                        board[i][j].hasMine = true;
            }
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                int count = 0;

                for(int di = -1; di <= 1; di++){
                    for(int dj = -1; dj <= 1; dj++){

                        if(di == 0 && dj == 0)
                            continue;

                        int newRow = i + di;
                        int newCol = j + dj;

                        if(newRow >= 0 &&
                        newRow < board.length &&
                        newCol >= 0 &&
                        newCol < board[newRow].length &&
                        board[newRow][newCol].hasMine){

                            count++;
                        }
                    }
                }

                board[i][j].nearbyMines = count;
            }
        }
    }

    public void revealCell(int row, int col){

        if(row < 0 || row >= board.length || col < 0 || col >= board[row].length)
            return;

        if(board[row][col].revealed)
            return;

        board[row][col].revealed = true;

        if(board[row][col].hasMine){
            System.out.println("GAME OVER.");
            gameOver = true;
            return;
        }

        if(board[row][col].nearbyMines > 0)
            return;

        for(int dr = -1; dr <= 1; dr++){
            for(int dc = -1; dc <= 1; dc++){

                if(dr == 0 && dc == 0)
                    continue;

                revealCell(row + dr, col + dc);
            }
        }
    }

    /* 
    public void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(!board[i][j].revealed)
                    System.out.print("[ ]");
                else{
                    if(!board[i][j].hasMine)
                        System.out.print(board[i][j].nearbyMines + " ");
                    else
                        System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
    */

    public void printBoard() {

        System.out.print("   ");
        for (int j = 0; j < board[0].length; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();

        System.out.print("   ");
        for (int j = 0; j < board[0].length; j++) {
            System.out.print("---");
        }
        System.out.println();

        for (int i = 0; i < board.length; i++) {

            System.out.printf("%2d| ", i);

            for (int j = 0; j < board[i].length; j++) {

                if (!board[i][j].revealed) {
                    System.out.print("■  ");
                }
                else if (board[i][j].hasMine) {
                    System.out.print("*  ");
                }
                else if (board[i][j].nearbyMines == 0) {
                    System.out.print("   ");
                }
                else {
                    System.out.print(board[i][j].nearbyMines + "  ");
                }
            }

            System.out.println();
        }
    }

    public void showAllMines(int rows, int columns){

        for(int i =0; i < rows; i ++)
        {
            for(int j = 0; j < columns; j++)
            {
                board[i][j].revealed = true;
            }
        }
    }

    //getters y setters

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isRevealed(int row, int col) {
        return board[row][col].revealed;
    }

    public boolean hasMine(int row, int col) {
        return board[row][col].hasMine;
    }

    public int getNearbyMines(int row, int col) {
        return board[row][col].nearbyMines;
    }

    public int getRows() {
        return board.length;
    }

    public int getColumns() {
        return board[0].length;
    }

}
