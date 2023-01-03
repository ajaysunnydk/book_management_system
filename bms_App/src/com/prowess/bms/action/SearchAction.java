package com.prowess.bms.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import com.prowess.bms.dao.BookDAO;
import com.prowess.bms.interfaces.BookInterface;
import com.prowess.bms.vo.BookVO;

public class SearchAction {
	public void searchMenu() {
		BookInterface bookInterface = new BookDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Searching---");
		System.out.println("1. Search Book By Id");
		System.out.println("2. Search Book By Author");
		System.out.println("3. Search Book By Active");
		System.out.println("4. Search Book By Its Stock");
		System.out.println("5. Search Book By City");
		System.out.println("6. Search Book By Price In Between");
		System.out.println("7. Search Book By Genere");
		System.out.println("\nEnter your choice: ");
		int searchChoice = scanner.nextInt();
		String userInputForSearch;
		ArrayList<BookVO> booksList;
		switch (searchChoice) {
		case 1: {
			System.out.println("Enter Book Id: ");
			userInputForSearch = scanner.next();
			booksList = bookInterface.getBooksListByColumn(searchChoice, userInputForSearch);
			if (booksList.size() == 0) {
				System.out.println("Book ID " + userInputForSearch + " doesnot exists.. Try Again");
			} else
				printListOfBooks(booksList);
			break;
		}
		case 2: {
			System.out.println("Enter Author Name: ");
			userInputForSearch = scanner.next();
			booksList = bookInterface.getBooksListByColumn(searchChoice, userInputForSearch);
			if (booksList.size() == 0) {
				System.out.println("Book with Author " + userInputForSearch + " does not exists!!!");
			} else
				printListOfBooks(booksList);
			break;
		}

		case 3: {
			System.out.println("Enter Book Status - Active (y/n):");
			userInputForSearch = scanner.next();
			booksList = bookInterface.getBooksListByColumn(searchChoice, userInputForSearch);
			if (booksList.size() == 0) {
				System.out.println("Book with Active Status '" + userInputForSearch + "' does not exists!!!");
			} else
				printListOfBooks(booksList);
			break;
		}

		case 4: {
			System.out.println("Enter Book Stock Availability - (true/false):");
			userInputForSearch = scanner.next();
			booksList = bookInterface.getBooksListByColumn(searchChoice, userInputForSearch);
			if (booksList.size() == 0) {
				System.out.println("All the books are Out Of Stock!!!");
			} else
				printListOfBooks(booksList);
			break;
		}

		case 5: {
			System.out.println("Enter City: ");
			userInputForSearch = scanner.next();
			booksList = bookInterface.getBooksListByColumn(searchChoice, userInputForSearch);
			if (booksList.size() == 0) {
				System.out.println("Book with City: " + userInputForSearch + " does not exists!!!");
			} else
				printListOfBooks(booksList);
			break;
		}

		case 6: {
			System.out.println("Enter starting price: ");
			float lowerRange = scanner.nextFloat();
			System.out.println("Enter highest price:");
			float higherRange = scanner.nextFloat();
			booksList = bookInterface.getBooksListByPriceInBetween(lowerRange, higherRange);
			if (booksList.size() == 0) {
				System.out.println("No Book exists under given price range");
			} else {
				printListOfBooks(booksList);
				System.out.println("Do you want to sort Books By Price? (y/n)");
				String sortChoice = scanner.next();
				if(sortChoice.equals("y")) {
					Collections.sort(booksList,new PriceComparator()) ;
					printListOfBooks(booksList);
				}
			}
			break;
		}

		case 7: {
			System.out.println("Enter Genere: ");
			userInputForSearch = scanner.next();
			booksList = bookInterface.getBooksListByColumn(searchChoice, userInputForSearch);
			if (booksList.size() == 0) {
				System.out.println("Book with Genere " + userInputForSearch + " does not exists!!!");
			} else
				printListOfBooks(booksList);
			break;
		}
		default:
			System.out.println("Invalid Choice");
			break;
		}

	}


	public void printListOfBooks(ArrayList<BookVO> booksList) {
		Iterator<BookVO> iterator = booksList.iterator();
		while (iterator.hasNext()) {
			BookVO bookVO = (BookVO) iterator.next();
			System.out.println(bookVO.getBookId() + " -- " + bookVO.getBookName() + " -- " + bookVO.getPublishYear()
					+ " -- " + bookVO.getBookAuthor() + " -- " + bookVO.getBookPrice() + " -- " + bookVO.getCity()
					+ " -- " + bookVO.getGenere() + " -- " + bookVO.getInStock() + " -- " + bookVO.getIsActive()
					+ " -- " + bookVO.getCreated() + " -- " + bookVO.getCreatedBy());
		}
	}

}
