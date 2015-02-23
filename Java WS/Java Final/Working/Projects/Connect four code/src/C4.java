
import javax.swing.*;

import java.awt.event.*;

import java.awt.GridLayout;

 

public class C4 {

    JFrame frame;

    JPanel panel;

    final int rowTiles = 6;

    final int colTiles = 5;

    int row, col, rowSelected, colSelected = 0;

    int pTurn = 0;

    boolean win = false;

    JButton[][] button = new JButton[rowTiles][colTiles];

    int[][] grid = new int[rowTiles][colTiles];

    GridLayout myGrid = new GridLayout(6,7);

    final ImageIcon c0 = new ImageIcon("p1.png");

    final ImageIcon c1 = new ImageIcon("p2.png");

    final ImageIcon c2 = new ImageIcon("p3.png");

 

    public C4() {

        frame = new JFrame("Connect Four");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        panel.setLayout(myGrid);

 

        for (row = rowTiles; row >= 0; row--) {

            for (col = colTiles; col >= 0; col--) {

                button[row][col] = new JButton(c0);

                button[row][col].addActionListener(new buttonListener());

                panel.add(button[col][row]);

            }

        }

 

        frame.setContentPane(panel);

        frame.setSize(700,600);

        frame.pack();

        frame.setVisible(true);

    }

 

    class buttonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String eventName = event.getActionCommand();

 

/*          if (win = false) {

                if (pTurn % 2 == 0) {

                    button[rowSelected][colSelected].setIcon(ImageIcon(c1));

                }

                if (pTurn % 2 == 1) {

                    button[rowSelected][colSelected].setIcon(ImageIcon(c2));

                }

                if (jLabelCheck(colSelected) == true) {

                    checkWin();

                    pTurn = pTurn + 1;

                }

           } *///I dunno where to go with this part, it's just here to give an idea of what I want to accomplish

        }

    }

 

    public boolean checkWin() {

      // check for a horizontal win

        for (int row=0; row<6; row++) {

            for (int col=0; col<4; col++) {

                if (grid[col][row] != 0 &&

              grid[col][row] == grid[col+1][row] &&

                grid[col][row] == grid[col+2][row] &&

              grid[col][row] == grid[col+3][row]) {

                   win = true;

                }

            }

        }

        // check for a vertical win

        for (int row=0; row<3; row++) {

            for (int col=0; col<7; col++) {

            if (grid[col][row] != 0 &&

            grid[col][row] == grid[col][row+1] &&

            grid[col][row] == grid[col][row+2] &&

            grid[col][row] == grid[col][row+3]) {

                win = true;

                }

            }

        }

        // check for a diagonal win (positive slope)

        for (int row=0; row<3; row++) {

            for (int col=0; col<4; col++) {

            if (grid[col][row] != 0 &&

            grid[col][row] == grid[col+1][row+1] &&

            grid[col][row] == grid[col+2][row+2] &&

            grid[col][row] == grid[col+3][row+3]) {

                win = true;

                }

            }

        }

        // check for a diagonal win (negative slope)

        for (int row=3; row<6; row++) {

            for (int col=0; col<4; col++) {

            if (grid[col][row] != 0 &&

            grid[col][row] == grid[col+1][row-1] &&

            grid[col][row] == grid[col+2][row-2] &&

            grid[col][row] == grid[col+3][row-3]) {

                win = true;

                }

            }

        }

        return win;

    }

 

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                JFrame.setDefaultLookAndFeelDecorated(true);

                new C4();

            }

        });

    }

}
