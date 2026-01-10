
package musicStreaming.media;

import musicStreaming.interfaces.Playable;


public abstract class Media implements Playable {
    private String id;
    private String title;
    private int duration;
    
    public Media(String id,String title,int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;}
    
     @Override
     public abstract void play();
    
    public String getTitle() {
        return title;
    }
    
    public String getId() { 
        return id;}
    
    public int getDuration() { 
        return duration;}
    
    public void setTitle(String title) { 
        this.title = title;}
}