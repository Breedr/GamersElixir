package uk.breedrapps.gamerselixir.classes;

/**
 * Created by edgeorge on 27/07/15.
 */
public class MenuListItem {
    public int name;
    public int imageResourceID;
    public boolean isExternal;

    public MenuListItem(int name, int imageResourceID, boolean isExternal){
        this.name = name;
        this.imageResourceID = imageResourceID;
        this.isExternal = isExternal;
    }
}
