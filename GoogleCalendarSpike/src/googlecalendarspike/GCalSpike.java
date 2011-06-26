/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Download GData, UnPack it, import core, client, calendar e collections
//http://code.google.com/intl/it-IT/apis/calendar/data/2.0/developers_guide_java.html
package googlecalendarspike;

import java.io.IOException;
import java.net.MalformedURLException;
import com.google.gdata.client.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.data.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;
import java.net.URL;
//===problemi con gli import?Leggi: http://code.google.com/p/scssweb1011-asl/wiki/GoogleCalendarHowtoUse

/**
 *
 * @author Piovesan
 */
public class GCalSpike {

    public String login = "vostro login gmail";
    public String password = "vostra password gmail";
    //questo esempio non fa uso di oauth
    CalendarService myService;

    public void creaConnessione() throws MalformedURLException, IOException, AuthenticationException, ServiceException {
        // Crea una connessione
        myService = new CalendarService("exampleCo-exampleApp-1");
        myService.setUserCredentials(login, password);

        //reperisce e stampa tutti i titoli dei miei calendari
        URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/allcalendars/full");
        CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
        System.out.println("Your calendars:");
        System.out.println();
        for (int i = 0; i < resultFeed.getEntries().size(); i++) {
            CalendarEntry entry = resultFeed.getEntries().get(i);
            System.out.println("\t" + entry.getTitle().getPlainText());
        }
    }

    public void creaCalendario(String title) throws Exception {
        // creare e personalizzare il calendario
        CalendarEntry calendar = new CalendarEntry();
        calendar.setTitle(new PlainTextConstruct(title));
        calendar.setSummary(new PlainTextConstruct("Calendario di prova"));
        calendar.setTimeZone(new TimeZoneProperty("Europe/Rome"));
        calendar.setHidden(HiddenProperty.FALSE);
        calendar.setColor(new ColorProperty("#2952A3"));
        calendar.addLocation(new Where("", "", "Turin"));

        // inserire il calendario
        URL postUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
        CalendarEntry returnedCalendar = myService.insert(postUrl, calendar);
    }

    //questo metodo serve per reperire l'id di un mio calendario in base al titolo
    public String getCalendarId(String title) throws Exception {
        URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
        CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
        String id = "";
        for (int i = 0; i < resultFeed.getEntries().size(); i++) {
            CalendarEntry entry = resultFeed.getEntries().get(i);
            if (entry.getTitle().getPlainText().equals(title)) {
                //http://www.google.com/support/forum/p/apps-apis/thread?tid=2cdc8238c5066e7c&hl=en
                id = entry.getLink("http://schemas.google.com/gCal/2005#eventFeed", null).getHref();
                return id;
            }
        }
        return null;
    }

    public void inserisciEvento() throws Exception {
        URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
        CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
        URL postUrl = new URL(this.getCalendarId("Appuntamenti"));
        //Creo l'evento e lo personalizzo
        CalendarEventEntry myEntry = new CalendarEventEntry();

        myEntry.setTitle(new PlainTextConstruct("Tennis with Beth"));
        myEntry.setContent(new PlainTextConstruct("Meet for a quick lesson."));

        DateTime startTime = DateTime.parseDateTime("2011-06-18T15:00:00-08:00");
        DateTime endTime = DateTime.parseDateTime("2011-06-18T17:00:00-08:00");
        When eventTimes = new When();
        eventTimes.setStartTime(startTime);
        eventTimes.setEndTime(endTime);
        myEntry.addTime(eventTimes);

        // Inserisco l'evento
        CalendarEventEntry insertedEntry = myService.insert(postUrl, myEntry);
    }

    public void eliminaEvento() throws Exception {
        URL feedUrl = new URL(this.getCalendarId("Appuntamenti"));
        //creo una query e ottengo la lista di eventi che la soddisfano nel calendario Appuntamenti
        Query myQuery = new Query(feedUrl);
        myQuery.setFullTextQuery("Tennis");
        CalendarEventFeed myResultsFeed = myService.query(myQuery, CalendarEventFeed.class);
        if (myResultsFeed.getEntries().size() > 0) {
            CalendarEventEntry firstMatchEntry = (CalendarEventEntry) myResultsFeed.getEntries().get(0);
            String myEntryTitle = firstMatchEntry.getTitle().getPlainText();
            System.out.println("==="+myEntryTitle);
            firstMatchEntry.delete();
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GCalSpike spike = new GCalSpike();
        try {
            spike.creaConnessione();
            spike.creaCalendario("Appuntamenti");
            spike.inserisciEvento();
            spike.eliminaEvento();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
    }
}
