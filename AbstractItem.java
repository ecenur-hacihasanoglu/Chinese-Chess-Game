
public abstract class AbstractItem implements ItemInterface {

	private String position; // tahtadaki konumu gösterir. Örneğin, a1
	private String colour;

	public  String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	};

}