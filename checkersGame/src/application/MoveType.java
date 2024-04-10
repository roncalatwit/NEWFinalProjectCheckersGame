package application;

/**
 * Enumeration representing the type of move in the checkers game.
 * Valid move types are NONE, NORMAL, and KILL.
 * 
 * @author Roncal and Luong
 */
public enum MoveType {
	/**
     * No move.
     */
	NONE, 
	/**
     * Normal move.
     */
	NORMAL, 
	/**
     * Move resulting in a captured piece.
     */ 
	KILL;
	
}
