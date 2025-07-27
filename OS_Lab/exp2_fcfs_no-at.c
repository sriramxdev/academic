#include <stdio.h>

void main(){
    int n,i;
    float avg_wt = 0, avg_tat = 0;
    printf("Enter the number of processes : ");
    scanf("%d",&n);
    int bt[n], wt[n], tat[n];
    for(int i = 0; i<n; i++){
        printf("Enter the burst time for process P%d : ",i);
        scanf("%d",&bt[i]);
    }   
    wt[0] = 0;
    for (i = 1; i<n; i++){
        wt[i] = wt[i-1] + bt[i -1];
    }
    for(i = 0;i<n;i++){
        tat[i] = wt[i] + bt[i];
    }
    printf("Process ID\t\tBurst Time\t\tWaiting Time\t\tTurnaround Time\n");
    for(i = 0;i<n;i++){
        printf("P%d\t\t\t%d\t\t\t%d\t\t\t%d\n",i,bt[i],wt[i],tat[i]);
        avg_wt += wt[i];
        avg_tat += tat[i];
    }
    avg_wt = avg_wt/n;
    avg_tat = avg_tat/n;
    printf("\nThe Average Waiting Time is : %.2f\n",avg_wt);
    printf("The Average Turnaround Time is : %.2f\n",avg_tat);
}