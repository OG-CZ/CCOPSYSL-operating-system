in this section we are task to imitate a terminal based table that looks like a gantt chart

we created this using nested for loops to imitate chart look using terminal, we need to do this because the next upcoming exercise is to imitate algorithm solutions that are necessary in operating systems

it has 3 parts

1. GanttChart.java - better readability and code
2. GanttChartFormat.java - raw format of the chart
3. GanttChartBruteForce.java - the original one before it was optimized for readability

output

```code
n = 6
+------+-------+-------+-------+-------+
|  p1  |  p2   |  p3   |  p4   |  p5   |
+------+-------+-------+-------+-------+
0      1       2       3       4       5
```

its resizable using "int blockWidth = 5;" to specify how long each block is

```code
n = 8
+--------+--------+--------+--------+--------+
|  P1    |  P2    |  P3    |  P4    |  P5    |
+--------+--------+--------+--------+--------+
0        1        2        3        4        5

```
