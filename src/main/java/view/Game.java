package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.DataBase;
import model.PacManCurrentPosition;

import java.io.IOException;

public class Game {
    private Terminal terminal;
    private Screen screen;
    private char[][] map;
    private TextGraphics textGraphics;
    private TerminalPosition position = new TerminalPosition(15, 1);

    public Game(Terminal terminal, Screen screen, TextGraphics textGraphics) {
        this.terminal = terminal;
        this.screen = screen;
        if (this.textGraphics == null) this.textGraphics = textGraphics;
    }


    public void clear() throws IOException {
        screen.clear();
        screen.refresh();
    }

    public void showCurrentName(String playerName) throws IOException {
        textGraphics.putString(34, 8, "Name: " + playerName);
        screen.refresh();
    }

    public void generateMap(char[][] map) throws IOException {
        screen.clear();
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 51; x++) {
                if (map[y][x] != '*') {
                    if (map[y][x] == '▅' || map[y][x] == '▉') {

                        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
                        textGraphics.putString(position.getColumn() + x, position.getRow() + y, String.valueOf(map[y][x]), SGR.FRAKTUR);
                    } else if (map[y][x] == 'C') {
                        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                        textGraphics.putString(position.getColumn() + x, position.getRow() + y, String.valueOf(map[y][x]), SGR.BOLD);
                    }
                    else {
                        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                        textGraphics.putString(position.getColumn() + x, position.getRow() + y, String.valueOf(map[y][x]));
                    }
                }
            }
        }

        screen.refresh();
    }

    public void refreshMap(int oldRow, int oldCol, int newRow, int newCol, char[][] map) throws IOException {
        if (map[newRow][newCol] == 'C' || map[newRow][newCol] == 'O') {
            textGraphics.putString(position.getColumn() + oldCol, position.getRow() + oldRow, " ");
            textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
            textGraphics.putString(position.getColumn() + newCol, position.getRow() + newRow, String.valueOf(map[newRow][newCol]), SGR.BOLD);
        }

        screen.refresh();
    }
}
