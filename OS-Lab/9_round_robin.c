#include <stdio.h>

// Round Robin CPU Scheduling Algorithm with arrival time
int main() {
    int n, i, tq, t = 0, completed = 0;
    printf("Enter number of processes: ");
    scanf("%d", &n);

    // Arrays to store arrival time, burst time, remaining time, completion time, turnaround time, waiting time, and completion status
    int at[n], bt[n], rt[n], ct[n], tat[n], wt[n], done[n];

    // Input arrival times
    printf("Enter arrival times:\n");
    for(i = 0; i < n; i++)
        scanf("%d", &at[i]);

    // Input burst times and initialize remaining time and done status
    printf("Enter burst times:\n");
    for(i = 0; i < n; i++) {
        scanf("%d", &bt[i]);
        rt[i] = bt[i];    // Remaining time initially equals burst time
        done[i] = 0;      // 0 means not completed
    }

    // Input time quantum
    printf("Enter time quantum: ");
    scanf("%d", &tq);

    int flag;
    // Main loop continues until all processes are completed
    while(completed < n) {
        flag = 0; // To check if any process was executed in this cycle

        // Traverse all processes in order
        for(i = 0; i < n; i++) {
            // If process has arrived and is not yet completed
            if(rt[i] > 0 && at[i] <= t) {
                flag = 1; // At least one process is ready to execute

                // If remaining time is more than time quantum, execute for time quantum
                if(rt[i] > tq) {
                    t += tq;
                    rt[i] -= tq;
                } else {
                    // If remaining time is less than or equal to time quantum, process completes
                    t += rt[i];
                    ct[i] = t;      // Record completion time
                    rt[i] = 0;      // No remaining time
                    done[i] = 1;    // Mark as completed
                    completed++;    // Increment completed count
                }
            }
        }
        // If no process was ready, increment time (CPU idle)
        if(!flag) t++;
    }

    // Calculate turnaround time and waiting time for each process
    for(i = 0; i < n; i++) {
        tat[i] = ct[i] - at[i];      // Turnaround time = completion - arrival
        wt[i] = tat[i] - bt[i];      // Waiting time = turnaround - burst
    }

    // Output the results
    printf("P\tAT\tBT\tWT\tTAT\n");
    for(i = 0; i < n; i++)
        printf("%d\t%d\t%d\t%d\t%d\n", i+1, at[i], bt[i], wt[i], tat[i]);
    return 0;
}
