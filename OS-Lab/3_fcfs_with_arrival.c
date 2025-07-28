#include <stdio.h>

int main() {
    int n, i, j;
    printf("Enter number of processes: ");
    scanf("%d", &n);
    int at[n], bt[n], wt[n], tat[n], ct[n];
    printf("Enter arrival times:\n");
    for(i = 0; i < n; i++)
        scanf("%d", &at[i]);
    printf("Enter burst times:\n");
    for(i = 0; i < n; i++)
        scanf("%d", &bt[i]);
    // Sort by arrival time
    for(i = 0; i < n-1; i++)
        for(j = i+1; j < n; j++)
            if(at[i] > at[j]) {
                int t = at[i]; at[i] = at[j]; at[j] = t;
                t = bt[i]; bt[i] = bt[j]; bt[j] = t;
            }
    ct[0] = at[0] + bt[0];
    for(i = 1; i < n; i++)
        ct[i] = (ct[i-1] > at[i] ? ct[i-1] : at[i]) + bt[i];
    for(i = 0; i < n; i++) {
        tat[i] = ct[i] - at[i];
        wt[i] = tat[i] - bt[i];
    }
    printf("P\tAT\tBT\tWT\tTAT\n");
    for(i = 0; i < n; i++)
        printf("%d\t%d\t%d\t%d\t%d\n", i+1, at[i], bt[i], wt[i], tat[i]);
    return 0;
}
