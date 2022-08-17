package com.wipro.igrs.transactionHistory.BD;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.wipro.igrs.serviceProviderTop.dto.DTO;
import com.wipro.igrs.transactionHistory.DAO.TranHistoryDAO;
import com.wipro.igrs.transactionHistory.DTO.TransactionHistoryDTO;

public class TransactionHistoryBD {

	public String getData(TransactionHistoryDTO dto) {

		String abc = "";
		TranHistoryDAO dao = new TranHistoryDAO();
		dao.getData(dto);

		return abc;
	}

	public String setData(TransactionHistoryDTO dto) {
		String abc = "";
		String docPath = "e:/09/abc/";
		uploadFile(dto.getUploadContent(), dto.getFileName(), docPath);

		return abc;
	}

	private boolean uploadFile(byte[] abc, String fileName, String filePath) {

		try {
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File newFile = new File(filePath, fileName);
			// FileInputStream fis= new FileInputStream(newFile);

			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(abc);
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	//modified by shruti--compilation issue
	public ArrayList getDistrict(String language) {
		TranHistoryDAO dao = new TranHistoryDAO();
		ArrayList alist = dao.getDistrict(language);
		ArrayList list = new ArrayList();

		if (alist != null) {
			for (int i = 0; i < alist.size(); i++) {
				ArrayList lst = (ArrayList) alist.get(i);
				DTO dto = new DTO();
				dto.setDistrictId((String) lst.get(0));
				dto.setDistrict((String) lst.get(1));
				list.add(dto);
			}
		}

		return list;
	}

	public String setStampData(TransactionHistoryDTO dto) {
		String abc = "";
		TranHistoryDAO dao = new TranHistoryDAO();
		abc = dao.setStampData(dto);

		return abc;

	}

	public ArrayList getStampList(TransactionHistoryDTO dto) {
		String abc = "";
		ArrayList finalList = new ArrayList();
		TranHistoryDAO dao = new TranHistoryDAO();
		ArrayList alist = dao.getList(dto);
		ArrayList row;
		for (int i = 0; i < alist.size(); i++) {
			row = (ArrayList) alist.get(i);
			dto.setSrNo(i + 1);
			dto.setDate((String) row.get(0));
			dto.setCodeStamp((String) row.get(1));
			dto.setType((String) row.get(2));
			dto.setAmount((String) row.get(3));

			finalList.add(dto);
			dto = new TransactionHistoryDTO();

		}
		return finalList;
	}

	public ArrayList getStampData(TransactionHistoryDTO dto) {
		TranHistoryDAO dao = new TranHistoryDAO();
		ArrayList finalList = new ArrayList();
		ArrayList alist = dao.getStampData(dto);
		for (int i = 0; i < alist.size(); i++) {
			ArrayList row = (ArrayList) alist.get(i);

			dto.setType((String) row.get(0));
			dto.setFirstName((String) row.get(1));
			dto.setDistrict((String) row.get(2));
			dto.setDate((String) row.get(3));
			dto.setDro((String) row.get(4));
			dto.setSro((String) row.get(5));
			dto.setDenoStamp((String) row.get(6));
			dto.setCodeStamp((String) row.get(7));
			if (row.get(8) != null) {
				dto.setSeriesNo((String) row.get(8));
				dto.setRadio("single");
			} else {
				dto.setSeriesNoF((String) row.get(12));
				dto.setSeriesNoT((String) row.get(13));
				dto.setRadio("multiple");
			}

			dto.setAmount((String) row.get(9));
			dto.setCreatedDate((String) row.get(10));
			dto.setCreatedBy((String) row.get(11));
			finalList.add(dto);

		}

		return alist;
	}
}
