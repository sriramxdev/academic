#include <stdio.h>

// Non-preemptive Priority CPU Scheduling Algorithm
// Lower priority number indicates higher priority

typedef struct {
    int pid;
    int arrival_time;
    int burst_time;
    int priority;
    int waiting_time;
    int turnaround_time;
    int completion_time;
    int is_completed;
} Process;

int findHighestPriority(Process processes[], int n, int current_time) {
    int highest = -1;
    int min_priority = 1000; // Assume max priority value
    
    for(int i = 0; i < n; i++) {
        if(!processes[i].is_completed && 
           processes[i].arrival_time <= current_time && 
           processes[i].priority < min_priority) {
            min_priority = processes[i].priority;
            highest = i;
        }
    }
    
    return highest;
}

void calculateTimes(Process processes[], int n) {
    int current_time = 0;
    int completed = 0;
    
    printf("\nExecution Timeline:\n");
    printf("Time\tProcess\tPriority\tBurst Time\n");
    printf("----\t-------\t--------\t----------\n");
    
    while(completed < n) {
        int highest = findHighestPriority(processes, n, current_time);
        
        if(highest == -1) {
            // No process available, move time forward
            current_time++;
            continue;
        }
        
        printf("%d\tP%d\t%d\t\t%d\n", current_time, processes[highest].pid, 
               processes[highest].priority, processes[highest].burst_time);
        
        // Execute the highest priority process completely (non-preemptive)
        current_time += processes[highest].burst_time;
        processes[highest].completion_time = current_time;
        processes[highest].turnaround_time = processes[highest].completion_time - processes[highest].arrival_time;
        processes[highest].waiting_time = processes[highest].turnaround_time - processes[highest].burst_time;
        processes[highest].is_completed = 1;
        completed++;
    }
}

void displayResults(Process processes[], int n) {
    printf("\nProcess ID\tArrival\tBurst\tPriority\tWaiting\tTurnaround\tCompletion\n");
    printf("----------\t-------\t-----\t--------\t-------\t----------\t----------\n");
    
    float total_wt = 0, total_tat = 0;
    
    for(int i = 0; i < n; i++) {
        printf("P%d\t\t%d\t%d\t%d\t\t%d\t%d\t\t%d\n", 
               processes[i].pid, processes[i].arrival_time, processes[i].burst_time,
               processes[i].priority, processes[i].waiting_time, 
               processes[i].turnaround_time, processes[i].completion_time);
        
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
    
    printf("\nExecution Order (Priority-based): ");
    for(int i = 0; i < n; i++) {
        printf("P%d(Priority:%d) ", temp[i].pid, temp[i].priority);
    }
    printf("\n");
    
    // Gantt Chart
    printf("\nGantt Chart:\n");
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
    
    printf("=== Non-preemptive Priority CPU Scheduling Algorithm ===\n");
    printf("Note: Lower priority number indicates higher priority\n");
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
        printf("Enter priority for process P%d (lower number = higher priority): ", i + 1);
        scanf("%d", &processes[i].priority);
        processes[i].is_completed = 0;
    }
    
    // Calculate waiting and turnaround times
    calculateTimes(processes, n);
    
    // Display results
    displayResults(processes, n);
    
    // Display execution order and Gantt chart
    displayExecutionOrder(processes, n);
    
    return 0;
}
