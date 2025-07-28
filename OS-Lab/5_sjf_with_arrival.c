#include <stdio.h>

int main() {
    int n, i, j, completed = 0, t = 0, min, idx;
    printf("Enter number of processes: ");
    scanf("%d", &n);
    int at[n], bt[n], ct[n], tat[n], wt[n], done[n];
    printf("Enter arrival times:\n");
    for(i = 0; i < n; i++)
        scanf("%d", &at[i]);
    printf("Enter burst times:\n");
    for(i = 0; i < n; i++)
        scanf("%d", &bt[i]);
    for(i = 0; i < n; i++) done[i] = 0;
    while(completed < n) {
        min = 1e9; idx = -1;
        for(i = 0; i < n; i++)
            if(!done[i] && at[i] <= t && bt[i] < min) {
                min = bt[i];
                idx = i;
            }
        if(idx == -1) { t++; continue; }
        t += bt[idx];
        ct[idx] = t;
        done[idx] = 1;
        completed++;
    }
    for(i = 0; i < n; i++) {
        tat[i] = ct[i] - at[i];
        wt[i] = tat[i] - bt[i];
    }
    printf("P\tAT\tBT\tWT\tTAT\n");
    for(i = 0; i < n; i++)
        printf("%d\t%d\t%d\t%d\t%d\n", i+1, at[i], bt[i], wt[i], tat[i]);
    return 0;
}
