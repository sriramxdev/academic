#include <stdio.h>

// SJF CPU Scheduling Algorithm without arrival time
// This program implements Shortest Job First scheduling without considering arrival times

typedef struct {
    int pid;
    int bt;
    int wt;
    int tat;
} Process;

void sortByBurstTime(Process processes[], int n) {
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(processes[j].bt > processes[j+1].bt) {
                Process temp = processes[j];
                processes[j] = processes[j+1];
                processes[j+1] = temp;
            }
        }
    }
}

void calculateTimes(Process processes[], int n) {
    // Calculate waiting times
    processes[0].wt = 0;
    for(int i = 1; i < n; i++) {
        processes[i].wt = processes[i-1].wt + processes[i-1].bt;
    }
    
    // Calculate turnaround times
    for(int i = 0; i < n; i++) {
        processes[i].tat = processes[i].wt + processes[i].bt;
    }
}

void displayResults(Process processes[], int n) {
    printf("\nProcess ID\tBurst Time\tWaiting Time\tTurnaround Time\n");
    printf("----------\t----------\t------------\t---------------\n");
    
    float total_wt = 0, total_tat = 0;
    
    for(int i = 0; i < n; i++) {
        printf("P%d\t\t%d\t\t%d\t\t%d\n", 
               processes[i].pid, processes[i].bt,
               processes[i].wt, processes[i].tat);
        
        total_wt += processes[i].wt;
        total_tat += processes[i].tat;
    }
    
    printf("\nAverage Waiting Time: %.2f\n", total_wt / n);
    printf("Average Turnaround Time: %.2f\n", total_tat / n);
}

void displayGanttChart(Process processes[], int n) {
    printf("\nGantt Chart:\n");
    printf("|");
    for(int i = 0; i < n; i++) {
        printf(" P%d |", processes[i].pid);
    }
    printf("\n");
    
    printf("0");
    int time = 0;
    for(int i = 0; i < n; i++) {
        time += processes[i].bt;
        printf("   %d", time);
    }
    printf("\n");
}

int main() {
    int n;
    
    printf("=== SJF CPU Scheduling Algorithm (Without Arrival Time) ===\n");
    printf("Enter the number of processes: ");
    scanf("%d", &n);
    
    Process processes[n];
    
    // Input process details
    for(int i = 0; i < n; i++) {
        processes[i].pid = i + 1;
        printf("Enter burst time for process P%d: ", i + 1);
        scanf("%d", &processes[i].bt);
    }
    
    printf("\nOriginal process order:\n");
    for(int i = 0; i < n; i++) {
        printf("P%d: %d  ", processes[i].pid, processes[i].bt);
    }
    printf("\n");
    
    // Sort processes by burst time (SJF)
    sortByBurstTime(processes, n);
    
    printf("\nAfter sorting by burst time (SJF order):\n");
    for(int i = 0; i < n; i++) {
        printf("P%d: %d  ", processes[i].pid, processes[i].bt);
    }
    printf("\n");
    
    // Calculate waiting and turnaround times
    calculateTimes(processes, n);
    
    // Display results
    displayResults(processes, n);
    
    // Display Gantt chart
    displayGanttChart(processes, n);
    
    return 0;
}
