package com.Dev.Bidding.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Dev.Bidding.dao.BidingRepo;
import com.Dev.Bidding.dao.MetalRepo;
import com.Dev.Bidding.model.Bid;
import com.Dev.Bidding.model.Metal;
import com.Dev.Bidding.services.BidServices;

@EnableScheduling
@RestController
public class BiddingController {
	
	@Autowired
	BidingRepo repo;
	
	@Autowired
	MetalRepo metaRepo;
	
	@Autowired
	BidServices ser;
	

	@GetMapping("/Bid/{cmpny}")
	public List<Bid> getCmpnyBid(@PathVariable("cmpny") String cmpny)
	{
		List<Bid> bid = repo.findByActivecompanyName(cmpny);
		return bid;
	}
	
	@GetMapping("/Bids/{id}")
	public Bid getBid(@PathVariable("id") int id)
	{
		Bid bid = repo.findById(id).orElse(null);
		return bid;
	}
	
	@PostMapping("/Bid")
	public String submitBid(@RequestBody Bid bid)
	{
		List<Metal> met = metaRepo.findBymetalname(bid.getMetalName());
		if(met.isEmpty())
		{
			return "Please select Gold,Platinum,Silver and Copper to bid";
		}
		int phase=0;
		Calendar cal = Calendar.getInstance();
		Date date=cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
		String time=dateFormat.format(date);
		
		SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy"); 
		String Date1=date1.format(date);
		phase=ser.getPhase();
		
		if(phase >= 1 && phase <= 12)
		{
			bid.setPhase(phase);
			bid.setBidTime(time);
			bid.setBidDate(Date1);
			bid.setStatus(1);//1- Active
			List<Bid> oldBid = repo.findBycompanyNameandmetalNameandbidDate(bid.getCompanyName(),bid.getMetalName(),bid.getBidDate(),bid.getStatus());
			if(oldBid.size()>0)
			{
				oldBid.get(0).setStatus(2);//2-InActive
				repo.save(oldBid.get(0));
			}
			repo.save(bid);
			return "Bid submitted successfully at- "+bid.getBidTime()+", Reference:-"+bid.getBidId();
		}
		else
		{
			return "Bid outside specified time [6 A.M. - 6 P.M.]";
		}
		
		
	}
	@Scheduled(cron="0 0/60 7-18 * * MON-SUN")
	public void hourlyOutput()
	{
		Calendar cal = Calendar.getInstance();
		Date date=cal.getTime();
		SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy"); 
		String Date1=date1.format(date);
		System.out.println(ser.getPhaseTime());
		try
		{
			List<Metal> metals = metaRepo.findAll();
			for (Metal metal : metals) {
				List<Bid> bidMaxHrMetal = repo.findBymetalNameMaxHrBid(metal.getMetalname(), Date1,ser.getPhase()-1);
				List<Bid> bidMinHrMetal = repo.findBymetalNameMinHrBid(metal.getMetalname(), Date1,ser.getPhase()-1);
				if(bidMaxHrMetal.isEmpty())
				{
					System.out.print(metal.getMetalname()+" : Lowest Bid- No Bid");
					System.out.println(" Highest Bid- No Bid");
				}
				else
				{
					System.out.print(metal.getMetalname()+" : Lowest Bid- "+bidMinHrMetal.get(0));
					System.out.println(" Highest Bid- "+bidMaxHrMetal.get(0));
				}	
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Scheduled(cron="0 0 18 * * MON-SUN")
	public void EODOutput()
	{
		System.out.println("At 6 pm ");
		Calendar cal = Calendar.getInstance();
		Date date=cal.getTime();
		SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy"); 
		String Date1=date1.format(date);
		
		try
		{
			List<Metal> metals = metaRepo.findAll();
			for (Metal metal : metals) {
				List<Bid> bidMaxMetal = repo.findBymetalNameMaxBid(metal.getMetalname(), Date1);
				List<Bid> bidMinMetal = repo.findBymetalNameMinBid(metal.getMetalname(), Date1);
				if(bidMaxMetal.isEmpty())
				{
					System.out.print(metal.getMetalname()+" : Lowest Bid- No Bid");
					System.out.println(" Highest Bid- No Bid");
				}
				else
				{
					System.out.print(metal.getMetalname()+" : Lowest Bid- "+bidMinMetal.get(0));
					System.out.println(" Highest Bid- "+bidMaxMetal.get(0));
				}	
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	

}
