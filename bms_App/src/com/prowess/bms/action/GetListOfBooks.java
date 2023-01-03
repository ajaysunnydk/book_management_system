package com.prowess.bms.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import com.prowess.bms.dao.BookDAO;
import com.prowess.bms.interfaces.BookInterface;
import com.prowess.bms.vo.BookVO;

public class GetListOfBooks {
	public void getBooksList() {
		Scanner scanner = new Scanner(System.in);
		BookInterface bookInterface = new BookDAO();
		SearchAction searchAction = new SearchAction();
		ArrayList<BookVO> booksList = bookInterface.getListOfBooks();
		System.out.println("Do you want to sort Books By Price? (y/n)");
		String sortChoice = scanner.next();
		if (sortChoice.equals("y")) {
			Collections.sort(booksList, new PriceComparator());
			searchAction.printListOfBooks(booksList);
		} else
			searchAction.printListOfBooks(booksList);
	}
}
