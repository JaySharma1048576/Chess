------------------------------------------------------------------------
2-Player Chess
------------------------------------------------------------------------

Author: Jay Sharma (JaySharma1048576)
Created: April 2018 - June 2018
Uploaded: May 2021
Playable at: https://gitpod.io/#https://github.com/JaySharma1048576/Chess
Language Used: Java

This is a 2-player chess game made using Java from scratch. The main features of this game are -
1. Whenever you click on a piece, it tells you all the possible legal squares to which the piece can move.
2. It automatically detects checks, checkmates, stalemates and draw.
3. It supports Castling, Pawn Promotion and En-Passant.

The game uses java.awt package to create the graphics and font and add images of the chess pieces, java.awt.event package to handle the mouse click events and the javax.swing package for the JFrame utilities. Each piece is mapped to a unique identification number. There are two arrays board and piece which store the state of the board and the position of all pieces respectively. The isAttacked function checks whether a particular square is attacked by any piece of a particular colour or not. The generateMoves function generates all possible moves that the currently selected piece can make. To make sure that a move is valid, it temporarily makes the move and checks whether the current player's King is under attack or not. If the King is under attack after performing that move, it means that the move is invalid otherwise it is valid. There is also a function to determine if a King is in check or not. If a King is in check and the list of possible moves is empty for every piece then it is a checkmate. If the list of possible moves is empty for every piece and the King is not in check then it is a Stalemate. If there are only two Kings or two Kings and a minor piece (Knight or Bishop) alive then the game is a draw as there is no possible checkmate position in such cases.
