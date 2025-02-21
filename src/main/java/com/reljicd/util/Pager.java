package com.reljicd.util;

import org.springframework.data.domain.Page;

import com.reljicd.model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Utility class for handling pagination of Post objects.
 * Provides methods to get pagination details.
 * 
 * @author Dusan Raljic
 */
@Data
@AllArgsConstructor
public class Pager {

    private final Page<Post> posts;

    /**
     * Gets the current page index (1-based).
     *
     * @return the page index
     */
    public int getPageIndex() {
        return posts.getNumber() + 1;
    }

    /**
     * Checks if the current page index is out of bounds.
     *
     * @return true if the index is out of bounds, false otherwise
     */
    public boolean indexOutOfBounds() {
        return getPageIndex() < 1 || getPageIndex() > getTotalPages();
    }

    /**
     * Gets the total number of pages.
     *
     * @return the total number of pages
     */
    public int getTotalPages() {
        return posts.getTotalPages(); // Use the Page<Post> method to get total pages
    }
}




// package com.reljicd.util;

// import com.reljicd.model.Post;
// import org.springframework.data.domain.Page;

// /**
//  * @author Dusan Raljic
//  */
// public class Pager {

//     private final Page<Post> posts;

//     public Pager(Page<Post> posts) {
//         this.posts = posts;
//     }

//     public int getPageIndex() {
//         return posts.getNumber() + 1;
//     }

//     public int getPageSize() {
//         return posts.getSize();
//     }

//     public boolean hasNext() {
//         return posts.hasNext();
//     }

//     public boolean hasPrevious() {
//         return posts.hasPrevious();
//     }

//     public int getTotalPages() {
//         return posts.getTotalPages();
//     }

//     public long getTotalElements() {
//         return posts.getTotalElements();
//     }

//     public Page<Post> getPosts() {
//         return posts;
//     }

//     public boolean indexOutOfBounds() {
//         return getPageIndex() < 0 || getPageIndex() > getTotalElements();
//     }

// }
