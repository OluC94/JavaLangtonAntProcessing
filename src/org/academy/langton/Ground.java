package org.academy.langton;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Ground {

    private List<List<Cell>> rowsOfCells;
    private final PApplet applet;
    /**
     * cell size in pixels
     */
    private final int cellSize;

    public Ground(PApplet applet, int numColumns, int numRows, int cellSizeInPixels) {
        this.applet = applet;
        this.rowsOfCells = this.createRowsOfCells(numColumns, numRows);
        this.cellSize = cellSizeInPixels;
        //we could also store numColumns and numRows but these can be derived easily.
    }

    int getCellSize() {
        return this.cellSize;
    }

    /**
     * Returns the number of columns in the land grid.  Not the pixel width.
     */
    int width() {
        return this.rowsOfCells.getFirst().size();
    }

    /**
     * Returns the number of rows in the land grid.  Not the pixel height.
     */
    int height() {
        return this.rowsOfCells.size();
    }

    //private
    List<List<Cell>> createRowsOfCells(int maxColumns, int maxRows) {
        ArrayList<List<Cell>> rows = new ArrayList<>();
        for (int y = 0; y < maxRows; y++) {
            ArrayList<Cell> row = new ArrayList<>();
            rows.add(row);
            for (int x = 0; x < maxColumns; x++) {
                Cell cell = new Cell(x, y);
                row.add(cell);
            }
        }
        return rows;
    }

    PixelPosition cellPositionAsPixelPosition(GridPosition cellPos) {
        return new PixelPosition(this.cellSize * cellPos.x(), this.cellSize * cellPos.y());
    }

    void draw() {

        for (List<Cell> rowOfCells : rowsOfCells) {

            for (Cell cell : rowOfCells) {

                applet.stroke(100);
                applet.fill(cell.isActive() ?  0xFF6347 : 0xDCDCDC);
                applet.square(
                        cell.gridPosition().x() * cellSize,
                        cell.gridPosition().y() * cellSize,
                        cellSize);
            }
        }
    }

    Cell cellAt(int x, int y) {
        return rowsOfCells.get(y).get(x);
    }

    GridPosition midpoint() {
        return new GridPosition(
                (int)Math.floor(width() / 2.0),
                (int)Math.floor(height() / 2.0));
    }

    void clear() {
        rowsOfCells = createRowsOfCells(width(), height());
    }

    boolean isPositionOutOfBounds(GridPosition pos){
        return pos.x() < 0 || pos.x() > width() - 1 || pos.y() < 0 || pos.y() > height() - 1;
    }

} //ends the class Land declaration