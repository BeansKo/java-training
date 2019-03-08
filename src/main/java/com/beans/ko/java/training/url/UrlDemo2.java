package com.beans.ko.java.training.url;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class UrlDemo2 {
	public static void main(String[] args){
	
		String emails = "andrewlee647@gmail.com,qclander@hotmail.ca,daniel.sheen@mail.utoronto.ca,wolf34@gmail.com,ditchitcanada@gmail.com,2l.llama@gmail.com,fajardo067@gmail.com,ve2vdi@gmail.com,don.quan57@gmail.com,stamant519@hotmail.com,crvanengineers@corusent.com,Avery.speller@gmail.com,fromthedarkness019@gmail.com,purchasing@heroninstruments.com,gabriel7hkis@gmail.com,leblanc84@outlook.com,lolydarcel@darfou.com,cocosocoo@gmail.com,ptritprogue@gmail.com,harrower.michael@gmail.com,2l.llama@gmail.com,memineselfandi@gmail.com,dmezquita2@gmail.com,bardalezvlad@gmail.com,pzac2003@hotmail.com,mfournier61@gmail.com,bumblebee1387@hotmail.com,annitadignam50@live.ca,920662354@qq.com,thomas.kuban@luxfer.com,webants@gmail.com,williamejb@outlook.com,trillionsviews@gmail.com,bfalardeau@mrcbm.qc.ca,bardalezvlad@gmail.com,yves_robitaille@hotmail.com,lkp10004@gmail.com,domfou@msn.com,junlongkong@outlook.com,wolfbane168@gmail.com,octosoufre@gmail.com,rainie@pssnet.com,mattmyster23@gmail.com,martin.dubois@hotmail.com,yves_robitaille@hotmail.com,martinrozon23@gmail.com,damien_verbakel@hotmail.com,baseball.star@gmail.com,mike.tom.888@gmail.com,aizyck@hotmail.com";
		String[] emailArr = emails.split(",");
		int count=0;
		StringBuffer strEmail = new StringBuffer("");

		for (int i=0;i<emailArr.length;i++) {
			strEmail.append(emailArr[i]).append(",");
			if(count == 5){
				URL restURL;
				try {
					String url = "http://apis.newegg.org/api/Common/BatchEncrypt?text=%s&access_token=9nK6cvBMcSQoxvNQcNl5DIF3PAV05VycFdSG9T8u";
					String email = strEmail.toString();
					url = String.format(url,email.substring(0, email.lastIndexOf(",")));
					System.out.println(url);
					restURL = new URL(url);
					HttpURLConnection conn = (HttpURLConnection)restURL.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					try (InputStream br = conn.getInputStream();) {
						int str = br.read();
						while (str != -1){
							
							str = br.read();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				count = 0;
				strEmail.setLength(0);			
			}
			count++;
		}
	}

}
