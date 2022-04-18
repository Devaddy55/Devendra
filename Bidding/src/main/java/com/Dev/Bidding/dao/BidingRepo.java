package com.Dev.Bidding.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Dev.Bidding.model.Bid;

public interface BidingRepo extends JpaRepository<Bid, Integer>{
	
	@Query("from Bid where companyName=?1 and status=1")
	List<Bid> findByActivecompanyName(String cmpny);
	
	@Query("from Bid where companyName=?1 and metalName=?2 and bidDate=?3 and status=?4")
	List<Bid> findBycompanyNameandmetalNameandbidDate(String cn,String mn,String bd,int st);
	
	@Query("from Bid where metalName=?1 and bidDate=?2 order by bidPrice desc")
	List<Bid> findBymetalNameMaxBid(String mn,String bd);
	
	@Query("from Bid where metalName=?1 and bidDate=?2 order by bidPrice asc")
	List<Bid> findBymetalNameMinBid(String mn,String bd);
	
	@Query("from Bid where metalName=?1 and bidDate=?2 and phase=?3 order by bidPrice desc")
	List<Bid> findBymetalNameMaxHrBid(String mn,String bd,int ph);
	
	@Query("from Bid where metalName=?1 and bidDate=?2 and phase=?3 order by bidPrice asc")
	List<Bid> findBymetalNameMinHrBid(String mn,String bd,int ph);

}
