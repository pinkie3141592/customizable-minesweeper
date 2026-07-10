package GUI;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import model.Board;
import model.Cell;

public class MinesweeperGUI extends JFrame {
    
    private JButton[][] buttons;
    private int rows = 16;
    private int columns = 16;
    private Board board;

    public MinesweeperGUI() {

        setTitle("Customizable Minesweeper");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(rows,columns));

        buttons = new JButton[rows][columns];

        for (int i = 0; i < rows; i++)
            for(int j=0; j < columns; j++)
            {

                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
                buttons[i][j].setFocusable(false);

                final int r = i;
                final int c = j;

                buttons[i][j].addActionListener(e -> {

                    if (board == null) {
                        board = new Board(rows, columns, r, c, 0.30);
                    }

                    board.revealCell(r, c);

                    refreshBoard();

                    if(board.isGameOver()){
                        JOptionPane.showMessageDialog(this, "Game Over!");
                        board.showAllMines(rows,columns);

                    }

                });

                add(buttons[i][j]);

            }
        setVisible(true);

    }

    public static void main(String[] args) {
        new MinesweeperGUI();
    }



    private void refreshBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {

                if (board.isRevealed(row, col)) {

                    int mines = board.getNearbyMines(row, col);

                    if(board.hasMine(row,col))
                    {
                        buttons[row][col].setText("*");
                    }
                    else
                    {
                        buttons[row][col].setText(String.valueOf(mines));
                    }

                    buttons[row][col].setEnabled(false);                       


                }
            }
        }
    }

}
