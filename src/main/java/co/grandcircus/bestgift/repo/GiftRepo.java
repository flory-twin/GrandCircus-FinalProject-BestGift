package co.grandcircus.bestgift.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.models.Gift;

public interface GiftRepo extends JpaRepository<Gift, Integer>{

}
