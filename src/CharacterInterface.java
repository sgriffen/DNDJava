
public interface CharacterInterface {

	public int getID();
	public int getSpeed();
	public int getViewRange();
	public String getImage();
	public String getName();
	public int[] getLocation();
	public boolean setLocation(int xLoc, int yLoc);
	public boolean retrieveStats(int ID);
	
}
