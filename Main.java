import java.util.Scanner;

public class Main{

    public static void main(String[] args){

        int row, col;

        Scanner input = new Scanner(System.in);
        
        System.out.println("Minesweeper cargando...");
    
        Board gameBoard = new Board(8,8, 0.30);
        
        do 
        {
            gameBoard.printBoard();

            System.out.print("Select row: ");
            row = input.nextInt();

            System.out.print("Select column: ");
            col = input.nextInt();

            gameBoard.revealCell(row,col);

        }while(!gameBoard.gameOver);

        input.close();

        gameBoard.printBoard();


    }



}