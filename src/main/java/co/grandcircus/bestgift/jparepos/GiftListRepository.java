package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.tables.GiftList;

public interface GiftListRepository extends JpaRepository<GiftList, Integer>{
	
}
