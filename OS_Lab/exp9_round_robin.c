#include <stdio.h>

// Round Robin CPU Scheduling Algorithm
// Each process gets a fixed time quantum

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

void calculateTimes(Process processes[], int n, int quantum) {
    int current_time = 0;
    int completed = 0;
    int queue[100], front = 0, rear = 0;
    int visited[100] = {0};
    
    // Initialize remaining times and response times
    for(int i = 0; i < n; i++) {
        processes[i].remaining_time = processes[i].burst_time;
        processes[i].first_response = -1;
    }
    
    // Add processes that arrive at time 0
    for(int i = 0; i < n; i++) {
        if(processes[i].arrival_time <= current_time) {
            queue[rear++] = i;
            visited[i] = 1;
        }
    }
    
    printf("\nExecution Timeline:\n");
    printf("Time\tProcess\tRemaining\tQuantum Used\n");
    printf("----\t-------\t---------\t------------\n");
    
    while(completed < n) {
        if(front == rear) {
            // Queue is empty, advance time and check for new arrivals
            current_time++;
            for(int i = 0; i < n; i++) {
                if(processes[i].arrival_time <= current_time && !visited[i] && processes[i].remaining_time > 0) {
                    queue[rear++] = i;
                    visited[i] = 1;
                }
            }
            continue;
        }
        
        int current_process = queue[front++];
        
        // Record first response time
        if(processes[current_process].first_response == -1) {
            processes[current_process].first_response = current_time;
            processes[current_process].response_time = current_time - processes[current_process].arrival_time;
        }
        
        // Execute for quantum time or remaining time, whichever is smaller
        int execution_time = (processes[current_process].remaining_time > quantum) ? quantum : processes[current_process].remaining_time;
        
        printf("%d\tP%d\t%d\t\t%d\n", current_time, processes[current_process].pid, 
               processes[current_process].remaining_time, execution_time);
        
        processes[current_process].remaining_time -= execution_time;
        current_time += execution_time;
        
        // Add newly arrived processes to queue
        for(int i = 0; i < n; i++) {
            if(processes[i].arrival_time <= current_time && !visited[i] && processes[i].remaining_time > 0) {
                queue[rear++] = i;
                visited[i] = 1;
            }
        }
        
        // Check if current process is completed
        if(processes[current_process].remaining_time == 0) {
            processes[current_process].completion_time = current_time;
            processes[current_process].turnaround_time = processes[current_process].completion_time - processes[current_process].arrival_time;
            processes[current_process].waiting_time = processes[current_process].turnaround_time - processes[current_process].burst_time;
            completed++;
        } else {
            // Add back to queue if not completed
            queue[rear++] = current_process;
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
    printf("\nGantt Chart (Final completion order):\n");
    
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
    int n, quantum;
    
    printf("=== Round Robin CPU Scheduling Algorithm ===\n");
    printf("Enter the number of processes: ");
    scanf("%d", &n);
    
    printf("Enter the time quantum: ");
    scanf("%d", &quantum);
    
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
    calculateTimes(processes, n, quantum);
    
    // Display results
    displayResults(processes, n);
    
    // Display Gantt chart
    displayGanttChart(processes, n);
    
    printf("\nNote: The actual execution involves multiple time slices per process.\n");
    printf("Each process runs for a maximum of %d time units before being preempted.\n", quantum);
    
    return 0;
}
