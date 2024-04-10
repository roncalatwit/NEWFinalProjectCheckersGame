package application;

/**
 * Enumeration representing the type of piece in the checkers game.
 * Pieces can be either RED or BLUE, each with a corresponding move direction.
 * 
 * @author Roncal and Luong
 */
public enum PieceType {

	/**
     * Red piece with a positive move direction.
     */
	RED(1), 
	/**
     * Blue piece with a negative move direction.
     */
	BLUE(-1);
	
	/**
     * The direction in which the piece can move.
     */
	int moveDir = 0;
	
	/**
     * Constructs a new PieceType with the specified move direction.
     *
     * @param moveDir The move direction of the piece.
     */
	PieceType(int moveDir){
	    this.moveDir = moveDir;
	}
	
}
