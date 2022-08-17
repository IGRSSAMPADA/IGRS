package com.wipro.igrs.aadhar.domain.authentication.helper;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Bio;
import com.wipro.igrs.aadhar.domain.authentication.requestData.BioMetricType;
import com.wipro.igrs.aadhar.domain.authentication.requestData.BiometricPosition;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Bios;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Demo;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Gender;
import com.wipro.igrs.aadhar.domain.authentication.requestData.MatchingStrategy;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Pa;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Pfa;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Pi;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Pid;
import com.wipro.igrs.aadhar.domain.authentication.requestData.Pv;

/**
 * This class provides utility method to create Pid object using data that was collected by the Auth Device
 * in the Auth Client GUI.
 * 
 * @author ARNAV.NEGI
 * 
 */
public class PidCreator {
	
	private static XMLGregorianCalendar timeStamp;

	public static XMLGregorianCalendar getTimeStamp() {
		return timeStamp;
	}

	public static void setTimeStamp(XMLGregorianCalendar timeStamp) {
		PidCreator.timeStamp = timeStamp;
	}

	public static Pid createXmlPid(com.wipro.igrs.aadhar.domain.authentication.subservice.Pid subservicePid) {

		Pid pid = new Pid();
		
		if (subservicePid != null) {

			boolean isPiPresent = false, isPaPresent = false, isPfaPresent = false;

			Demo demo = new Demo();
			if(subservicePid.getDemo() != null)
			{
				if(subservicePid.getDemo().getPi() != null)
				{
					demo.setLang(subservicePid.getDemo().getLang());
					if (subservicePid.getDemo().getPi().getName() != null && subservicePid.getDemo().getPi().getName().length()!=0
							|| subservicePid.getDemo().getPi().getLname() != null && subservicePid.getDemo().getPi().getLname().length()!=0
							|| subservicePid.getDemo().getPi().getDob() != null && subservicePid.getDemo().getPi().getDob().length()!=0
							|| subservicePid.getDemo().getPi().getDobt() != null && subservicePid.getDemo().getPi().getDobt().length()!=0
							|| subservicePid.getDemo().getPi().getEmail() != null && subservicePid.getDemo().getPi().getEmail().length()!=0
							|| subservicePid.getDemo().getPi().getGender() != null 
							&& (subservicePid.getDemo().getPi().getGender()==com.wipro.igrs.aadhar.domain.authentication.subservice.Gender.M
							|| subservicePid.getDemo().getPi().getGender()==com.wipro.igrs.aadhar.domain.authentication.subservice.Gender.F
							|| subservicePid.getDemo().getPi().getGender()==com.wipro.igrs.aadhar.domain.authentication.subservice.Gender.T)
							|| subservicePid.getDemo().getPi().getAge() != null && subservicePid.getDemo().getPi().getAge() != 0
							|| subservicePid.getDemo().getPi().getPhone() != null && subservicePid.getDemo().getPi().getPhone().length()!=0) {
						Pi pi = new Pi();
						if(subservicePid.getDemo().getPi().getMs() != null && 
								(subservicePid.getDemo().getPi().getMs()==com.wipro.igrs.aadhar.domain.authentication.subservice.MatchingStrategy.E
								|| subservicePid.getDemo().getPi().getMs()==com.wipro.igrs.aadhar.domain.authentication.subservice.MatchingStrategy.F
								|| subservicePid.getDemo().getPi().getMs()==com.wipro.igrs.aadhar.domain.authentication.subservice.MatchingStrategy.P))
							pi.setMs(MatchingStrategy.fromValue(subservicePid.getDemo().getPi().getMs().value()));
						else
							pi.setMs(MatchingStrategy.P);
						if(subservicePid.getDemo().getPi().getMv() != 0 && StringUtils.isNumeric(String.valueOf(subservicePid.getDemo().getPi().getMv())))
							pi.setMv(subservicePid.getDemo().getPi().getMv());
						else
							pi.setMv(100);
						pi.setName(subservicePid.getDemo().getPi().getName());
		
						if (subservicePid.getDemo().getPi().getLname() != null && subservicePid.getDemo().getPi().getLname().length()!=0) {
							if(subservicePid.getDemo().getPi().getLmv() != 0 && StringUtils.isNumeric(String.valueOf(subservicePid.getDemo().getPi().getLmv())))
								pi.setLmv(subservicePid.getDemo().getPi().getLmv());
							else
								pi.setLmv(100);
							pi.setLname(subservicePid.getDemo().getPi().getLname());
						}
		
						pi.setDob(subservicePid.getDemo().getPi().getDob());
						pi.setDobt(subservicePid.getDemo().getPi().getDobt());
		
						if(subservicePid.getDemo().getPi().getAge() != null && subservicePid.getDemo().getPi().getAge() != 0)
						{
							if (StringUtils.isNumeric(String.valueOf(subservicePid.getDemo().getPi().getAge()))) {
								pi.setAge(new Integer(subservicePid.getDemo().getPi().getAge()));
							} else {
								if (StringUtils.isNotBlank(String.valueOf(subservicePid.getDemo().getPi().getAge()))) {
									throw new RuntimeException("Age should be numeric");
								}
							}
						}
		
						if(subservicePid.getDemo().getPi().getEmail()!=null && !subservicePid.getDemo().getPi().getEmail().equalsIgnoreCase(""))
						pi.setEmail(subservicePid.getDemo().getPi().getEmail());
						if (subservicePid.getDemo().getPi().getGender()!=null 
								&& subservicePid.getDemo().getPi().getGender()==com.wipro.igrs.aadhar.domain.authentication.subservice.Gender.M) {
							pi.setGender(Gender.M);
						} else if (subservicePid.getDemo().getPi().getGender()!=null 
								&& subservicePid.getDemo().getPi().getGender()==com.wipro.igrs.aadhar.domain.authentication.subservice.Gender.F) {
							pi.setGender(Gender.F);
						} else if (subservicePid.getDemo().getPi().getGender()!=null 
								&& subservicePid.getDemo().getPi().getGender()==com.wipro.igrs.aadhar.domain.authentication.subservice.Gender.T) {
							pi.setGender(Gender.T);
						} else {
							pi.setGender(null);
						}
						if(subservicePid.getDemo().getPi().getPhone()!=null && !subservicePid.getDemo().getPi().getPhone().equalsIgnoreCase(""))
							pi.setPhone(subservicePid.getDemo().getPi().getPhone());
						demo.setPi(pi);
		
						isPiPresent = true;
					}
				}
				if(subservicePid.getDemo().getPfa() != null)
				{
					if (subservicePid.getDemo().getPfa().getAv() != null && subservicePid.getDemo().getPfa().getAv().length() != 0
							|| (subservicePid.getDemo().getPfa().getLav() != null && subservicePid.getDemo().getPfa().getLav().length() != 0)) {
						Pfa pfa = new Pfa();
						if(subservicePid.getDemo().getPfa().getMs() != null && 
								(subservicePid.getDemo().getPfa().getMs()==com.wipro.igrs.aadhar.domain.authentication.subservice.MatchingStrategy.E
								|| subservicePid.getDemo().getPfa().getMs()==com.wipro.igrs.aadhar.domain.authentication.subservice.MatchingStrategy.F
								|| subservicePid.getDemo().getPfa().getMs()==com.wipro.igrs.aadhar.domain.authentication.subservice.MatchingStrategy.P))
							pfa.setMs(MatchingStrategy.fromValue(subservicePid.getDemo().getPfa().getMs().value()));
						else
							pfa.setMs(MatchingStrategy.P);
						if(subservicePid.getDemo().getPfa().getMv() != 0 && StringUtils.isNumeric(String.valueOf(subservicePid.getDemo().getPfa().getMv())))
							pfa.setMv(subservicePid.getDemo().getPfa().getMv());
						else
							pfa.setMv(100);
						pfa.setAv(subservicePid.getDemo().getPfa().getAv());
		
						if (subservicePid.getDemo().getPfa().getLav() != null && subservicePid.getDemo().getPfa().getLav().length()!=0) {
							pfa.setLav(subservicePid.getDemo().getPfa().getLav());
							if(subservicePid.getDemo().getPfa().getLmv() != 0 && StringUtils.isNumeric(String.valueOf(subservicePid.getDemo().getPfa().getLmv())))
								pfa.setLmv(subservicePid.getDemo().getPfa().getLmv());
							else
								pfa.setLmv(100);
						}
		
						demo.setPfa(pfa);
		
						isPfaPresent = true;
					}
				}
				// Add Pa only if one of the constituent attributes have a value
				// specified
				if(subservicePid.getDemo().getPa() != null)
				{
					if (subservicePid.getDemo().getPa().getCo() != null && subservicePid.getDemo().getPa().getCo().length()!=0
							|| subservicePid.getDemo().getPa().getDist() != null && subservicePid.getDemo().getPa().getDist().length()!=0
							|| subservicePid.getDemo().getPa().getHouse() != null && subservicePid.getDemo().getPa().getHouse().length()!=0
							|| subservicePid.getDemo().getPa().getLm() != null && subservicePid.getDemo().getPa().getLm().length()!=0
							|| subservicePid.getDemo().getPa().getLoc() != null && subservicePid.getDemo().getPa().getLoc().length()!=0
							|| subservicePid.getDemo().getPa().getPc() != null && subservicePid.getDemo().getPa().getPc().length()!=0 
							|| subservicePid.getDemo().getPa().getPo() != null && subservicePid.getDemo().getPa().getPo().length()!=0
							|| subservicePid.getDemo().getPa().getSubdist() != null && subservicePid.getDemo().getPa().getSubdist().length()!=0
							|| subservicePid.getDemo().getPa().getState() != null && subservicePid.getDemo().getPa().getState().length()!=0
							|| subservicePid.getDemo().getPa().getStreet() != null && subservicePid.getDemo().getPa().getStreet().length()!=0
							|| subservicePid.getDemo().getPa().getVtc() != null
							&& subservicePid.getDemo().getPa().getVtc().length()!=0) {
						Pa pa = new Pa();
						pa.setMs(MatchingStrategy.fromValue(subservicePid.getDemo().getPa().getMs().value()));
						pa.setCo(subservicePid.getDemo().getPa().getCo());
						pa.setDist(subservicePid.getDemo().getPa().getDist());
						pa.setHouse(subservicePid.getDemo().getPa().getHouse());
						pa.setLm(subservicePid.getDemo().getPa().getLm());
						pa.setLoc(subservicePid.getDemo().getPa().getLoc());
						pa.setPc(subservicePid.getDemo().getPa().getPc());
						pa.setPo(subservicePid.getDemo().getPa().getPo());
						pa.setSubdist(subservicePid.getDemo().getPa().getSubdist());
						pa.setState(subservicePid.getDemo().getPa().getState());
						pa.setStreet(subservicePid.getDemo().getPa().getStreet());
						pa.setVtc(subservicePid.getDemo().getPa().getVtc());
						demo.setPa(pa);
		
						isPaPresent = true;
					}
				}
			}
			if (isPiPresent || isPaPresent || isPfaPresent) {
				pid.setDemo(demo);
			}			
			if(subservicePid.getPv() != null)
			{
				if (StringUtils.isNotBlank(subservicePid.getPv().getPin()) || StringUtils.isNotBlank(subservicePid.getPv().getOtp())) {
					System.out.println("OTP PID creator block");
					pid.setPv(new Pv());
					pid.getPv().setPin(subservicePid.getPv().getPin());
					pid.getPv().setOtp(subservicePid.getPv().getOtp());
				}
			}
			if(subservicePid.getBios() != null)
			{
				if (subservicePid.getBios().getBio() != null
						&& subservicePid.getBios().getBio().size() > 0) {
					
					System.out.println("BIOS PID creator block");
					
					Bios bios = new Bios();
					pid.setBios(bios);
	
					for (com.wipro.igrs.aadhar.domain.authentication.subservice.Bio p : subservicePid.getBios().getBio()) {
						Bio bio = new Bio();
						bio.setType(BioMetricType.fromValue(p.getType().value()));
						bio.setValue(p.getValue());
						bio.setPosh(BiometricPosition.fromValue(p.getPosh().value()));
	
						pid.getBios().getBio().add(bio);
					}
				}
			}
			Calendar calendar = GregorianCalendar.getInstance();			
			XMLGregorianCalendar gregorianCalendar = XMLGregorianCalendarImpl.createDateTime(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND));			
			pid.setTs(gregorianCalendar);
			pid.setVer("1.0");
			timeStamp=gregorianCalendar;
		}
		return pid;
	}
}
