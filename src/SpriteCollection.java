//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * a class for a collection of sprites.
 */
public class SpriteCollection {

    // Fields
    private final List<Sprite> list;

    // Constructors

    /**
     * creates a sprite collection object to store sprites.
     *
     * @param l a list for the given sprites.
     */
    public SpriteCollection(List<Sprite> l) {
        this.list = l;
    }

    /**
     * add sprites to the list.
     *
     * @param s given sprite.
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> l = new ArrayList(getSprites());
        for (Sprite s : l) {
            s.timePassed();
        }
    }

    /**
     * draws each sprite in the collection on the given surface.
     *
     * @param d the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        // call drawOn(d) on all sprites.
        for (Sprite s : getSprites()) {
            s.drawOn(d);
        }
    }

    // getters

    /**
     * gets the list of sprites in the collection.
     *
     * @return list of sprites.
     */
    public List<Sprite> getSprites() {
        return list;
    }

    // Removers.

    /**
     * removes sprite from collection.
     *
     * @param s sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }
}