package com.prowess.bms.common;

import java.util.Scanner;

import com.prowess.bms.action.DeleteBook;
import com.prowess.bms.action.GetListOfBooks;
import com.prowess.bms.action.RegisterBookAction;
import com.prowess.bms.action.SearchAction;
import com.prowess.bms.action.UpdateBook;

public class CommonApp {

	public void displayUI() {

		int menuChoice;
		Scanner sc = new Scanner(System.in);
		boolean menuRepeat = true;
		while (menuRepeat) {
			System.out.println("BOOK MANAGEMENT SYSTEM - PROWESS");
			System.out.println("\n1. Register Book");
			System.out.println("2. Get Books List");
			System.out.println("3. Search Book(s)");
			System.out.println("4. Delete Book");
			System.out.println("5. Update Book Price");
			System.out.print("Enter Your Choice: ");
			menuChoice = sc.nextInt();
			switch (menuChoice) {
			case 1:
				RegisterBookAction registerBookAction = new RegisterBookAction();
				registerBookAction.registerBook();
				break;
			case 2:
				GetListOfBooks getBooks = new GetListOfBooks();
				getBooks.getBooksList();
				break;
			case 3:
				SearchAction searchAction = new SearchAction();
				searchAction.searchMenu();
				break;
			case 4:
				DeleteBook deleteBook = new DeleteBook();
				deleteBook.deleteBookMenu();
				break;
			case 5:
				UpdateBook updateBook = new UpdateBook();
				updateBook.updateBookMenu();
				break;
			default:
				System.out.println("Invalid Choice");
			}
			System.out.print("\nDo you wish to continue (y/n): ");
			if (!sc.next().equals("y")) {
				System.out.println("\n---THANK YOU---");
				menuRepeat = false;
			}

		}
	}

	public static void main(String[] args) {
		CommonApp commonApp = new CommonApp();
		commonApp.displayUI();
	}
}
