
/*
 labar away la class Media da tanha interface playble implemented krawa
boya lerada boya anwsin 
 */
package musicStreaming.media;

import musicStreaming.interfaces.Downloadable;


public class Song extends Media implements Downloadable{
    //artist: nawy gorani bezhakaya
     private String artist; 
     
     //stile style goraniaka (pop,rock,jazz)
      private String gener; 
    
    

    public Song(String id, String title, int duration,String artist,String gener) {
        super(id, title, duration);
        this.artist=artist;
        this.gener=gener;
    }
@Override
    public void play(){}
    
    @Override
    public void download(){
       System.out.println("Download song :"+getTitle()); //To change body of generated methods, choose Tools | Templates.
    }

    public String getArtist() {
        return artist;
    }

    public String getGener() {
        return gener;
    }

    public Object getGenre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
