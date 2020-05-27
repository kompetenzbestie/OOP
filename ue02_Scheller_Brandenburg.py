import random
import time

# AUFGABE 1

def marsenne(n):
    return ~(-1<<n)


# AUFGABE 2

def repeats (n,m):

    xs = [0 for x in range(n+1)]
    
    for i in range(m):
        xs[random.randint(0, n)]+=1

    return xs


# AUFGABE 3

# linear search iterative, see 5th presentation, slide 58
def linear_search(key, seq):
    for i in seq:
        if i == key:
            return True
    return False

# binary search iterative, see 5th presentation, slide 76
# - Switched parameters
def binary_search(key, nums):
    lowerBound = 0
    upperBound = len(nums) - 1

    while lowerBound <= upperBound:
        current = (lowerBound + upperBound) // 2
        if nums[current] == key:
            return True
        else:
            if nums[current] < key:
                lowerBound = current + 1
            else:
                upperBound = current - 1
    return False

# linear search recursive, see 5th presentation, slide 59
# - Renamed to lin_search
def lin_search(key, seq):
    if len(seq) == 0:
        return False
    elif seq[0] == key:
        return True
    else:
        return lin_search(key, seq[1:])

# binary search recursive, see 5th presentation, slide 61
def bin_search(key, seq):
    if len(seq) > 1:
        m = len(seq) // 2
        if seq[m] == key:
            return True
        elif key < seq[m]:
            return bin_search(key, seq[0:m])
        else:
            return bin_search(key, seq[(m+1):])
    elif len(seq) == 1:
        return seq[0] == key
    else:
        return False

def search_benchmark():
    funcs = [linear_search,binary_search,lin_search,bin_search]
    func_names = ["Linear iterative","Binary iterative","Linear recursive","Binary recursive"]
    list = [x for x in range (500)]

    for i in range (4):

        time_start = time.time()

        for j in range (1000):
            key = random.randint(0, 500)
            funcs[i](key, list)

        print(func_names[i],(time.time()-time_start), "\tseconds.")
        
