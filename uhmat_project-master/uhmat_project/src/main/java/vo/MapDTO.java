package vo;

//CREATE TABLE map(
//longitude DOUBLE,
//latitude DOUBLE,
//res_name VARCHAR(30) REFERENCES restaurant_info(res_name)
//);

public class MapDTO {
	private String resName;
	private double longitude;
	private double latitude;
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "MapDTO [resName=" + resName + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
}
