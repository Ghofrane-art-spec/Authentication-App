package MiniProject.project.Login.Entity;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private String type;
	@Lob
	@Column(name = "imagedata",length = 1000)
    private byte[] imageData;
	
	@JsonBackReference(value="image-user")
	@OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private User user;
	
	public Image( String name, String type, byte[] imageData) {
		super();
		this.name = name;
		this.type = type;
		this.imageData = imageData;
	}
	public Image() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", type=" + type + ", imageData=" + Arrays.toString(imageData)
				+ "]";
	}
	
	
	
	

}
