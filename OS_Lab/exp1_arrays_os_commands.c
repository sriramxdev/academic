#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Program to implement various operations on arrays and execute OS commands using CLI
// This program demonstrates array operations and system command execution

void displayArray(int arr[], int size) {
    printf("Array elements: ");
    for(int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void insertElement(int arr[], int *size, int element, int position) {
    if(position < 0 || position > *size) {
        printf("Invalid position!\n");
        return;
    }
    
    for(int i = *size; i > position; i--) {
        arr[i] = arr[i-1];
    }
    arr[position] = element;
    (*size)++;
    printf("Element %d inserted at position %d\n", element, position);
}

void deleteElement(int arr[], int *size, int position) {
    if(position < 0 || position >= *size) {
        printf("Invalid position!\n");
        return;
    }
    
    int deletedElement = arr[position];
    for(int i = position; i < *size - 1; i++) {
        arr[i] = arr[i+1];
    }
    (*size)--;
    printf("Element %d deleted from position %d\n", deletedElement, position);
}

int searchElement(int arr[], int size, int element) {
    for(int i = 0; i < size; i++) {
        if(arr[i] == element) {
            return i;
        }
    }
    return -1;
}

void sortArray(int arr[], int size) {
    for(int i = 0; i < size - 1; i++) {
        for(int j = 0; j < size - i - 1; j++) {
            if(arr[j] > arr[j+1]) {
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
    printf("Array sorted successfully!\n");
}

void executeOSCommand() {
    char command[100];
    printf("\nEnter OS command to execute: ");
    getchar(); // consume newline
    fgets(command, sizeof(command), stdin);
    command[strcspn(command, "\n")] = 0; // remove newline
    
    printf("Executing command: %s\n", command);
    int result = system(command);
    if(result == -1) {
        printf("Failed to execute command!\n");
    }
}

int main() {
    int arr[100], size = 0, choice, element, position;
    
    printf("=== Array Operations and OS Commands Program ===\n");
    
    while(1) {
        printf("\n--- Menu ---\n");
        printf("1. Insert element\n");
        printf("2. Delete element\n");
        printf("3. Search element\n");
        printf("4. Display array\n");
        printf("5. Sort array\n");
        printf("6. Execute OS command\n");
        printf("7. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        
        switch(choice) {
            case 1:
                printf("Enter element to insert: ");
                scanf("%d", &element);
                printf("Enter position (0 to %d): ", size);
                scanf("%d", &position);
                insertElement(arr, &size, element, position);
                break;
                
            case 2:
                if(size == 0) {
                    printf("Array is empty!\n");
                    break;
                }
                displayArray(arr, size);
                printf("Enter position to delete (0 to %d): ", size-1);
                scanf("%d", &position);
                deleteElement(arr, &size, position);
                break;
                
            case 3:
                if(size == 0) {
                    printf("Array is empty!\n");
                    break;
                }
                printf("Enter element to search: ");
                scanf("%d", &element);
                position = searchElement(arr, size, element);
                if(position != -1) {
                    printf("Element %d found at position %d\n", element, position);
                } else {
                    printf("Element %d not found in array\n", element);
                }
                break;
                
            case 4:
                if(size == 0) {
                    printf("Array is empty!\n");
                } else {
                    displayArray(arr, size);
                }
                break;
                
            case 5:
                if(size == 0) {
                    printf("Array is empty!\n");
                } else {
                    sortArray(arr, size);
                    displayArray(arr, size);
                }
                break;
                
            case 6:
                executeOSCommand();
                break;
                
            case 7:
                printf("Exiting program...\n");
                exit(0);
                
            default:
                printf("Invalid choice! Please try again.\n");
        }
    }
    
    return 0;
}
