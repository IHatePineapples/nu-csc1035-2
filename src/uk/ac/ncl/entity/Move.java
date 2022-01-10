/**
 * @author Kostiantyn Potomkin
 * @version 0.9
 * @since 05-03-2020
 */
package uk.ac.ncl.entity;

import java.util.ArrayList;

/**
 * Stores potential moves of the piece.
 */
public class Move {

    /**
     *  Specifies valid moves.
     */
    private ArrayList<DirectedMove> moves;

    /**
     * Stores the total number of pieces to flip during the move.
     */
    private int score;

    public Move(ArrayList<DirectedMove> moves, int score) {
        this.moves = moves;
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public ArrayList<DirectedMove> getMoves() {
        return moves;
    }
}
