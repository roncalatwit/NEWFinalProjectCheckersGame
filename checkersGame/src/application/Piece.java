package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static application.CheckersApp.TILE_SIZE;

/**
 * Represents a piece in the checkers game, which is drawn as an ellipse on the board.
 * It also contains logic for mouse interactions to move the piece.
 * 
 * @author Roncal and Luong
 */
public class Piece extends StackPane{
	
	/**
     * The type of piece.
     */
	private PieceType type;
	
	private double mouseX, mouseY;
	
	/**
     * The X and Y coordinates of the piece's previous position.
     */
	private double oldX, oldY;
	
	/**
     * Gets the type of piece.
     *
     * @return The type of piece.
     */
	public PieceType getType() {
		return type;
	}
	
	/**
     * Gets the X-coordinate of the piece's previous position.
     *
     * @return The X-coordinate of the previous position.
     */
	public double getOldX() {
		return oldX;
	}
	
	/**
     * Gets the Y-coordinate of the piece's previous position.
     *
     * @return The Y-coordinate of the previous position.
     */
	public double getOldY(){
		return oldY;
	}
	
	/**
     * Creates a piece and sets its initial position on the game board.
     * 
     * @param type The type of the piece, which can be either RED or BLUE.
     * @param x    The initial x-coordinate on the game board.
     * @param y    The initial y-coordinate on the game board.
     */
	public Piece(PieceType type, int x, int y) {
		this.type = type;
		
		move(x, y);
		
		Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE *0.26);
		bg.setFill(Color.BLACK);
		bg.setStroke(Color.BLACK);
		bg.setStrokeWidth(TILE_SIZE * 0.03);
		bg.setTranslateX((TILE_SIZE - TILE_SIZE*0.3125*2)/2);
		bg.setTranslateY((TILE_SIZE - TILE_SIZE*0.26*2)/2 + TILE_SIZE *0.07);
		
		
		
		Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE *0.26);
		ellipse.setFill(type == PieceType.RED ? Color.RED : Color.BLUE);
		ellipse.setStroke(Color.BLACK);
		ellipse.setStrokeWidth(TILE_SIZE * 0.03);
		ellipse.setTranslateX((TILE_SIZE - TILE_SIZE*0.3125*2)/2);
		ellipse.setTranslateY((TILE_SIZE - TILE_SIZE*0.26*2)/2);
		
		getChildren().addAll(bg, ellipse);
		
		setOnMousePressed(e ->{
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		
		setOnMouseDragged(e ->{
			relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
		});
	}
	
	/**
     * Moves the piece to the specified coordinates on the game board.
     * 
     * @param x The x-coordinate to move to.
     * @param y The y-coordinate to move to.
     */
	public void move(int x, int y) {
		oldX = x*TILE_SIZE;
		oldY = y*TILE_SIZE;
		relocate(oldX, oldY);
		
	}
	
	/**
     * Aborts the current move and resets the piece to its previous position.
     */
	public void abortMove() {
		relocate(oldX, oldY);
	}
	
}

