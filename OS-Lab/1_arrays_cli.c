#include <stdio.h>
#include <stdlib.h>

int main() {
    int arr[100], n, i, sum = 0;
    printf("Enter number of elements: ");
    scanf("%d", &n);
    printf("Enter %d elements:\n", n);
    for(i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
        sum += arr[i];
    }
    printf("Sum = %d\n", sum);

    // Execute an OS command
    char cmd[100];
    printf("Enter an OS command to execute: ");
    scanf(" %[^\n]", cmd);
    system(cmd);

    return 0;
}
