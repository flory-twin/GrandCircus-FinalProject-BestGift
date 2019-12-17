package co.grandcircus.bestgift.models.etsy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "image_item")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_item_id")
	Integer id;
	String url_570xN;
	String imageAsBinary;
	public String getUrl_570xN() {
		return url_570xN;
	}
	public ImageItem(Integer id, String url_570xN, String imageAsBinary) {
		super();
		this.id = id;
		this.url_570xN = url_570xN;
		this.imageAsBinary = imageAsBinary;
	}
	public ImageItem(String url_570xN, String imageAsBinary) {
		super();
		this.url_570xN = url_570xN;
		this.imageAsBinary = imageAsBinary;
	}
	public ImageItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setUrl_570xN(String url_570xN) {
		this.url_570xN = url_570xN;
	}
	public String getImageAsBinary() {
		return imageAsBinary;
	}
	public void setImageAsBinary(String imageAsBinary) {
		this.imageAsBinary = imageAsBinary;
	}


}
