#include <stdio.h>
#include <limits.h>

// SJF CPU Scheduling Algorithm with arrival time
// This program implements Shortest Job First scheduling considering arrival times

typedef struct {
    int pid;
    int arrival_time;
    int burst_time;
    int waiting_time;
    int turnaround_time;
    int completion_time;
    int is_completed;
} Process;

int findShortestJob(Process processes[], int n, int current_time) {
    int shortest = -1;
    int min_burst = INT_MAX;
    
    for(int i = 0; i < n; i++) {
        if(!processes[i].is_completed && 
           processes[i].arrival_time <= current_time && 
           processes[i].burst_time < min_burst) {
            min_burst = processes[i].burst_time;
            shortest = i;
        }
    }
    
    return shortest;
}

void calculateTimes(Process processes[], int n) {
    int current_time = 0;
    int completed = 0;
    
    while(completed < n) {
        int shortest = findShortestJob(processes, n, current_time);
        
        if(shortest == -1) {
            // No process available, move time forward
            current_time++;
            continue;
        }
        
        // Execute the shortest job
        current_time += processes[shortest].burst_time;
        processes[shortest].completion_time = current_time;
        processes[shortest].turnaround_time = processes[shortest].completion_time - processes[shortest].arrival_time;
        processes[shortest].waiting_time = processes[shortest].turnaround_time - processes[shortest].burst_time;
        processes[shortest].is_completed = 1;
        completed++;
    }
}

void displayResults(Process processes[], int n) {
    printf("\nProcess ID\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time\tCompletion Time\n");
    printf("----------\t------------\t----------\t------------\t---------------\t---------------\n");
    
    float total_wt = 0, total_tat = 0;
    
    for(int i = 0; i < n; i++) {
        printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", 
               processes[i].pid, processes[i].arrival_time, processes[i].burst_time,
               processes[i].waiting_time, processes[i].turnaround_time, processes[i].completion_time);
        
        total_wt += processes[i].waiting_time;
        total_tat += processes[i].turnaround_time;
    }
    
    printf("\nAverage Waiting Time: %.2f\n", total_wt / n);
    printf("Average Turnaround Time: %.2f\n", total_tat / n);
}

void displayExecutionOrder(Process processes[], int n) {
    // Create a temporary array to sort by completion time for execution order
    Process temp[n];
    for(int i = 0; i < n; i++) {
        temp[i] = processes[i];
    }
    
    // Sort by completion time to show execution order
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(temp[j].completion_time > temp[j+1].completion_time) {
                Process t = temp[j];
                temp[j] = temp[j+1];
                temp[j+1] = t;
            }
        }
    }
    
    printf("\nExecution Order (SJF): ");
    for(int i = 0; i < n; i++) {
        printf("P%d ", temp[i].pid);
    }
    printf("\n");
}

int main() {
    int n;
    
    printf("=== SJF CPU Scheduling Algorithm (With Arrival Time) ===\n");
    printf("Enter the number of processes: ");
    scanf("%d", &n);
    
    Process processes[n];
    
    // Input process details
    for(int i = 0; i < n; i++) {
        processes[i].pid = i + 1;
        printf("Enter arrival time for process P%d: ", i + 1);
        scanf("%d", &processes[i].arrival_time);
        printf("Enter burst time for process P%d: ", i + 1);
        scanf("%d", &processes[i].burst_time);
        processes[i].is_completed = 0;
    }
    
    // Calculate waiting and turnaround times
    calculateTimes(processes, n);
    
    // Display results
    displayResults(processes, n);
    
    // Display execution order
    displayExecutionOrder(processes, n);
    
    return 0;
}
