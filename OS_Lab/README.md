# OS Lab Programs - C Implementation

This repository contains implementations of 11 Operating System concepts using C programming language.

## Program List

### 1. Arrays Operations and OS Commands (`exp1_arrays_os_commands.c`)
- **Description**: Implements various array operations (insert, delete, search, sort) and executes OS commands using CLI
- **Features**: 
  - Interactive menu-driven program
  - Array manipulation functions
  - System command execution
- **Usage**: Demonstrates basic data structures and system calls

### 2. FCFS Without Arrival Time (`exp2_fcfs_no-at.c`)
- **Description**: First Come First Serve CPU scheduling without considering arrival times
- **Features**: 
  - Calculates waiting time and turnaround time
  - Displays process execution order
- **Concept**: Basic CPU scheduling algorithm

### 3. FCFS With Arrival Time (`exp3_fcfs_with_arrival.c`)
- **Description**: First Come First Serve CPU scheduling with arrival times
- **Features**: 
  - Sorts processes by arrival time
  - Handles CPU idle time
  - Calculates completion, waiting, and turnaround times
- **Concept**: Real-world CPU scheduling with process arrival

### 4. SJF Without Arrival Time (`exp4_sjf_no_arrival.c`)
- **Description**: Shortest Job First (non-preemptive) without arrival times
- **Features**: 
  - Sorts processes by burst time
  - Displays Gantt chart
  - Shows execution order
- **Concept**: Optimal scheduling for minimizing average waiting time

### 5. SJF With Arrival Time (`exp5_sjf_with_arrival.c`)
- **Description**: Shortest Job First (non-preemptive) with arrival times
- **Features**: 
  - Considers both arrival time and burst time
  - Dynamic process selection
  - Execution order tracking
- **Concept**: SJF with realistic process arrival

### 6. SRTF - Shortest Remaining Time First (`exp6_srtf.c`)
- **Description**: Preemptive version of SJF algorithm
- **Features**: 
  - Process preemption based on remaining time
  - Real-time execution timeline
  - Response time calculation
- **Concept**: Preemptive scheduling for better responsiveness

### 7. Non-Preemptive Priority Scheduling (`exp7_priority_non_preemptive.c`)
- **Description**: Priority-based CPU scheduling without preemption
- **Features**: 
  - Priority-based process selection
  - Lower number = higher priority
  - Execution timeline display
- **Concept**: Priority-driven scheduling

### 8. Preemptive Priority Scheduling (`exp8_priority_preemptive.c`)
- **Description**: Priority-based CPU scheduling with preemption
- **Features**: 
  - Dynamic priority-based preemption
  - Response time tracking
  - Real-time execution monitoring
- **Concept**: Preemptive priority scheduling

### 9. Round Robin Scheduling (`exp9_round_robin.c`)
- **Description**: Time-quantum based CPU scheduling
- **Features**: 
  - Fixed time quantum execution
  - Queue-based process management
  - Fair CPU time distribution
- **Concept**: Time-sharing systems

### 10. FIFO Page Replacement (`exp10_fifo_page_replacement.c`)
- **Description**: First In First Out page replacement algorithm
- **Features**: 
  - Page fault simulation
  - Memory frame visualization
  - Hit/miss ratio calculation
- **Concept**: Memory management and virtual memory

### 11. LRU Page Replacement (`exp11_lru_page_replacement.c`)
- **Description**: Least Recently Used page replacement algorithm
- **Features**: 
  - Timestamp-based page tracking
  - LRU logic demonstration
  - Detailed memory state display
- **Concept**: Advanced memory management strategy

## Compilation Instructions

### Individual Program Compilation
```bash
# For any single program
gcc -o program_name program_name.c

# Examples:
gcc -o exp1 exp1_arrays_os_commands.c
gcc -o fcfs_arrival exp3_fcfs_with_arrival.c
gcc -o sjf exp4_sjf_no_arrival.c
```

