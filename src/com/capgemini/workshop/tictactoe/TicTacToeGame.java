package com.capgemini.workshop.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	private static final char EMPTY = ' ';
	private static final char CROSS = 'X';
	private static final char ROUND = 'O';

	private char playerSymbol;
	private char computerSymbol;
	private char[] board;

	TicTacToeGame() {
		board = new char[10];
		Arrays.fill(board, EMPTY);

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
	 * @return The player symbol character
	 */
	public char getPlayerSymbol() {
		return playerSymbol;
	}

	public void move(int row, int col) {
		if (row > 3 || row < 1 || col > 3 || col < 1)
			System.out.println("Invalid move!");

		if (board[getIndex(row, col)] == EMPTY) {
			board[getIndex(row, col)] = playerSymbol;
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

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose a symbol (X or O): ");
		String symbol = sc.next();
		game.choosePlayerSymbol(symbol.charAt(0));
		System.out.println("Player has chosen: " + game.getPlayerSymbol());
		System.out.println("initial:");
		game.showBoard();
		System.out.println("Enter row, column to move: ");
		int row=sc.nextInt();
		int col=sc.nextInt();
		game.move(row, col);
		System.out.println("after 1st move: ");
		game.showBoard();
		sc.close();

	}
}
