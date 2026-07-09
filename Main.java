import java.util.Scanner;

public class Main{

    public static void main(String[] args){

        int row, col;

        Scanner input = new Scanner(System.in);
        
        System.out.println("Minesweeper cargando...");

        System.out.print("Select row: ");
        row = input.nextInt();

        System.out.print("Select column: ");
        col = input.nextInt();

        Board gameBoard = new Board(8,8, row, col, 0.30);

        gameBoard.revealCell(row, col);

        do 
        {

            gameBoard.printBoard();

            System.out.print("Select row: ");
            row = input.nextInt();

            System.out.print("Select column: ");
            col = input.nextInt();

            gameBoard.revealCell(row,col);

        }while(!gameBoard.isGameOver());

        input.close();

        gameBoard.printBoard();


    }



}