package com.prowess.bms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.prowess.bms.common.CC;
import com.prowess.bms.interfaces.BookInterface;
import com.prowess.bms.vo.BookVO;

public class BookDAO implements BookInterface {

	@Override
	public boolean isRecordExists(int bookID) {
		boolean recordExists = false;
		try {
			Connection connection = CC.getMySqlDBConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CC.GET_BOOKS_BY_ID);
			preparedStatement.setInt(1, bookID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				recordExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordExists;
	}

	@Override
	public int registerBookDetails(BookVO bookVO) {
		Connection connection = CC.getMySqlDBConnection();
		int recordCount = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(CC.INSERT_QUERY);
			preparedStatement.setInt(1, bookVO.getBookId());
			preparedStatement.setString(2, bookVO.getBookName());
			preparedStatement.setInt(3, bookVO.getPublishYear());
			preparedStatement.setString(4, bookVO.getBookAuthor());
			preparedStatement.setFloat(5, bookVO.getBookPrice());
			preparedStatement.setString(6, bookVO.getCity());
			preparedStatement.setString(7, bookVO.getGenere());
			preparedStatement.setBoolean(8, bookVO.getInStock());
			preparedStatement.setString(9, bookVO.getIsActive());
			preparedStatement.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(11, bookVO.getCreatedBy());
			preparedStatement.setBlob(12, new FileInputStream(bookVO.getFilePath()));
			recordCount = preparedStatement.executeUpdate();

		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return recordCount;
	}

	@Override
	public ArrayList<BookVO> getListOfBooks() {
		ArrayList<BookVO> arrayList = new ArrayList<BookVO>();
		int fileNameCounter = 0;
		Connection connection = CC.getMySqlDBConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(CC.GET_ALL_RECORDS_QUERY);
			while (resultSet.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setBookId(resultSet.getInt(1));
				bookVO.setBookName(resultSet.getString(2));
				bookVO.setPublishYear(resultSet.getInt(3));
				bookVO.setBookAuthor(resultSet.getString(4));
				bookVO.setBookPrice(resultSet.getFloat(5));
				bookVO.setCity(resultSet.getString(6));
				bookVO.setGenere(resultSet.getString(7));
				bookVO.setInStock(resultSet.getBoolean(8));
				bookVO.setIsActive(resultSet.getString(9));
				bookVO.setCreated(resultSet.getTimestamp(10));
				bookVO.setCreatedBy(resultSet.getString(11));

				String filePath = "D:/book_images_after_select/book" + (++fileNameCounter) + ".jpg";
				OutputStream out = new FileOutputStream(filePath);
				Blob blob = resultSet.getBlob(12);
				out.write(blob.getBytes(1, (int) blob.length()));
				out.close();
				arrayList.add(bookVO);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	@Override
	public ArrayList<BookVO> getBooksListByColumn(int searchChoice, String enteredInput) {
		ArrayList<BookVO> arrayList = new ArrayList<>();
		String GET_BOOKS_SQL = CC.GET_BOOKS_BY_COLUMN;
		PreparedStatement preparedStatement;
		Connection connection = CC.getMySqlDBConnection();
		switch (searchChoice) {
		case 1:
			GET_BOOKS_SQL += "BOOK_ID = ?";
			break;
		case 2:
			GET_BOOKS_SQL += "AUTHOR = ?";
			break;
		case 3:
			GET_BOOKS_SQL += "IS_ACTIVE = ?";
			break;
		case 4:
			GET_BOOKS_SQL += "IN_STOCK = ?";
			break;
		case 5:
			GET_BOOKS_SQL += "CITY = ?";
			break;
		case 7:
			GET_BOOKS_SQL += "BOOK_GENERE = ?";
		}
		try {
			preparedStatement = connection.prepareStatement(GET_BOOKS_SQL);
			if (searchChoice == 4)
				preparedStatement.setBoolean(1, Boolean.parseBoolean(enteredInput));
			else
				preparedStatement.setString(1, enteredInput);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setBookId(resultSet.getInt(1));
				bookVO.setBookName(resultSet.getString(2));
				bookVO.setPublishYear(resultSet.getInt(3));
				bookVO.setBookAuthor(resultSet.getString(4));
				bookVO.setBookPrice(resultSet.getFloat(5));
				bookVO.setCity(resultSet.getString(6));
				bookVO.setGenere(resultSet.getString(7));
				bookVO.setInStock(resultSet.getBoolean(8));
				bookVO.setIsActive(resultSet.getString(9));
				bookVO.setCreated(resultSet.getTimestamp(10));
				bookVO.setCreatedBy(resultSet.getString(11));
				arrayList.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public ArrayList<BookVO> getBooksListByPriceInBetween(float lowerRange, float higherRange) {
		ArrayList<BookVO> arrayList = new ArrayList<>();
		Connection connection = CC.getMySqlDBConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(CC.PRICE_IN_BETWEEN_QUERY);
			preparedStatement.setFloat(1, lowerRange);
			preparedStatement.setFloat(2, higherRange);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setBookId(resultSet.getInt(1));
				bookVO.setBookName(resultSet.getString(2));
				bookVO.setPublishYear(resultSet.getInt(3));
				bookVO.setBookAuthor(resultSet.getString(4));
				bookVO.setBookPrice(resultSet.getFloat(5));
				bookVO.setCity(resultSet.getString(6));
				bookVO.setGenere(resultSet.getString(7));
				bookVO.setInStock(resultSet.getBoolean(8));
				bookVO.setIsActive(resultSet.getString(9));
				bookVO.setCreated(resultSet.getTimestamp(10));
				bookVO.setCreatedBy(resultSet.getString(11));
				arrayList.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	@Override
	public int deleteBookByID(int bookId) {
		Connection connection = CC.getMySqlDBConnection();
		int deleteAck = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(CC.DELETE_QUERY);
			preparedStatement.setInt(1, bookId);
			deleteAck = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deleteAck;
	}

	@Override
	public int updateBookPrice(int bookID, float bookPrice) {
		int updateAck = 0;
		Connection connection = CC.getMySqlDBConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(CC.UPDATE_PRICE_QUERY);
			preparedStatement.setFloat(1, bookPrice);
			preparedStatement.setInt(2, bookID);
			updateAck = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateAck;
	}

	

}
