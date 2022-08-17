package com.wipro.igrs.payments.constant;

public class PaymentConstant {
    
    // Form Name
    public static final String SRO_PAGE = "SROSearchPage";
    
    
    //Action Name
    public static final String SEARCH_RECEIPT_ACTION = "SEARCH_RECEIPT_ACTION";
    public static final String SEARCH_CHALLAN_RECEIPT_ACTION = "SEARCH_CHALLAN_RECEIPT_ACTION";
    public static final String SEARCH_ONLINE_RECEIPT_ACTION = "SEARCH_ONLINE_RECEIPT_ACTION";
    public static final String NEXT_ACTION = "NEXT_ACTION";
    public static final String RESET = "RESET";
    
    // Error codes
    public static final String ERROR_10001 = "You Do Not Have Sufficient Account Balance To Carry Out This Transaction. Your Account Balance is (INR) : ";
    public static final String ERROR_10002 = "Partial Payment is not allowed/आंशिक भुगतान की अनुमति नहीं है |";
    
    public static final String ERROR_5000 = "Invalid SP License no";

    public static final String ERROR_10025 = "Unable to process your Transaction due to Network Error";

    public static final String ERROR_20000 = "Enter Valid CIN. / मान्य चालान पहचान संख्या दर्ज करें।";
    public static final String ERROR_20001 = "Enter Valid Challan Amount. / मान्य चालान राशि दर्ज करें।";
    public static final String ERROR_20002 = "Kindly Check The Purpose Of The Entered Challan/Scroll Number. / कृपया लिखित चालान/स्क्रॉल संख्या का उद्देश्य जाँचें।";
    public static final String ERROR_20003 = "The Entered Challan/Scroll Number Has Already Been Utilised. / लिखित चालान/स्क्रॉल संख्या पहले से ही इस्तेमाल किया गया है।";
    public static final String ERROR_20004 = "Enter valid Challan Reference number. / मान्य चालान संदर्भ संख्या दर्ज करें।";
    public static final String ERROR_20005 = "Enter Valid CIN. / मान्य चालान पहचान संख्या दर्ज करें।";
    
    public static final String ERROR_40000 = "Enter valid CIN. / मान्य चालान पहचान संख्या दर्ज करें।";
    public static final String ERROR_40001 = "Enter Valid Online Amount. / मान्य ऑनलाइन राशि दर्ज करें।";
    public static final String ERROR_40002 = "Kindly Check The Purpose Of The Entered CIN. / कृपया चालान पहचान संख्या का उद्देश्य जाँचें।";
    public static final String ERROR_40003 = "The Entered CIN Has Already Been Utilised. / चालान पहचान संख्या पहले से ही इस्तेमाल किया गया है।";
    
    
    public static final String ERROR_30000 = "Enter Valid Receipt ID(Either the receipt does not exist or does not belong to the same office) / मान्य रसीद आईडी दर्ज करें।(या तो रसीद मौजूद नहीं है या एक ही कार्यालय से संबंधित नहीं है)";
    public static final String ERROR_30001 = "Enter Valid Receipt Amount. / मान्य रसीद राशि दर्ज करें।";
    public static final String ERROR_30004 = "Entered Amount Cannot be Greater./दर्ज की गई राशि अधिक नहीं हो सकती  ";
    public static final String ERROR_30005 = "Unable to validate your IP. Please try again./आपका आईपी मान्य नहीं है। कृपया पुनः प्रयास करें ।";

    public static final String ERROR_30002 = "Kindly Check The Purpose Of The Entered Receipt ID. / कृपया रसीद आईडी का उद्देश्य जाँचें।";
    public static final String ERROR_30003 = "The Entered Receipt ID Has Already Been Utilised. / रसीद पहले से ही इस्तेमाल किया गया है।";
    
    public static final String TREASURY_URL = "http://www.mptreasury.org/MPTwar/frmCybertreasigras.jsp?deptt_code=0030&dept_name=IGRS-PAS&tax_dept=PAS&actname=&act_code=&major_head={0}&submajor_head={1}&minor_head={2}&scheme_head=0000&distt_code={3}&dist_name={4}&name={5}&tin_no={9}&circle_code=00&parent_treasury_code=&office_name={6}&trs_no=&from_date={7}&to_date={7}&purpose={8}&crn=&Make+Payment=SUBMIT";
    
   // public static final String TREASURY_URL_TEST = "http://www.mptreasury.org/MPTWar/frmCyberTreasigrastest.jsp?deptt_code=0030&dept_name=IGRS-PAS&tax_dept=PAS&actname=&act_code=&major_head={0}&submajor_head={1}&minor_head={2}&scheme_head=0000&distt_code={3}&dist_name={4}&name={5}&tin_no={9}&circle_code=00&parent_treasury_code=&office_name={6}&trs_no=&from_date={7}&to_date={7}&purpose={8}&crn=&Make+Payment=SUBMIT";
    
    public static final String TREASURY_URL_TEST ="https://www.mptreasury.org/MPTWar/cyber/frmCyberTreasIGRTest.jsp?deptt_code=0030&dept_name=IGRS-PAS&tax_dept=PAS&actname=&act_code=&major_head={0}&submajor_head={1}&minor_head={2}&scheme_head=0000&distt_code={3}&dist_name={4}&name={5}&tin_no={9}&circle_code=00&parent_treasury_code=&office_name={6}&trs_no=&from_date={7}&to_date={7}&purpose={8}&crn=&Make+Payment=SUBMIT";
    public static final String TREASURY_URL_TEST_IFMIS ="http://117.240.217.54/MPCTP/IntegrationAgentGMT?deptt_code=0030&dept_name=IGRS-PAS&tax_dept=PAS&act_name=IGRS&major_head={16}&submajor_head={17}&minor_head={18}&scheme_head=00&distt_code={31}&dist_name={32}&name={3}&tin={14}&circle_code=00&parent_treasury_code={25}&office_name={30}&from_date={14}&to_date={15}&purpose={20}&igrs_transaction_ref_no={24}&urn={34}Make+Payment=SUBMIT";
    public static final String TREASURY_URL_FINAL = "https://www.mptreasury.org/MPTWar/cyber/frmCyberTreasIGRTest.jsp?encdata=";
   
}
