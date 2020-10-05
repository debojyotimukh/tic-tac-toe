package com.capgemini.workshop.tictactoe;

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
		for (int i = 0; i < board.length; i++)
			this.board[i] = EMPTY;
	}

	private static int getIndex(int row, int col) {
		return 3 * (row - 1) + (col - 1);
	}

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

	public char getPlayerSymbol() {
		return playerSymbol;
	}

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose a symbol (X or O): ");
		String symbol = sc.next();
		game.choosePlayerSymbol(symbol.charAt(0));
		System.out.println("Player has chosen: " + game.getPlayerSymbol());
		sc.close();

	}
}
