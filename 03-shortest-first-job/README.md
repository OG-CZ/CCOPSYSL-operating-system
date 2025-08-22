# Shortest Job First (SJF) Scheduling in Java

## Overview
This Java program simulates the **Shortest Job First (SJF) non-preemptive scheduling algorithm**.  
It calculates the **Completion Time (ET), Turnaround Time (TAT), and Waiting Time (WT)** for a set of processes, and also prints a **Gantt chart**.

---

## Features
- Accepts **3 to 5 processes** with user input:
  - **Arrival Time (AT)**
  - **Burst Time (BT)**
- Implements **SJF non-preemptive scheduling**:
  - At each step, chooses the **available process with the shortest burst time**.
- Tracks finished processes using a **boolean array** (`done[]`).
- Computes:
  - **Completion/Exit Time (ET)** = time when process finishes
  - **Turnaround Time (TAT)** = `ET - AT`
  - **Waiting Time (WT)** = `TAT - BT`
- Prints:
  - **Gantt chart** for visualization
  - **Table of all process times**
  - **Average TAT and WT**

---

## How the Algorithm Works
1. Initialize `time = 0` and `completed = 0`.
2. While there are unfinished processes:
   - Search for the **process that has arrived** (`AT <= time`) and has the **shortest burst time**.
   - If found:
     - Run it and increment `time` by its `BT`.
     - Calculate `ET`, `TAT`, and `WT`.
     - Mark the process as done: `done[i] = true`.
     - Increment `completed`.
   - If none are ready, increment `time` (CPU idle).
3. After all processes finish:
   - Use `ET` to order processes for **Gantt chart**.
4. Print **Gantt chart**, **table**, and **average times**.

---

## Key Java Concepts Used
- **Arrays** to store process info (`AT`, `BT`, `ET`, `TAT`, `WT`, `done`).
- **Boolean array (`done[]`)** to track completed processes.
- **Loops** for scheduling and table printing.
- **Conditional statements (`if`)** to select shortest job.
- **Cloning arrays** to sort for Gantt chart without affecting original data.



