package com.prowess.bms.action;

import java.util.Scanner;

import com.prowess.bms.dao.BookDAO;
import com.prowess.bms.interfaces.BookInterface;

public class DeleteBook {

	public void deleteBookMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Book Deletion");
		BookInterface bookInterface = new BookDAO();
		System.out.println("Enter Book ID to delete: ");
		int bookId = scanner.nextInt();
		if (bookInterface.isRecordExists(bookId)) {
			int deleteAck = bookInterface.deleteBookByID(bookId);
			if (deleteAck == 1)
				System.out.println("Book with Book ID: " + bookId + " deleted Successfully ");
			else
				System.out.println("Can't Delete Book with Book ID: " + bookId);
		} else
			System.out.println("Book with Book ID: " + bookId + " does not exists");
	}
}
