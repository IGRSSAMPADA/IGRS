/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.form;

    import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.empmgmt.dto.AssetDTO;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.NomineeDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.PropertyDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;
import com.wipro.igrs.empmgmt.dto.TalentDetailsDTO;
	
	

	
/**
* 
* EmpMgmtForm.java <br>
* EmpMgmtForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmpMgmtForm extends ActionForm {
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7222855628825730376L;
		private TalentDetailsDTO talentDTO=new TalentDetailsDTO();
		private String moduleName;
		
		
		// for bank details
		
		//private FundDTO fundDTO=new FundDTO();
		//private  NomineeDTO nomineeDTO=new NomineeDTO();
//		 for talent
		
		//private ArrayList childList=new ArrayList();
		
		// for bank details
		private  BankDTO bankDTO=new BankDTO();
		private ArrayList fundslist=new ArrayList();
		private ArrayList nomineeslist=new ArrayList();
		
		
		//for department
		
		
		// for Property
		private PropertyDTO propertyDTO=new PropertyDTO();
		private AssetDTO assetDTO=new AssetDTO();
		private ArrayList assestlist=new ArrayList();
		private ArrayList propertylist=new ArrayList();
		//for official info
		private ArrayList  serviceVerificationList=new ArrayList(); 
		private ArrayList documentList=new ArrayList();
		private OfficalInfoDTO officalInfoDTO=new OfficalInfoDTO ();
		
		private String formName;
		private String actionName;
		
		/**
		 * 
		 */
		public EmpMgmtForm() {
			
			
		}

		/**
		 * @return
		 */
		public String getActionName() {
			return actionName;
		}

		/**
		 * @param actionName
		 */
		public void setActionName(String actionName) {
			this.actionName = actionName;
		}

		

		/**
		 * @return
		 */
		public String getFormName() {
			return formName;
		}

		/**
		 * @param formName
		 */
		public void setFormName(String formName) {
			this.formName = formName;
		}

		
		
		/**
		 * @return
		 */
		public TalentDetailsDTO getTalentDTO() {
			return talentDTO;
		}

		/**
		 * @param talentDTO
		 */
		public void setTalentDTO(TalentDetailsDTO talentDTO) {
			this.talentDTO = talentDTO;
		}

		

		/**
		 * @return
		 */
		public BankDTO getBankDTO() {
			return bankDTO;
		}

		/**
		 * @param bankDTO
		 */
		public void setBankDTO(BankDTO bankDTO) {
			this.bankDTO = bankDTO;
		}
		
		//for bank details
		
		/**
		 * @param index
		 * @param value
		 */
		public void setFundArrDTO(int index, FundDTO value) 
		{
			
			for(; index >= fundslist.size(); 
			fundslist.add(new FundDTO()));
			fundslist.add(index,value);
		}
		
		/**
		 * @param index
		 * @return
		 */
		public FundDTO getFundArrDTO(int index) 
		{
			for(; index >= fundslist.size(); 
			fundslist.add(new FundDTO()));
			return (FundDTO)fundslist.get(index);
		}
		
		/**
		 * @param index
		 * @param value
		 */
		public void setNomineeArrDTO(int index, NomineeDTO value) 
		{
			
			for(; index >= fundslist.size();fundslist.add(new NomineeDTO()));
			fundslist.add(index,value);
		}
		
		/**
		 * @param index
		 * @return
		 */
		public NomineeDTO getNomineeArrDTO(int index) 
		{
			for(; index >= nomineeslist.size();nomineeslist.add(new NomineeDTO()));
			return (NomineeDTO)nomineeslist.get(index);
		}
		


		/*public FundDTO getFundDTO() {
			return fundDTO;
		}

		public void setFundDTO(FundDTO fundDTO) {
			this.fundDTO = fundDTO;
		}

		

		public NomineeDTO getNomineeDTO() {
			return nomineeDTO;
		}

		public void setNomineeDTO(NomineeDTO nomineeDTO) {
			this.nomineeDTO = nomineeDTO;
		}

*/
		/**
		 * @return
		 */
		public ArrayList getNomineeslist() {
			return nomineeslist;
		}

		/**
		 * @param nomineeslist
		 */
		public void setNomineeslist(ArrayList nomineeslist) {
			this.nomineeslist = nomineeslist;
		}
		/**
		 * @return
		 */
		public ArrayList getFundslist() {
			return fundslist;
		}

		/**
		 * @param fundslist
		 */
		public void setFundslist(ArrayList fundslist) {
			this.fundslist = fundslist;
		}

		
		/**
		 * @param index
		 * @param value
		 */
		public void setPropertyArrDTO(int index ,PropertyDTO value){
			for(;index>=propertylist.size();propertylist.add(new PropertyDTO()));
			
			propertylist.add(index,value);
			
		}
		/**
		 * @param index
		 * @return
		 */
		public PropertyDTO getPropertyArrDTO(int index){
			for(;index>=propertylist.size();propertylist.add(new PropertyDTO()));
			return (PropertyDTO)propertylist.get(index);
		}
		
		
		
		/**
		 * @param index
		 * @param value
		 */
		public void setAssestArrDTO(int index ,AssetDTO value){
			for(;index>=assestlist.size();assestlist.add(new AssetDTO()));
			
			assestlist.add(index,value);
			
		}
		/**
		 * @param index
		 * @return
		 */
		public AssetDTO getAssestArrDTO(int index){
			for(;index>=assestlist.size();assestlist.add(new AssetDTO()));
			return (AssetDTO)assestlist.get(index);
		}

		/**
		 * @return
		 */
		public ArrayList getAssestlist() {
			return assestlist;
		}

		/**
		 * @param assestlist
		 */
		public void setAssestlist(ArrayList assestlist) {
			this.assestlist = assestlist;
		}

		/**
		 * @return
		 */
		public AssetDTO getAssetDTO() {
			return assetDTO;
		}

		/**
		 * @param assetDTO
		 */
		public void setAssetDTO(AssetDTO assetDTO) {
			this.assetDTO = assetDTO;
		}

		/**
		 * @return
		 */
		public PropertyDTO getPropertyDTO() {
			return propertyDTO;
		}

		/**
		 * @param propertyDTO
		 */
		public void setPropertyDTO(PropertyDTO propertyDTO) {
			this.propertyDTO = propertyDTO;
		}

		/**
		 * @return
		 */
		public ArrayList getPropertylist() {
			return propertylist;
		}

		/**
		 * @param propertylist
		 */
		public void setPropertylist(ArrayList propertylist) {
			this.propertylist = propertylist;
		}

		/**
		 * @return
		 */
		public String getModuleName() {
			return moduleName;
		}

		/**
		 * @param moduleName
		 */
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		
		/**
		 * @return
		 */
		public OfficalInfoDTO getOfficalInfoDTO() {
			return officalInfoDTO;
		}

		/**
		 * @param officalInfoDTO
		 */
		public void setOfficalInfoDTO(OfficalInfoDTO officalInfoDTO) {
			this.officalInfoDTO = officalInfoDTO;
		} 
		
		/**
		 * @param index
		 * @param value
		 */
		public void setServiceVerificationDTO(int index ,ServiceVerificationDTO value){
			for(;index>=serviceVerificationList.size();serviceVerificationList.add(new ServiceVerificationDTO()));
			
			serviceVerificationList.add(index,value);
			
		}
		/**
		 * @param index
		 * @return
		 */
		public ServiceVerificationDTO getServiceVerificationDTO(int index){
			for(;index>=serviceVerificationList.size();serviceVerificationList.add(new ServiceVerificationDTO()  ));
			return (ServiceVerificationDTO)serviceVerificationList.get(index);
		}
		
		/**
		 * @param index
		 * @param value
		 */
		public void setServiceVerifyDocumentDTO(int index ,ServiceVerificationDTO value){
			for(;index>=documentList.size();documentList.add(new ServiceVerificationDTO()));
			
			documentList.add(index,value);
			
		}
		/**
		 * @param index
		 * @return
		 */
		public ServiceVerificationDTO getServiceVerifyDocumentDTO(int index){
			for(;index>=documentList.size();documentList.add(new ServiceVerificationDTO()));
			return (ServiceVerificationDTO)documentList.get(index);
		}
		
		
		/**
		 * @return
		 */
		public ArrayList getServiceVerificationList() {
			return serviceVerificationList;
		}

		/**
		 * @param serviceVerificationList
		 */
		public void setServiceVerificationList(ArrayList serviceVerificationList) {
			this.serviceVerificationList = serviceVerificationList;
		}

		/**
		 * @return
		 */
		public ArrayList getDocumentList() {
			return documentList;
		}

		/**
		 * @param documentList
		 */
		public void setDocumentList(ArrayList documentList) {
			this.documentList = documentList;
		}

		

		


	}
		