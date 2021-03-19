package logic;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class Item {
	private String id;
	@NotEmpty(message="��ǰ���� �Է��ϼ���")
	private String name;
	@Min(value=10,message="10���̻� �����մϴ�.")
	@Max(value=100000,message="10�������ϸ� �����մϴ�.")
	private int price;
	@NotEmpty(message="��ǰ������ �Է��ϼ���")
	@Size(min=10,max=1000,message="10���̻� 1000�� ���ϸ� �����մϴ�.")
	private String description;
	private String pictureUrl;
	private MultipartFile picture; //���� ���ε�
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", pictureUrl=" + pictureUrl + ", picture=" + picture + "]";
	}
	
	
}
