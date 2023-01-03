package com.prowess.bms.action;

import java.util.Comparator;

import com.prowess.bms.vo.BookVO;

public class PriceComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		BookVO bookVO1 = (BookVO) o1;
		BookVO bookVO2 = (BookVO) o2;

		if (bookVO1.getBookPrice() > bookVO2.getBookPrice())
			return 1;
		else if (bookVO1.getBookPrice() < bookVO2.getBookPrice())
			return -1;
		else
			return 0;
	}

}
