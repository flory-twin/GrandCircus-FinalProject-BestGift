package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.models.etsy.ImageItem;

public interface ImageItemRepository extends JpaRepository<ImageItem, Integer> {

}
