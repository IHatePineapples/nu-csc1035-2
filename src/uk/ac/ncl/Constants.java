/**
 * @author Kostiantyn Potomkin
 * @version 0.9
 * @since 05-03-2020
 */
package uk.ac.ncl;

import uk.ac.ncl.entity.CellStatus;

import java.awt.*;

public class Constants {
    public static final String EMPTY_STRING = "";
    public static int BOARD_SIZE = 8;
    public static CellStatus PLAYERS_CELL_STATUS = CellStatus.DARK;
    public static CellStatus OPPONENTS_CELL_STATUS = CellStatus.LIGHT;
    public static Color PLAYERS_COLOUR = Color.BLACK;
    public static Color OPPONENTS_COLOUR = Color.WHITE;
    public static Color BOARD_COLOUR = new Color(820000);
    public static int DELAY_TIME = 400;
}
