package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.models.Gift;

public interface GiftRepository extends JpaRepository<Gift, Integer> {

}
