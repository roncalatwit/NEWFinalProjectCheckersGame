package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The CheckersApp class is the main entry point for the checkers game application.
 * It sets up the game board and starts the JavaFX application.
 * 
 * @author Roncal and Luong
 */
public class CheckersApp extends Application{

	/**
     * Size of each tile on the checkers board.
     */
	public static final int TILE_SIZE = 100;
	
	/**
     * Width of the checkers board.
     */
	public static final int WIDTH = 8;
	
	/**
     * Height of the checkers board.
     */
	public static final int HEIGHT = 8;
	
	/**
     * 2D array representing the checkers board.
     */
	private Tile[][] board = new Tile[WIDTH][HEIGHT];
	
	/**
     * Group containing all tiles on the board.
     */
	private Group tileGroup = new Group();
	
	/**
     * Group containing all pieces on the board.
     */
	private Group pieceGroup = new Group();
	
	/**
     * Creates the content for the application scene.
     *
     * @return The root node containing all graphical elements of the game.
     */
	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
		root.getChildren().addAll(tileGroup, pieceGroup);
		
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				Tile tile = new Tile((x + y)% 2 == 0, x, y);
				board[x][y] = tile;
				
				tileGroup.getChildren().add(tile);
				
				Piece piece = null;
				
				if (y <= 2 && (x + y)% 2 !=0) {
					piece = makePiece(PieceType.RED,x ,y);
				}
				
				if (y >= 5 && (x + y)% 2 !=0) {
					piece = makePiece(PieceType.BLUE,x ,y);
				}
				
				if (piece != null) {
					tile.setPiece(piece);
					pieceGroup.getChildren().add(piece);
				}
				
			}
			
		}
		
		return root;
	}
	
	/**
     * Attempts to move a piece to the specified coordinates.
     *
     * @param piece The piece to move.
     * @param newX  The new X-coordinate for the piece.
     * @param newY  The new Y-coordinate for the piece.
     * @return The result of the move.
     */
	private MoveResult tryMove(Piece piece, int newX, int newY) {
		if(board[newX][newY].hasPiece() || (newX + newY)%2==0) {
			return new MoveResult(MoveType.NONE);
		}
		int x0 = toBoard(piece.getOldX());
		int y0 = toBoard(piece.getOldY());
		
		if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
			return new MoveResult(MoveType.NORMAL);
		} else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {
			int x1 = x0 + (newX - x0)/2;
			int y1 = y0 + (newY -y0)/2;
			
			if(board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
				return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
			}
		}
		return new MoveResult(MoveType.NONE);

	}
	
	/**
     * Converts pixel coordinates to board coordinates.
     *
     * @param pixel The pixel coordinate to convert.
     * @return The board coordinate.
     */
	private int toBoard(double pixel) {
		return (int)(pixel + TILE_SIZE/2)/ TILE_SIZE;
	}
	
	
	 /**
     * Starts the JavaFX application, setting the stage with a scene that contains
     * the checkers game content.
     * 
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set.
     * @throws Exception if an error occurs during application start.
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Checkers");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
     * Creates a new piece of the specified type and adds it to the board.
     *
     * @param type The type of piece to create.
     * @param x    The initial X-coordinate for the piece.
     * @param y    The initial Y-coordinate for the piece.
     * @return The newly created piece.
     */
	private Piece makePiece(PieceType type, int x, int y) {
		Piece piece = new Piece(type, x, y);
		
		piece.setOnMouseReleased(e ->{
			int newX = toBoard(piece.getLayoutX());
			int newY = toBoard(piece.getLayoutY());
			
			MoveResult result = tryMove(piece, newX, newY);
			
			int x0 = toBoard(piece.getOldX());
			int y0 = toBoard(piece.getOldY());
			
			
			switch (result.getType()) {
			case NONE:
				piece.abortMove();
				break;
			case NORMAL:
				
				piece.move(newX, newY);
				board[x0][y0].setPiece(null);
				board[newX][newY].setPiece(piece);
				
				break;
			case KILL:
				
				piece.move(newX, newY);
				board[x0][y0].setPiece(null);
				board[newX][newY].setPiece(piece);
				
				Piece otherPiece = result.getPiece();
				
				board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
				pieceGroup.getChildren().remove(otherPiece);
				
				break;
			
			}

		});
		
		return piece;
	}
	
	/**
     * The main method that launches the checkers game application.
     * 
     * @param args Command line arguments passed to the application.
     */
	public static void main(String[] args) {launch(args);}

}