### Compile All Programs
```bash
# Windows (PowerShell)
gcc -o exp1_arrays_os_commands exp1_arrays_os_commands.c
gcc -o exp2_fcfs_no-at exp2_fcfs_no-at.c
gcc -o exp3_fcfs_with_arrival exp3_fcfs_with_arrival.c
gcc -o exp4_sjf_no_arrival exp4_sjf_no_arrival.c
gcc -o exp5_sjf_with_arrival exp5_sjf_with_arrival.c
gcc -o exp6_srtf exp6_srtf.c
gcc -o exp7_priority_non_preemptive exp7_priority_non_preemptive.c
gcc -o exp8_priority_preemptive exp8_priority_preemptive.c
gcc -o exp9_round_robin exp9_round_robin.c
gcc -o exp10_fifo_page_replacement exp10_fifo_page_replacement.c
gcc -o exp11_lru_page_replacement exp11_lru_page_replacement.c

# Linux/Unix
gcc -o exp1_arrays_os_commands exp1_arrays_os_commands.c
gcc -o exp2_fcfs_no-at exp2_fcfs_no-at.c
gcc -o exp3_fcfs_with_arrival exp3_fcfs_with_arrival.c
gcc -o exp4_sjf_no_arrival exp4_sjf_no_arrival.c
gcc -o exp5_sjf_with_arrival exp5_sjf_with_arrival.c
gcc -o exp6_srtf exp6_srtf.c
gcc -o exp7_priority_non_preemptive exp7_priority_non_preemptive.c
gcc -o exp8_priority_preemptive exp8_priority_preemptive.c
gcc -o exp9_round_robin exp9_round_robin.c
gcc -o exp10_fifo_page_replacement exp10_fifo_page_replacement.c
gcc -o exp11_lru_page_replacement exp11_lru_page_replacement.c
```

## Execution Instructions

### Running Programs
```bash
# Windows
.\program_name.exe

# Linux/Unix
./program_name
```

### Sample Test Data

#### CPU Scheduling Programs (2-9)
```
Sample Input:
Number of processes: 3
Process 1: Arrival=0, Burst=5, Priority=2
Process 2: Arrival=1, Burst=3, Priority=1  
Process 3: Arrival=2, Burst=8, Priority=3
Time Quantum (for Round Robin): 2
```

#### Page Replacement Programs (10-11)
```
Sample Input:
Number of page references: 12
Page reference string: 1 3 0 3 5 6 3 1 6 1 2 4
Number of page frames: 3
```

## Key Concepts Demonstrated

### CPU Scheduling
- **FCFS**: Simple, non-preemptive, may cause convoy effect
- **SJF**: Optimal for minimizing average waiting time
- **SRTF**: Preemptive SJF, better response time
- **Priority**: Higher priority processes get CPU first
- **Round Robin**: Fair time-sharing with fixed quantum

### Memory Management
- **FIFO**: Simple, may suffer from Belady's anomaly
- **LRU**: Better performance, based on locality of reference

## Program Features

### Common Features Across All Programs:
1. **Interactive Input**: User-friendly input prompts
2. **Detailed Output**: Comprehensive result display
3. **Algorithm Visualization**: Step-by-step execution
4. **Performance Metrics**: Calculations for efficiency analysis
5. **Educational Comments**: Code documentation for learning

### Advanced Features:
- **Gantt Charts**: Visual representation of process execution
- **Timeline Display**: Real-time algorithm execution
- **Hit/Miss Ratios**: Memory management efficiency
- **Average Calculations**: Performance metrics

## Learning Objectives

After running these programs, students will understand:
1. Different CPU scheduling algorithms and their trade-offs
2. How process arrival times affect scheduling decisions
3. The difference between preemptive and non-preemptive scheduling
4. Memory management techniques and page replacement strategies
5. Performance metrics for evaluating OS algorithms

## Notes

- All programs are designed to be educational and include detailed output
- Input validation is included where necessary
- Programs demonstrate both theoretical concepts and practical implementation
- Code is well-commented for better understanding
- Each program can be run independently

## System Requirements

- **Compiler**: GCC (GNU Compiler Collection)
- **Operating System**: Windows, Linux, or Unix-based systems
- **Memory**: Minimal requirements (programs use small datasets)
- **Dependencies**: Standard C library only

## Troubleshooting

### Common Issues:
1. **Compilation Errors**: Ensure GCC is installed and in PATH
2. **Runtime Errors**: Check input format and ranges
3. **Permission Issues**: Ensure executable permissions on Unix systems

### Solutions:
```bash
# Install GCC on Ubuntu/Debian
sudo apt-get install gcc

# Install GCC on Windows (MinGW)
# Download from: https://mingw-w64.org/

# Make executable on Unix
chmod +x program_name
```

---

**Author**: Sriram  
**Course**: Operating Systems Laboratory  
**Language**: C Programming  
**Date**: July 2025
