package com.beans.ko.java.training.url;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;


public class TestURL {
	public static void main(String[] args) throws Exception {
		String emails = "alan.y.qian@newegg.com,lynn.x.dong@newegg.com";
		emails = URLEncoder.encode(emails, "UTF-8");
		System.out.println(emails);
		URL url = new URL("http://apis.newegg.org/api/Common/BatchEncrypt?text=zahirengland%40gmail.com%2Czahrar13%40gmail.com%2Czainjaffal%40gmail.com%2Czak33%40msn.com%2CZakHutchison0711%40hotmail.com%2Czaktreehugger%40g           mail.com%2Czammccants%40yahoo.com%2Czane.budaha%40gamil.com%2Czak.wilcockson01%40gmail.com%2Czak_o2%40yahoo.co.uk%2Czakkinu2%40hotmail.com%2CZaliquid%40gmail.com%2Czarkanian%40gmail.com%2Czatchillac%40gmail.com%2Czayanmustafa%           40yahoo.com%2Czanlin%40gmail.com%2Czbigniewpll%40gmail.com%2Czbsmith%40hotmail.com%2Czdempsey28%40gmail.com%2Cze_billy16%40hotmail.com%2Czeearch%40yahoo.com%2Czeeshan%40hussain.com%2CZedmastertime%40outlook.com%2Czenzareus%40g           mail.com%2Czeradin%40hotmail.co.uk%2CZer0Reduction%40icloud.com%2Czhadowz.major%40gmail.com%2Czesuk58%40gmail.com%2Czhihaoli0000%40gmail.com%2Czhouquandong1%40gmail.com%2CZhouquandong2%40gmail.com%2Czhenis.js%40googlemail.com%           2Czifttm%40gmail.com%2CZiolekarkadiusz%40gmail.com%2CZivshanmugam%40gmail.com%2Czlamlam10%40gmail.com%2CZombietsar%40gmail.com%2Cznorvidas%40outlook.com%2Czombiekiller481516%40gmail.com%2Czoran.lubura%40gmail.com%2Cztaher%40ma           c.com%2Cztorkehaasn%40gmail.com%2Czuki%40orange.net%2Czxj.hts%40gmail.com%2Czytajakub73%40gmail.com%2Cmax.krawiec0%40gmail.com%2Cdmorley%40morleyelectronics.co.uk%2Cearmail360%40gmail.com%2Cparthcoolboy05%40gmail.com%2Cgamersf           unbox%40live.com%2Cferelinstincts%40hotmail.co.uk%2Cblllcurie%40yahoo.co.uk%2Cmarleyrules%40hotmail.co.uk%2Cgrant_mcculloch2008%40hotmail.co.uk%2Cpsacet%40hotmail.co.uk%2Ccharles.lee961%40googlemail.com%2Ckitoamano%40gmail.com           %2Cfastair6%40gmail.com%2Cthenderslender%40gmail.com%2Cyxxact%40gmail.com%2Cadamgreen99%40live.co.uk%2Cpaochman%40interia.pl%2Cfaizan999245%40gmail.com%2Cs.wilson989%40gmail.com%2Cthomas_dixon%40hotmail.co.uk%2Cumairmuzammil16           8%40gmail.com%2Cmax%40maxmaxwell.co.uk%2Csykeoriginalyt%40outlook.com%2Cnojus0456%40gmail.com%2Cskull66.de%40gmail.com%2CBlueskylight%40hotmail.co.uk%2CI_Lavington%40hotmail.com%2Cflynnbo3%40msn.com%2Cchrissinclair1951991%40gm           ail.com%2Cbenhayman%40btinternet.com%2Cminecraftstevey%40gmail.com%2Ckarol%40e-dot.pl%2Ckeiranstevenson%40live.co.uk%2Cdaniel.uribe.blanco%40gmail.com%2Cvickers012682%40gmail.com%2Cjkeveren%40gmail.com%2Cswaansea%40gmail.com%2           Cron%40ronmitchell.co.uk%2Clazycronin%40gmail.com%2Clewysworld%40hotmail.com%2Csigisone%40gmail.com%2Cgzweatherlight%40gmail.com%2Cbuchanan18%40hotmail.co.uk%2Croddycarver%40msn.com%2C826018370%40qq.com%2Camy.y.wang%40newegg.c           om%2Crickyblackwell%40hotmail.com%2Cjonnym91%40ymail.com%2Cthebeanogamer%40gmail.com%2Cowzie123%40gmail.com&access_token=9nK6cvBMcSQoxvNQcNl5DIF3PAV05VycFdSG9T8u");
		try(
			InputStream in = url.openStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024)){
			byte[] b = new byte[1024];
			int len = 0;
			while((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			System.out.println(out.toString());
		}catch (Exception ex){
			System.out.println("error");
		}
		System.out.println("end");
	}
}
