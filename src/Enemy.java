
public class Enemy implements CharacterInterface {
	private String name = null;
	private int ID = 0001;
	private int speed = 6;//Move speed in Units/Turn
	private int viewRange = 6;
	private String imagePath = "src/";
	private int[] location = {0, 0}; //xLoc, yLoc

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public int getViewRange() {
		return viewRange;
	}

	@Override
	public String getImage() {
		return imagePath;
	}

	@Override
	public int[] getLocation() {
		return location;
	}

	@Override
	public boolean setLocation(int xLoc, int yLoc) {
		location[0] = xLoc;
		location[1] = yLoc;
		return true;		
}

	@Override
	public boolean retrieveStats(int ID) {
		//TODO read stats from spreadsheet and set variables.
		return true;
	}

	public Enemy(int IdentificationNumber) {
		ID = IdentificationNumber;
		retrieveStats(ID);
	}

	@Override
	public String getName() {
		return name;
	}

}
