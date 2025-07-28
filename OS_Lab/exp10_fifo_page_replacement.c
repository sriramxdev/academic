#include <stdio.h>
#include <stdbool.h>

// FIFO (First In First Out) Page Replacement Algorithm
// Also known as FCFS page replacement

void displayPageFrames(int frames[], int frame_size) {
    printf("Frames: [");
    for(int i = 0; i < frame_size; i++) {
        if(frames[i] == -1) {
            printf(" - ");
        } else {
            printf(" %d ", frames[i]);
        }
    }
    printf("]\n");
}

bool isPagePresent(int frames[], int frame_size, int page) {
    for(int i = 0; i < frame_size; i++) {
        if(frames[i] == page) {
            return true;
        }
    }
    return false;
}

int findEmptyFrame(int frames[], int frame_size) {
    for(int i = 0; i < frame_size; i++) {
        if(frames[i] == -1) {
            return i;
        }
    }
    return -1;
}

void fifoPageReplacement(int pages[], int n, int frame_size) {
    int frames[frame_size];
    int page_faults = 0;
    int page_hits = 0;
    int fifo_index = 0; // Points to the oldest page (next to be replaced)
    
    // Initialize frames to -1 (empty)
    for(int i = 0; i < frame_size; i++) {
        frames[i] = -1;
    }
    
    printf("\nFIFO Page Replacement Simulation:\n");
    printf("Page\tFrames\t\t\tStatus\n");
    printf("----\t------\t\t\t------\n");
    
    for(int i = 0; i < n; i++) {
        printf("%d\t", pages[i]);
        
        if(isPagePresent(frames, frame_size, pages[i])) {
            // Page hit
            page_hits++;
            displayPageFrames(frames, frame_size);
            printf("\tPage Hit\n");
        } else {
            // Page fault
            page_faults++;
            
            int empty_frame = findEmptyFrame(frames, frame_size);
            
            if(empty_frame != -1) {
                // Empty frame available
                frames[empty_frame] = pages[i];
            } else {
                // No empty frame, replace using FIFO
                frames[fifo_index] = pages[i];
                fifo_index = (fifo_index + 1) % frame_size;
            }
            
            displayPageFrames(frames, frame_size);
            printf("\tPage Fault\n");
        }
    }
    
    printf("\n=== FIFO Page Replacement Results ===\n");
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

int main() {
    int n, frame_size;
    
    printf("=== FIFO (First In First Out) Page Replacement Algorithm ===\n");
    
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
    
    // Simulate FIFO page replacement
    fifoPageReplacement(pages, n, frame_size);
    
    printf("\nNote: FIFO replaces the oldest page in memory when a page fault occurs.\n");
    printf("The algorithm maintains a queue of pages in the order they were loaded.\n");
    
    return 0;
}
