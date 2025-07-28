#include <stdio.h>
#include <stdbool.h>
#include <limits.h>

// LRU (Least Recently Used) Page Replacement Algorithm

typedef struct {
    int page;
    int last_used_time;
} PageFrame;

void displayPageFrames(PageFrame frames[], int frame_size, int current_time) {
    printf("Frames: [");
    for(int i = 0; i < frame_size; i++) {
        if(frames[i].page == -1) {
            printf(" - ");
        } else {
            printf(" %d ", frames[i].page);
        }
    }
    printf("]\t");
    
    // Display last used times
    printf("Times: [");
    for(int i = 0; i < frame_size; i++) {
        if(frames[i].page == -1) {
            printf(" - ");
        } else {
            printf(" %d ", frames[i].last_used_time);
        }
    }
    printf("]");
}

bool isPagePresent(PageFrame frames[], int frame_size, int page) {
    for(int i = 0; i < frame_size; i++) {
        if(frames[i].page == page) {
            return true;
        }
    }
    return false;
}

int findPageIndex(PageFrame frames[], int frame_size, int page) {
    for(int i = 0; i < frame_size; i++) {
        if(frames[i].page == page) {
            return i;
        }
    }
    return -1;
}

int findEmptyFrame(PageFrame frames[], int frame_size) {
    for(int i = 0; i < frame_size; i++) {
        if(frames[i].page == -1) {
            return i;
        }
    }
    return -1;
}

int findLRUFrame(PageFrame frames[], int frame_size) {
    int lru_index = 0;
    int min_time = frames[0].last_used_time;
    
    for(int i = 1; i < frame_size; i++) {
        if(frames[i].last_used_time < min_time) {
            min_time = frames[i].last_used_time;
            lru_index = i;
        }
    }
    
    return lru_index;
}

void lruPageReplacement(int pages[], int n, int frame_size) {
    PageFrame frames[frame_size];
    int page_faults = 0;
    int page_hits = 0;
    int current_time = 0;
    
    // Initialize frames
    for(int i = 0; i < frame_size; i++) {
        frames[i].page = -1;
        frames[i].last_used_time = -1;
    }
    
    printf("\nLRU Page Replacement Simulation:\n");
    printf("Page\tFrames\t\t\tTimes\t\t\tStatus\n");
    printf("----\t------\t\t\t-----\t\t\t------\n");
    
    for(int i = 0; i < n; i++) {
        current_time = i;
        printf("%d\t", pages[i]);
        
        if(isPagePresent(frames, frame_size, pages[i])) {
            // Page hit - update last used time
            page_hits++;
            int page_index = findPageIndex(frames, frame_size, pages[i]);
            frames[page_index].last_used_time = current_time;
            
            displayPageFrames(frames, frame_size, current_time);
            printf("\tPage Hit\n");
        } else {
            // Page fault
            page_faults++;
            
            int empty_frame = findEmptyFrame(frames, frame_size);
            
            if(empty_frame != -1) {
                // Empty frame available
                frames[empty_frame].page = pages[i];
                frames[empty_frame].last_used_time = current_time;
            } else {
                // No empty frame, replace LRU page
                int lru_frame = findLRUFrame(frames, frame_size);
                printf("(Replacing page %d) ", frames[lru_frame].page);
                frames[lru_frame].page = pages[i];
                frames[lru_frame].last_used_time = current_time;
            }
            
            displayPageFrames(frames, frame_size, current_time);
            printf("\tPage Fault\n");
        }
    }
    
    printf("\n=== LRU Page Replacement Results ===\n");
    printf("Total Page References: %d\n", n);
    printf("Total Page Faults: %d\n", page_faults);
    printf("Total Page Hits: %d\n", page_hits);
    printf("Page Fault Rate: %.2f%%\n", (float)page_faults / n * 100);
    printf("Page Hit Rate: %.2f%%\n", (float)page_hits / n * 100);
}

void displayPageReferences(int pages[], int n) {
    printf("Page Reference String: ");
    for(int i = 0; i < n; i++) {
        printf("%d ", pages[i]);
    }
    printf("\n");
}

void demonstrateLRULogic() {
    printf("\n=== LRU Algorithm Logic ===\n");
    printf("1. When a page is referenced, it becomes the most recently used\n");
    printf("2. Each page has a timestamp indicating when it was last used\n");
    printf("3. When a page fault occurs and all frames are full:\n");
    printf("   - Find the page with the oldest (smallest) timestamp\n");
    printf("   - Replace that page with the new page\n");
    printf("   - Update the timestamp of the new page\n");
    printf("4. LRU assumes that recently used pages are more likely to be used again\n");
}

int main() {
    int n, frame_size;
    
    printf("=== LRU (Least Recently Used) Page Replacement Algorithm ===\n");
    
    printf("Enter the number of page references: ");
    scanf("%d", &n);
    
    int pages[n];
    printf("Enter the page reference string:\n");
    for(int i = 0; i < n; i++) {
        printf("Page %d: ", i + 1);
        scanf("%d", &pages[i]);
    }
    
    printf("Enter the number of page frames: ");
    scanf("%d", &frame_size);
    
    if(frame_size <= 0) {
        printf("Error: Number of frames must be positive!\n");
        return 1;
    }
    
    printf("\n=== Input Summary ===\n");
    displayPageReferences(pages, n);
    printf("Number of Page Frames: %d\n", frame_size);
    
    // Demonstrate LRU logic
    demonstrateLRULogic();
    
    // Simulate LRU page replacement
    lruPageReplacement(pages, n, frame_size);
    
    printf("\nNote: LRU replaces the page that has not been used for the longest time.\n");
    printf("This algorithm requires tracking the last access time for each page.\n");
    
    return 0;
}
