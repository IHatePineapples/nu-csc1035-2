package uk.ac.ncl.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.entity.*;
import uk.ac.ncl.game.MoveChecker;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static uk.ac.ncl.Constants.BOARD_SIZE;

class MoveCheckerTest {

    private MoveChecker moveChecker;
    private Cell[][] cells;

    @BeforeEach
    void setUp() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        moveChecker = new MoveChecker(cells);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                CellStatus cellStatus = CellStatus.EMPTY;
                Cell tempEl = new Cell(cellStatus, new JButton(), i, j);
                if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
                    tempEl.setValue( CellStatus.LIGHT);
                } else if ((i == 4 && j == 4) || (i == 3 && j == 3)) {
                    tempEl.setValue( CellStatus.DARK);
                }
                cells[i][j] = tempEl;
            }
        }
    }

    @Test
    void generateOpponentDark() {
        Cell cell = moveChecker.generateOpponent(CellStatus.DARK);

        assertTrue(cells[2][4] == cell || cells[3][5] == cell ||
                cells[4][2] == cell || cells[4][3] == cell);
        assertNotNull(cell.getMove());
        assertEquals(cell.getMove().getMoves().size(), 1);
        assertEquals(cell.getMove().getScore(), 1);
    }

    @Test
    void generateOpponentLight() {
        Cell cell = moveChecker.generateOpponent(CellStatus.LIGHT);

        assertTrue(cells[2][3] == cell || cells[3][2] == cell ||
                cells[4][5] == cell || cells[5][4] == cell);
        assertNotNull(cell.getMove());
        assertEquals(1, cell.getMove().getMoves().size());
        assertEquals(1, cell.getMove().getScore());
    }

    @Test
    void flipCheckers() {
        cells[5][3].isLegal(CellStatus.LIGHT, this.cells);
        moveChecker.flipPieces(cells[5][3], CellStatus.LIGHT);
        assertSame(cells[4][3].getValue(), CellStatus.LIGHT);
        assertNull(cells[4][3].getMove());
    }

    @Test
    void findPotentialMoves() {
        ArrayList<Cell> grayCells = moveChecker.findPotentialMoves(CellStatus.DARK);
        assertEquals(4, grayCells.size());
        assertTrue(grayCells.contains(cells[2][4]));
        assertTrue(grayCells.contains(cells[3][5]));
        assertTrue(grayCells.contains(cells[4][2]));
        assertTrue(grayCells.contains(cells[5][3]));
    }

    @Test
    void colourPieces() {
        CellStatus testColour = CellStatus.GRAY;
        ArrayList<Cell> piecesToColour = new ArrayList<>();
        piecesToColour.add(cells[2][4]);
        piecesToColour.add(cells[3][5]);
        piecesToColour.add(cells[4][2]);
        piecesToColour.add(cells[5][3]);
        moveChecker.colourPieces(piecesToColour, CellStatus.GRAY);
        assertSame(cells[2][4].getValue(), testColour);
        assertSame(cells[3][5].getValue(), testColour);
        assertSame(cells[4][2].getValue(), testColour);
        assertSame(cells[5][3].getValue(), testColour);
    }

    @Test
    void getFinalScore() {
        String result = "The game is over. It is a draw. Each player has " + 2 + " pieces";
        assertEquals(result, moveChecker.getFinalScore());
    }

    @Test
    void removeMoves() {
        ArrayList<Cell> grayCells = moveChecker.findPotentialMoves(CellStatus.DARK);
        assertNotNull(cells[2][4].getMove());
        assertNotNull(cells[3][5].getMove());
        assertNotNull(cells[4][2].getMove());
        assertNotNull(cells[5][3].getMove());
        moveChecker.removeMoves(grayCells);
        assertNull(cells[2][4].getMove());
        assertNull(cells[3][5].getMove());
        assertNull(cells[4][2].getMove());
        assertNull(cells[5][3].getMove());
    }

    @Test
    void testIsLegal(){
        assertTrue(cells[5][3].isLegal(CellStatus.DARK, this.cells));
        assertEquals(1, cells[5][3].getMove().getMoves().size());
        assertEquals(3, cells[5][3].getMove().getMoves().get(0).getCell().getColumn());
        assertEquals(3, cells[5][3].getMove().getMoves().get(0).getCell().getRow());
        assertEquals(1, cells[5][3].getMove().getScore());
    }
}