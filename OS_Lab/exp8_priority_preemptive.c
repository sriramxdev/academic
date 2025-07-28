#include <stdio.h>
#include <limits.h>

// Preemptive Priority CPU Scheduling Algorithm
// Lower priority number indicates higher priority

typedef struct {
    int pid;
    int at;
    int bt;
    int priority;
    int rt;
    int wt;
    int tat;
    int ct;
    int resp_time;
    int first_resp;
} Process;

void calculateTimes(Process processes[], int n) {
    int current_time = 0;
    int completed = 0;
    
    // Initialize remaining times and response times
    for(int i = 0; i < n; i++) {
        processes[i].rt = processes[i].bt;
        processes[i].first_resp = -1;
    }
    
    printf("\nExecution Timeline (Preemptive):\n");
    printf("Time\tProcess\tPriority\tRemaining Time\n");
    printf("----\t-------\t--------\t--------------\n");
    
    while(completed < n) {
        int highest = -1;
        int min_priority = INT_MAX;
        
        // Find process with highest priority (lowest priority number)
        for(int i = 0; i < n; i++) {
            if(processes[i].at <= current_time && 
               processes[i].rt > 0 && 
               processes[i].priority < min_priority) {
                min_priority = processes[i].priority;
                highest = i;
            }
        }
        
        if(highest == -1) {
            current_time++;
            continue;
        }
        
        // Record first response time
        if(processes[highest].first_resp == -1) {
            processes[highest].first_resp = current_time;
            processes[highest].resp_time = current_time - processes[highest].at;
        }
        
        // Execute for 1 time unit
        processes[highest].rt--;
        current_time++;
        
        printf("%d\tP%d\t%d\t\t%d\n", current_time-1, processes[highest].pid, 
               processes[highest].priority, processes[highest].rt);
        
        // Check if process is completed
        if(processes[highest].rt == 0) {
            processes[highest].ct = current_time;
            processes[highest].tat = processes[highest].ct - processes[highest].at;
            processes[highest].wt = processes[highest].tat - processes[highest].bt;
            completed++;
        }
    }
}

void displayResults(Process processes[], int n) {
    printf("\nProcess ID\tArrival\tBurst\tPriority\tWaiting\tTurnaround\tCompletion\tResponse\n");
    printf("----------\t-------\t-----\t--------\t-------\t----------\t----------\t--------\n");
    
    float total_wt = 0, total_tat = 0, total_rt = 0;
    
    for(int i = 0; i < n; i++) {
        printf("P%d\t\t%d\t%d\t%d\t\t%d\t%d\t\t%d\t\t%d\n", 
               processes[i].pid, processes[i].at, processes[i].bt,
               processes[i].priority, processes[i].wt, 
               processes[i].tat, processes[i].ct, processes[i].resp_time);
        
        total_wt += processes[i].wt;
        total_tat += processes[i].tat;
        total_rt += processes[i].resp_time;
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
    
    printf("\nPriorities: ");
    for(int i = 0; i < n; i++) {
        printf("P%d(%d) ", temp[i].pid, temp[i].priority);
    }
    printf("\n");
}

int main() {
    int n;
    
    printf("=== Preemptive Priority CPU Scheduling Algorithm ===\n");
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
    }
    
    // Calculate waiting and turnaround times
    calculateTimes(processes, n);
    
    // Display results
    displayResults(processes, n);
    
    // Display Gantt chart
    displayGanttChart(processes, n);
    
    return 0;
}
