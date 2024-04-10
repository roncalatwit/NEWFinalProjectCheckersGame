package application;

/**
 * The MoveResult class represents the result of a move in the checkers game.
 * It contains information about the type of move and any captured pieces.
 * 
 * @author Roncal and Luong
 */
public class MoveResult {

	/**
     * The type of move.
     */
	private MoveType type;
	
	/**
     * Gets the type of move.
     *
     * @return The type of move.
     */ 
	public MoveType getType() {
		return type;
	}
	
	/**
     * The piece captured during the move (if any).
     */
	private Piece piece;
	
	/**
     * Gets the piece captured during the move.
     *
     * @return The captured piece, or {@code null} if no piece was captured.
     */
	public Piece getPiece() {
		return piece;
	}
	
	/**
     * Constructs a new MoveResult with the specified type.
     *
     * @param type The type of move.
     */
	public MoveResult(MoveType type) {
		this(type, null);
	}
	
	/**
     * Constructs a new MoveResult with the specified type and captured piece.
     *
     * @param type  The type of move.
     * @param piece The piece captured during the move.
     */
	public MoveResult(MoveType type, Piece piece) {
		this.type = type;
		this.piece = piece;
	}
}
