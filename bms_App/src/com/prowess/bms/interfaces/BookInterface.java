package com.prowess.bms.interfaces;

import java.util.ArrayList;

import com.prowess.bms.vo.BookVO;

public interface BookInterface {

	boolean isRecordExists(int bookID);

	int registerBookDetails(BookVO bookVO);

	ArrayList<BookVO> getListOfBooks();

	ArrayList<BookVO> getBooksListByColumn(int searchChoice, String enteredStatus);

	ArrayList<BookVO> getBooksListByPriceInBetween(float lowerRange, float higherRange);

	int deleteBookByID(int bookId);

	int updateBookPrice(int bookID, float bookPrice);

}
