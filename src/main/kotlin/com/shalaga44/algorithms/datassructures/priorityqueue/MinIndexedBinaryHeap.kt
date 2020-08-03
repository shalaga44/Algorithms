package com.shalaga44.algorithms.datassructures.priorityqueue

class MinIndexedBinaryHeap<T : Comparable<T>>(maxSize: Int) :
    MinIndexedDHeap<T>(2, maxSize)
