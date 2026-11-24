# Percolation
Key features
- Grid-based simulation: Models fluid flow through an N × N grid of open and blocked sites.
- Efficient connectivity tracking: Uses the Weighted Quick Union-Find data structure to manage connections between open sites.
- Percolation detection: Determines whether a connected path exists between the top and bottom of the grid.
- Fullness detection: Identifies whether an open site is connected to the top of the grid.
- Backwash prevention: Prevents disconnected sites from being incorrectly identified as full after the system percolates.
- Dynamic site management: Supports opening individual sites, updating connections, and tracking the total number of open sites.
