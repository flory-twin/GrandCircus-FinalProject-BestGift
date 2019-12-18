package co.grandcircus.bestgift.jparepos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.models.etsy.Gift;

public interface GiftRepository extends JpaRepository<Gift, Integer> {
	
	public List<Gift> findByListingId(Integer listingId);
}
