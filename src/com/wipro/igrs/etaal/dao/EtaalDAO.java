package com.wipro.igrs.etaal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.etaal.dto.ResponseDTO;
import com.wipro.igrs.etaal.sql.EtaalSQL;

public class EtaalDAO {
	private Logger	logger	= (Logger) Logger.getLogger(EtaalDAO.class);

	@SuppressWarnings("deprecation")
	public String getEstampCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.ESTAMP_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getEstampCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getEstampCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getRegistrationCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.REGISTRATION_INITIATION_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getRegistrationCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getRegistrationCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getOnlinePaymentEstampCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.ONLINE_PAYMENT_ESTAMP_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getOnlinePaymentEstampCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getOnlinePaymentEstampCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getOnlinePaymentRegistartionFeeCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.ONLINE_PAYMENT_REGISTRATION_FEE_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getOnlinePaymentRegistartionFeeCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getOnlinePaymentRegistartionFeeCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getDeedPreparationCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.ONLINE_PREPARATION_OF_DEEDS_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getDeedPreparationCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getDeedPreparationCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getEregistrationBySubRegistrarsCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.EREGISTRATION_BY_SUB_REGISTRARS_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getEregistrationBySubRegistrarsCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getEregistrationBySubRegistrarsCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getOnlineDocSearchCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.ONLINE_SEARCH_EREGISTERED_DOC_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getOnlineDocSearchCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getOnlineDocSearchCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getRevenueFromStampDuty(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.REVENUE_GENERATION_STAMP_DUTY);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getRevenueFromStampDuty():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getRevenueFromStampDuty() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getRevenueFromRegistration(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.REVENUE_GENERATION_REGISTRATION_FEE);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getRevenueFromRegistration():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getRevenueFromRegistration() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getPropertyValuationCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.PROPERTY_VALUATION_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getPropertyValuationCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getPropertyValuationCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getDutyCalculationCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.DUTY_CALCULATION_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getDutyCalculationCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getDutyCalculationCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getDownloadedEregisteredDocCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.DOWNLOAD_EREGISTERED_DOC_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getDownloadedEregisteredDocCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getDownloadedEregisteredDocCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getPINGenerationCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.PIN_GENERATION_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getPINGenerationCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getPINGenerationCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getActiveServiceProviderCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.ACTIVE_SERVICE_PROVIDER_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getActiveServiceProviderCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getActiveServiceProviderCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public String getSlotBookCount(String date) {
		DBUtility dbUtil = null;
		String count = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(EtaalSQL.SLOT_BOOK_COUNT);
			count = dbUtil.executeQry(new String[] { date });
		} catch (Exception e) {
			logger.error("Exception in getSlotBookCount():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		logger.info("getSlotBookCount() in EtaalDAO : " + count);
		return count;
	}

	@SuppressWarnings("deprecation")
	public void insertEtransactionData(String urlDate, List<ResponseDTO> etranxResponse) {
		DBUtility dbUtil = null;
		try {
			if (etranxResponse != null) {
				dbUtil = new DBUtility();
				PreparedStatement pst = dbUtil.returnPreparedStatement(EtaalSQL.INSERT_ETRANSACTION_DATA);
				for (ResponseDTO response : etranxResponse) {
					pst.setString(1, response.getServiceCode());
					pst.setString(2, response.getServiceCount());
					pst.setString(3, urlDate);
					pst.addBatch();
				}
				pst.executeBatch();
				logger.info("Etransaction data inserted in ");
			}
		} catch (Exception e) {
			logger.error("Exception in insertEtransactionData():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<ResponseDTO> checkEtaalDataForSameDate(String urlDate) {
		DBUtility dbUtil = null;
		ArrayList<ResponseDTO> eTaalDetails = new ArrayList<ResponseDTO>();
		ResponseDTO etaalResponse = null;
		try {
			dbUtil = new DBUtility();
			PreparedStatement pst = dbUtil.returnPreparedStatement(EtaalSQL.GET_ETRANSACTION_DATA);
			pst.setString(1, urlDate);
			ResultSet result = pst.executeQuery();
			while (result.next()) {
				etaalResponse = new ResponseDTO();
				etaalResponse.setServiceCode(result.getString("SERVICE_CODE"));
				etaalResponse.setServiceCount(result.getString("SERVICE_COUNT"));
				eTaalDetails.add(etaalResponse);
			}
		} catch (Exception e) {
			logger.error("Exception in checkEtaalDataForSameDate():" + e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		return eTaalDetails;
	}
}
