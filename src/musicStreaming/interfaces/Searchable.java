/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package musicStreaming.interfaces;

import java.util.List;
import musicStreaming.media.Media;

/**
 *
 * @author Fro Wrya M Salih
 */

/**
 * Interface for classes that support searching functionality
 */
public interface Searchable {
    /**
     * Search for media by keyword
     * @param keyword the search term
     * @return list of matching media
     */
    List<Media> search(String keyword);
}
