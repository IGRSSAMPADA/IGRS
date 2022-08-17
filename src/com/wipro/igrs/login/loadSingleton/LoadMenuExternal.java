package com.wipro.igrs.login.loadSingleton;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import com.wipro.igrs.ACL.bd.MenuBD;
import com.wipro.igrs.login.dao.LoginDAO;
import com.wipro.igrs.payment.dto.OnlinePaymentDto;

public class LoadMenuExternal {
	
private static LoadMenuExternal instance = null;

private static final String[] ipMap= {"A","B","C","D","E","F","G","H","I","K"};

private static final String[] portMAp= {"P","Q","R","S","T","U","V","W","X","Y"};

private String ip;
HashMap<String,ArrayList<Map<String, String>>> map= new HashMap<String,ArrayList<Map<String, String>>>();

private static Hashtable<String , OnlinePaymentDto> dto = new Hashtable<String, OnlinePaymentDto>();

HashMap<String,String> mapToken= new HashMap<String,String>();


HashMap<String,String> mapIP= new HashMap<String,String>();

public HashMap<String, String> getMapIP() {
	return mapIP;
}

public void setMapIP(HashMap<String, String> mapIP) {
	this.mapIP = mapIP;
}

public HashMap<String, String> getMapToken() {
	return mapToken;
}

public void setMapToken(HashMap<String, String> mapToken) {
	this.mapToken = mapToken;
}

private LoadMenuExternal()
{
	InetAddress ip;
	try {
		ip = InetAddress.getLocalHost();
		String ipStr = ip.getHostAddress();
		ipStr = ipStr.substring(ipStr.lastIndexOf(".")+1, ipStr.length());
		char[] num = ipStr.toCharArray();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < num.length; i++) {
			temp.append(ipMap[new Integer(num[i]+"")]);
		}
		this.ip = temp.toString();
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public String getIP(){
return ip;	
}

public String getPort(String port){
	return portMAp[Integer.parseInt(port)];
}

public static LoadMenuExternal getInstance()
{
	if(instance==null)
	{
		instance=new LoadMenuExternal();
		
	}
	return instance;
}
public Hashtable<String, OnlinePaymentDto> getDto() {
	return dto;
}
/*public void setDto(HashMap<String, OnlinePaymentDto> dto) {
	this.dto = dto;
}*/
public  ArrayList<Map<String, String>> loadMenu(String roleId)
{
	Iterator I = map.entrySet().iterator();
	int i=0;
	while (I.hasNext()) { 
		Map.Entry me = (Map.Entry) I.next();
		String key=(String) me.getKey();
		if(key.equalsIgnoreCase(roleId))
		{
			return((ArrayList<Map<String, String>>) me.getValue());
		}
	}
	MenuBD menubd;
	try {
		LoginDAO dao = new LoginDAO();
		ArrayList<Map<String, String>> arr=dao.getCompleteMenuDetails(new String[]{roleId});
		map.put(roleId,arr);
		//ArrayList newList=(ArrayList)arr.clone();
		return arr;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;	
}

public OnlinePaymentDto getOnlinePaymentDto(String id)
{
	
	return dto.get(id);
}

public void deleteOnlinePayementDto(String id)
{
	dto.remove(id);
}

}
