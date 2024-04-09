package application;

public enum PieceType {

	RED(1), BLUE(-1);
	
	int moveDir = 0;
	
	PieceType(int moveDir){
	    this.moveDir = moveDir;
	}
	
}
