
/*
lerada tanha interface playable bakar dahenerit
hokary nanwsiny code interface ka awaya ka aw interface la class Media implemented krdwa
hokary bakar henany yak interface awaya ka damanawet podcateka tanha btwanin gwey le bgrin natwanin dowanload bkain 
*/
package musicStreaming.media;


public class Podcast extends Media{

/*am varable na tanha bo halgrtni data nya balkw bo 
    awaya btwanin la regay nawy peshkash karakawa research bkain 
    //yakam varaible: nawe aw peshkash karanan ka podcast peshkash dakan,*/
    
    private String name; 
    
    //am varavble tanha bo katy search kada yarmatiman nayat balkw sort krdnish datwanin swdy le warbgrin 
    
    private int episodeNumber; 
    
    public Podcast(String id, String title, int duration, String host, int episodeNumber) {
        super(id, title, duration);
        this.name= name;
        this.episodeNumber = episodeNumber;
    }
    //lerada methody adstracter ka implement bkain. 
    @Override
    public void play() { 
        System.out.println("Playing Podcast: " + getTitle() + " (Episode " + episodeNumber + ") hosted by " +name);
    }

    }


    
    