
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package musicStreaming.interfaces;

/**
 *
 * @author Fro Wrya M Salih
 */

/**
 * Interface for media that can be downloaded
 */
public interface Downloadable {
    /**
     * Download the media for offline use
     * @return true if download successful, false otherwise
     */
    boolean download();
}
