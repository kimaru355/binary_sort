EMMANUEL KIMARU | EB3/61554/22
Binary Insertion Sort on 10 Million Elements

📌 Overview

This project implements Binary Insertion Sort in Java and analyzes its performance on large datasets up to 10 million elements.

Binary Insertion Sort improves comparison efficiency using binary search but still suffers from quadratic time complexity due to element shifting.

⚙️ Algorithm Idea

Maintain a sorted portion of the array.

Use binary search to find insertion position.

Shift elements to make space.

Insert element.

🕑 Time Complexity Case Complexity Best Case O(n log n) Average O(n²) Worst O(n²)

Worst case shifts:

n(n − 1) / 2

For n = 10,000,000:

≈ 50 trillion shifts

📊 Key Insight

Although binary search reduces comparisons to O(n log n), shifting dominates runtime, keeping total complexity at O(n²).
