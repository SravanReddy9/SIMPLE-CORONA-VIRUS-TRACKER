package com.covidcases.project.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.covidcases.project.model.DataModel;



@Service
public class DataService {
	
	private static String data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	
	private List<DataModel> stats = new ArrayList<>(); 
	
	public  List<DataModel>getStats() {
        return stats;
    }
	 
	
	
	@PostConstruct
	@Scheduled(cron ="0 0 6 * * *")
	//@Scheduled(fixedRate = 3600000)
	public void getData() throws IOException, InterruptedException {
		
		List<DataModel>newstats = new ArrayList<>();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
									.uri(URI.create(data_url))
									.build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(httpResponse.body());
		
		
		StringReader csvBodyReader= new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for (CSVRecord record : records) {
			DataModel datamodel = new DataModel();
			//datamodel.setCountry(record.get("Province/State"));
		    datamodel.setCountry(record.get("Country/Region"));
		    int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            datamodel.setCases_today(latestCases - prevDayCases);
            datamodel.setTotal_cases(latestCases);
		    
		   // System.out.println(datamodel);
		    newstats.add(datamodel);  
		    }
		
		 this.stats = newstats;
		
	}
	  
	
} 
  