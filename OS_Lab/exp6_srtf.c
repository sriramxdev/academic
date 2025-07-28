#include <stdio.h>
#include <limits.h>

// SRTF (Shortest Remaining Time First) CPU Scheduling Algorithm
// This is a preemptive version of SJF scheduling

typedef struct {
    int pid;
    int arrival_time;
    int burst_time;
    int remaining_time;
    int waiting_time;
    int turnaround_time;
    int completion_time;
    int response_time;
    int first_response;
} Process;

void calculateTimes(Process processes[], int n) {
    int current_time = 0;
    int completed = 0;
    int prev_process = -1;
    
    // Initialize remaining times and response times
    for(int i = 0; i < n; i++) {
        processes[i].remaining_time = processes[i].burst_time;
        processes[i].first_response = -1;
    }
    
    printf("\nTime\tProcess\tRemaining Time\n");
    printf("----\t-------\t--------------\n");
    
    while(completed < n) {
        int shortest = -1;
        int min_remaining = INT_MAX;
        
        // Find process with shortest remaining time
        for(int i = 0; i < n; i++) {
            if(processes[i].arrival_time <= current_time && 
               processes[i].remaining_time > 0 && 
               processes[i].remaining_time < min_remaining) {
                min_remaining = processes[i].remaining_time;
                shortest = i;
            }
        }
        
        if(shortest == -1) {
            current_time++;
            continue;
        }
        
        // Record first response time
        if(processes[shortest].first_response == -1) {
            processes[shortest].first_response = current_time;
            processes[shortest].response_time = current_time - processes[shortest].arrival_time;
        }
        
        // Execute for 1 time unit
        processes[shortest].remaining_time--;
        current_time++;
        
        printf("%d\tP%d\t%d\n", current_time-1, processes[shortest].pid, processes[shortest].remaining_time);
        
        // Check if process is completed
        if(processes[shortest].remaining_time == 0) {
            processes[shortest].completion_time = current_time;
            processes[shortest].turnaround_time = processes[shortest].completion_time - processes[shortest].arrival_time;
            processes[shortest].waiting_time = processes[shortest].turnaround_time - processes[shortest].burst_time;
            completed++;
        }
    }
}

void displayResults(Process processes[], int n) {
    printf("\nProcess ID\tArrival\tBurst\tWaiting\tTurnaround\tCompletion\tResponse\n");
    printf("----------\t-------\t-----\t-------\t----------\t----------\t--------\n");
    
    float total_wt = 0, total_tat = 0, total_rt = 0;
    
    for(int i = 0; i < n; i++) {
        printf("P%d\t\t%d\t%d\t%d\t%d\t\t%d\t\t%d\n", 
               processes[i].pid, processes[i].arrival_time, processes[i].burst_time,
               processes[i].waiting_time, processes[i].turnaround_time, 
               processes[i].completion_time, processes[i].response_time);
        
        total_wt += processes[i].waiting_time;
        total_tat += processes[i].turnaround_time;
        total_rt += processes[i].response_time;
    }
    
    printf("\nAverage Waiting Time: %.2f\n", total_wt / n);
    printf("Average Turnaround Time: %.2f\n", total_tat / n);
    printf("Average Response Time: %.2f\n", total_rt / n);
}

void displayGanttChart(Process processes[], int n) {
    printf("\nGantt Chart:\n");
    printf("Note: This shows the final completion order, not the actual preemption timeline\n");
    
    // Sort by completion time for display
    Process temp[n];
    for(int i = 0; i < n; i++) {
        temp[i] = processes[i];
    }
    
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(temp[j].completion_time > temp[j+1].completion_time) {
                Process t = temp[j];
                temp[j] = temp[j+1];
                temp[j+1] = t;
            }
        }
    }
    
    printf("|");
    for(int i = 0; i < n; i++) {
        printf(" P%d |", temp[i].pid);
    }
    printf("\n");
    
    printf("0");
    for(int i = 0; i < n; i++) {
        printf("   %d", temp[i].completion_time);
    }
    printf("\n");
}

int main() {
    int n;
    
    printf("=== SRTF (Shortest Remaining Time First) CPU Scheduling Algorithm ===\n");
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
    }
    
    // Calculate waiting and turnaround times
    calculateTimes(processes, n);
    
    // Display results
    displayResults(processes, n);
    
    // Display Gantt chart
    displayGanttChart(processes, n);
    
    return 0;
}
