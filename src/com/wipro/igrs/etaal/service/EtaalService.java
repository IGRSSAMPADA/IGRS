package com.wipro.igrs.etaal.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.wipro.igrs.etaal.bd.EtaalBD;
import com.wipro.igrs.etaal.dto.EtransactionResponse;

@Path("/eTransactionCount")
public class EtaalService {
	private Logger	logger	= (Logger) Logger.getLogger(EtaalService.class);

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public EtransactionResponse getDataForEtaal(@QueryParam("TransactionDate") String tranxDate, @QueryParam("UserName") String userName,
			@QueryParam("Password") String password) {
		try {
			logger.info("Parameters received from etaal : " + tranxDate + "-" + userName + "-" + password);
			EtaalBD etaalBD = new EtaalBD();
			if (etaalBD.isValidUserCredentials(userName, password)) {
				EtransactionResponse etranxResponse = etaalBD.getDataCount(tranxDate);
				return etranxResponse;
			}
		} catch (Exception ex) {
			logger.error("Exception in EtaalService:" + ex);
		}
		return new EtransactionResponse();
	}
}
