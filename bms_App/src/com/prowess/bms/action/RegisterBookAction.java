package com.prowess.bms.action;

import java.util.Scanner;

import com.prowess.bms.common.CC;
import com.prowess.bms.dao.BookDAO;
import com.prowess.bms.interfaces.BookInterface;
import com.prowess.bms.vo.BookVO;

public class RegisterBookAction {

	public void registerBook() {
		Scanner scanner = new Scanner(System.in);

		BookVO bookVO = new BookVO();
		BookInterface bookInterface = new BookDAO();
		System.out.println("Enter Book ID: ");
		int bookID = scanner.nextInt();
		if (bookInterface.isRecordExists(bookID)) {
			System.out.println("Book ID " + bookID + " already exists.. Try Again");
		} else {
			bookVO.setBookId(bookID);
			System.out.println("Enter Book Name:");
			bookVO.setBookName(scanner.next());
			System.out.println("\nEnter Published Year:");
			bookVO.setPublishYear(Integer.parseInt(scanner.next()));
			System.out.println("Enter Author Name:");
			bookVO.setBookAuthor(scanner.next());
			System.out.println("Enter Book Price:");
			bookVO.setBookPrice(Float.parseFloat(scanner.next()));
			System.out.println("Enter Book City:");
			bookVO.setCity(scanner.next());
			System.out.println("Enter Book Genere:");
			bookVO.setGenere(scanner.next());
			System.out.println("Book is in Stock(true/false): ");
			bookVO.setInStock(Boolean.parseBoolean(scanner.next()));
			System.out.println("Book is now Active(y/n): ");
			bookVO.setIsActive(scanner.next());
			bookVO.setCreatedBy(CC.createdUser);
			System.out.println("Enter File Path of Book Image: ");
			bookVO.setFilePath(scanner.next());
			int recordCount = bookInterface.registerBookDetails(bookVO);
			if (recordCount != 0)
				System.out.println(recordCount + " Book Registered successfully with Book ID: " + bookID);
			else
				System.out.println("Book Registration Failed!!! with Book ID: " + bookID);
		}
	}

}
