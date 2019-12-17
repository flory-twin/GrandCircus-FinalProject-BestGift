package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.models.etsy.Image;

public interface ImageRepository  extends JpaRepository<Image, Integer> {

}
