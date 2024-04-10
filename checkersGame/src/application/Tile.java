package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Tile class represents a single tile on the checkers board.
 * It contains information about whether a piece is currently occupying the tile.
 * 
 * @author Roncal and Luong
 */
public class Tile extends Rectangle {
	
	/**
     * The piece occupying the tile (if any).
     */
	private Piece piece;
	
	/**
     * Checks if the tile has a piece occupying it.
     *
     * @return {@code true} if the tile has a piece; {@code false} otherwise.
     */
	public boolean hasPiece() {
		return piece !=null;
	}
	
	/**
     * Gets the piece occupying the tile.
     *
     * @return The piece occupying the tile, or {@code null} if no piece is present.
     */
	public Piece getPiece() {
		return piece;
	}
	
	/**
     * Sets the piece occupying the tile.
     *
     * @param piece The piece to set on the tile.
     */
	public void setPiece(Piece piece) {
		this.piece = piece;;
	}

	/**
     * Constructs a new Tile with the specified characteristics.
     *
     * @param light Whether the tile is light or dark.
     * @param x     The X-coordinate of the tile on the board.
     * @param y     The Y-coordinate of the tile on the board.
     */
	public Tile(boolean light, int x, int y) {
		setWidth(CheckersApp.TILE_SIZE);
		setHeight(CheckersApp.TILE_SIZE);
		
		relocate(x * CheckersApp.TILE_SIZE, y * CheckersApp.TILE_SIZE);
		
		setFill(light ? Color.WHITE : Color.GRAY);
	}


	
}
