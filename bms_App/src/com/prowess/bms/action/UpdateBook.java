package com.prowess.bms.action;

import java.util.Scanner;

import com.prowess.bms.dao.BookDAO;
import com.prowess.bms.interfaces.BookInterface;

public class UpdateBook {

	public void updateBookMenu() {
		Scanner scanner = new Scanner(System.in);
		BookInterface bookInterface = new BookDAO();
		System.out.println("Book Price Updation...");
		System.out.println("Enter Book ID: ");
		int bookID = scanner.nextInt();
		if (!bookInterface.isRecordExists(bookID)) {
			System.out.println("Book ID " + bookID + " does not exists.. Try Again");
		} else {
			System.out.println("Enter new Price of the Book:");
			float bookPrice = scanner.nextFloat();
			int updateAck = bookInterface.updateBookPrice(bookID, bookPrice);
			if (updateAck == 1)
				System.out.println("Price Updated for Book ID: " + bookID + " with new price: " + bookPrice);
			else
				System.out.println("Price Updation failed for Book with Book ID: " + bookID);
		}
	}
}
