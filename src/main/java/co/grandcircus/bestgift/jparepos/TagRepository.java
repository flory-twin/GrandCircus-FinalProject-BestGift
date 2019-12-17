package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.models.etsy.info.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
