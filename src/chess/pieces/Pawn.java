package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

  private ChessMatch chessMatch;
  
  public Pawn(Board board, Color color, ChessMatch chessMatch) {
    super(board, color);
    this.chessMatch = chessMatch;
    
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position(0, 0);
    
    //WHITE PAWN
    if (getColor() == Color.WHITE) {
      //One move ahead
      p.setValues(position.getRow() - 1, position.getColumn());
      if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }
      //Two moves ahead
      p.setValues(position.getRow() - 2, position.getColumn());
      Position p2 = new Position(position.getRow() -1, position.getColumn());
      if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
        mat[p.getRow()][p.getColumn()] = true;
      }
      
      //Opponent piece on the diagonal 
      p.setValues(position.getRow() - 1, position.getColumn() - 1);
      if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }
      //Opponent piece on the another diagonal 
      p.setValues(position.getRow() - 1, position.getColumn() + 1);
      if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }
      
      //#enPasant movimento especial nas peças brancas.
      if(position.getRow() == 3) {
        Position left = new Position(position.getRow(), position.getColumn() - 1);
        if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
          mat[left.getRow() - 1][left.getColumn()] = true;
        }
        Position right = new Position(position.getRow(), position.getColumn() + 1);
        if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
          mat[right.getRow() - 1][right.getColumn()] = true;
        }
      }
    }
    else { //BLACK Pawn
        //One move ahead
        p.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
          mat[p.getRow()][p.getColumn()] = true;
        }
        //Two moves ahead
        p.setValues(position.getRow() + 2, position.getColumn());
        Position p2 = new Position(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
          mat[p.getRow()][p.getColumn()] = true;
        }
        
        //Opponent piece on the diagonal 
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
          mat[p.getRow()][p.getColumn()] = true;
        }
        //Opponent piece on the another diagonal 
        p.setValues(position.getRow()  + 1, position.getColumn() + 1);
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
          mat[p.getRow()][p.getColumn()] = true;
      }
        
        //#enPasant movimento especial nas peças pretas.
        if(position.getRow() == 4) {
          Position left = new Position(position.getRow(), position.getColumn() - 1);
          if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
            mat[left.getRow() + 1][left.getColumn()] = true;
          }
          Position right = new Position(position.getRow(), position.getColumn() + 1);
          if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
            mat[right.getRow() + 1][right.getColumn()] = true;
          }
        }
    }
    
    return mat;
  }
  
  @Override
  public String toString() {
    return "P";
  }
}
