package com.capgemini.workshop.tictactoe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TicTacToeGame {
	private static final char EMPTY = ' ';
	private static final char CROSS = 'X';
	private static final char ROUND = 'O';

	private char playerSymbol;
	private char computerSymbol;
	private char[] board;
	private int moveCount;

	TicTacToeGame() {
		board = new char[10];
		Arrays.fill(board, EMPTY);
		this.moveCount = 0;
	}

	private TicTacToeGame(char[] board) {
		this.board = board;
		this.moveCount = 0;
	}

	public TicTacToeGame getCopy() {
		return new TicTacToeGame(this.board);
	}

	public char getPlayerSymbol() {
		return playerSymbol;
	}

	public char getComputerSymbol() {
		return computerSymbol;
	}

	public boolean isOver() {
		return moveCount == 9;
	}

	/**
	 * @param row Row position on the board
	 * @param col Column position on the board
	 * @return 0-based array index
	 */
	private static int getIndex(int row, int col) {
		return 3 * (row - 1) + (col - 1);
	}

	/**
	 * For choosing player symbol (X or O)
	 * 
	 * @param playerSymbol
	 */
	public void choosePlayerSymbol(char playerSymbol) {
		if (playerSymbol == CROSS) {
			this.playerSymbol = CROSS;
			this.computerSymbol = ROUND;
		} else if (playerSymbol == ROUND) {
			this.playerSymbol = ROUND;
			this.computerSymbol = CROSS;
		} else
			System.out.println("Invalid Symbol");
	}

	/**
	 * Check if a position is free or not
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isFree(int row, int col) {
		if (row > 3 || row < 1 || col > 3 || col < 1)
			System.out.println("Invalid position!");

		if (board[getIndex(row, col)] == EMPTY)
			return true;
		return false;
	}

	private boolean isFree(int position) {
		if (position < 1 || position > 9)
			System.out.println("Invalid position!");
		if (board[position - 1] == EMPTY)
			return true;
		return false;
	}

	/**
	 * Player move
	 * 
	 * @param row Row position to move
	 * @param col Column position to move
	 */
	public void playerMove(int row, int col) {
		if (row > 3 || row < 1 || col > 3 || col < 1) {
			System.out.println("Invalid move!");
			return;
		}

		if (isFree(row, col)) {
			board[getIndex(row, col)] = playerSymbol;
			System.out.println("After player move");
			moveCount++;
			showBoard();
		} else
			System.out.println("Illegal move!");

	}

	public void playerMove(int position) {
		if (position < 1 || position > 9) {
			System.out.println("Invalid move!");
			return;
		}

		if (isFree(position)) {
			board[position - 1] = playerSymbol;
			System.out.println("After player move");
			moveCount++;
			showBoard();
		} else
			System.out.println("Illegal move!");
	}

	/**
	 * Prints the board
	 */
	public void showBoard() {
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				if (j == 3)
					System.out.print(board[getIndex(i, j)]);
				else
					System.out.print(board[getIndex(i, j)] + " | ");

			}
			System.out.println("");
			if (i != 3)
				System.out.println("---------");
		}
	}

	/**
	 * Toss to select who plays first
	 */
	private int toss() {
		int tossResult = (int) Math.floor(Math.random() * 10) % 2;
		if (tossResult == 1) {
			System.out.println("User plays first");
			choosePlayerSymbol(CROSS);
			return 1;
		} else {
			System.out.println("Computer plays first");
			choosePlayerSymbol(ROUND);
			return 0;
		}

	}

	/**
	 * Shows the winning condition
	 * 
	 * @param symbol user or computer symbol to check winning condition
	 * @return zero if no winning condition is reached
	 */
	private int winningPosition(char symbol) {
		// horizontal
		if (board[getIndex(1, 1)] == symbol && board[getIndex(1, 2)] == symbol && board[getIndex(1, 3)] == symbol)
			return 1;
		if (board[getIndex(2, 1)] == symbol && board[getIndex(2, 2)] == symbol && board[getIndex(2, 3)] == symbol)
			return 2;
		if (board[getIndex(3, 1)] == symbol && board[getIndex(3, 2)] == symbol && board[getIndex(3, 3)] == symbol)
			return 3;

		// vertical
		if (board[getIndex(1, 1)] == symbol && board[getIndex(2, 1)] == symbol && board[getIndex(3, 1)] == symbol)
			return 4;
		if (board[getIndex(1, 2)] == symbol && board[getIndex(2, 2)] == symbol && board[getIndex(3, 2)] == symbol)
			return 5;
		if (board[getIndex(1, 3)] == symbol && board[getIndex(2, 3)] == symbol && board[getIndex(3, 3)] == symbol)
			return 6;

		// diagonal
		if (board[getIndex(1, 1)] == symbol && board[getIndex(2, 2)] == symbol && board[getIndex(3, 3)] == symbol)
			return 7;

		// off diagonal
		if (board[getIndex(1, 3)] == symbol && board[getIndex(2, 2)] == symbol && board[getIndex(3, 1)] == symbol)
			return 8;

		return 0;

	}

	/**
	 * Random computer moves
	 */
	public void computerMove() {
		Random random = new Random();
		int move = random.nextInt(9) + 1;
		while (!isFree(move))
			move = random.nextInt(9) + 1;
		board[move - 1] = computerSymbol;
		System.out.println("After computer move");
		moveCount++;
		showBoard();
	}

	public int nextWinnigMovePosition(char[] board, char character) {
		TicTacToeGame temp = this.getCopy();

		temp.choosePlayerSymbol(this.playerSymbol);
		int winningPosition = -1;
		for (int position = 1; position <= 9; position++) {
			if (temp.isFree(position)) {
				temp.playerMove(position);
				if (temp.hasPlayerWon()) {
					winningPosition = temp.winningPosition(this.playerSymbol);
					break;
				}
			}
		}

		return winningPosition;
	}

	public int getEmptyCorner() {
		List<Integer> corners = Arrays.asList(1, 3, 7, 9);
		List<Integer> emptyCorners = corners.stream().filter(position -> this.isFree(position))
				.collect(Collectors.toList());
				
		if (emptyCorners.isEmpty())
			return -1;

		Collections.shuffle(emptyCorners);
		return emptyCorners.get(0);
	}

	public boolean hasPlayerWon() {
		return winningPosition(playerSymbol) != 0;
	}

	public boolean hasComputerWon() {
		return winningPosition(computerSymbol) != 0;
	}

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		Scanner sc = new Scanner(System.in);
		int whoPlaysFirst = game.toss();
		System.out.println("Player symbol is: " + game.getPlayerSymbol());
		System.out.println("initial:");
		game.showBoard();
		if (whoPlaysFirst == 0)
			game.computerMove();

		while (!game.isOver()) {
			if (!game.hasComputerWon()) {
				System.out.print("Enter position to play[1-9]: ");
				game.playerMove(sc.nextInt());
			}
			if (!game.hasPlayerWon())
				game.computerMove();
		}
		sc.close();
		if (!(game.hasPlayerWon() && game.hasComputerWon())) {
			System.out.println("DRAW!");
			return;
		}

		System.out.println(game.hasPlayerWon() ? "YOU WON!" : "COMPUTER WON");

	}
}
