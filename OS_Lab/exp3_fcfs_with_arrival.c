#include <stdio.h>

// FCFS CPU Scheduling Algorithm with arrival time
// This program calculates waiting time and turnaround time considering arrival times

typedef struct {
    int pid;
    int at;
    int bt;
    int wt;
    int tat;
    int ct;
} Process;

void sortByArrivalTime(Process processes[], int n) {
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(processes[j].at > processes[j+1].at) {
                Process temp = processes[j];
                processes[j] = processes[j+1];
                processes[j+1] = temp;
            }
        }
    }
}

void calculateTimes(Process processes[], int n) {
    int current_time = 0;
    
    for(int i = 0; i < n; i++) {
        // If CPU is idle, move time to next process arrival
        if(current_time < processes[i].at) {
            current_time = processes[i].at;
        }
        
        // Calculate completion time
        processes[i].ct = current_time + processes[i].bt;
        
        // Calculate turnaround time and waiting time
        processes[i].tat = processes[i].ct - processes[i].at;
        processes[i].wt = processes[i].tat - processes[i].bt;
        
        // Update current time
        current_time = processes[i].ct;
    }
}

void displayResults(Process processes[], int n) {
    printf("\nProcess ID\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time\tCompletion Time\n");
    printf("----------\t------------\t----------\t------------\t---------------\t---------------\n");
    
    float total_wt = 0, total_tat = 0;
    
    for(int i = 0; i < n; i++) {
        printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", 
               processes[i].pid, processes[i].at, processes[i].bt,
               processes[i].wt, processes[i].tat, processes[i].ct);
        
        total_wt += processes[i].wt;
        total_tat += processes[i].tat;
    }
    
    printf("\nAverage Waiting Time: %.2f\n", total_wt / n);
    printf("Average Turnaround Time: %.2f\n", total_tat / n);
}

int main() {
    int n;
    
    printf("=== FCFS CPU Scheduling Algorithm (With Arrival Time) ===\n");
    printf("Enter the number of processes: ");
    scanf("%d", &n);
    
    Process processes[n];
    
    // Input process details
    for(int i = 0; i < n; i++) {
        processes[i].pid = i + 1;
        printf("Enter arrival time for process P%d: ", i + 1);
        scanf("%d", &processes[i].at);
        printf("Enter burst time for process P%d: ", i + 1);
        scanf("%d", &processes[i].bt);
    }
    
    // Sort processes by arrival time
    sortByArrivalTime(processes, n);
    
    // Calculate waiting and turnaround times
    calculateTimes(processes, n);
    
    // Display results
    displayResults(processes, n);
    
    return 0;
}
