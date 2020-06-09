import random
import operator

# AUFGABE 1

def random_list(a, b, n):
    returnList = []

    for i in range(n):
        returnList.append(random.randint(a, b))

    return returnList

def sorted(op, xs):
    if(len(xs)<2):
        print("Liste needs to have at least 2 elements!")
        return
    elif(len(xs)==2):
        return (op(xs[0],xs[1]))
    else:
        return (op(xs[0],xs[1]) and sorted(op,xs[2:]))

# AUFGABE 2


# AUFGABE 3

def insertsort(seq):
    j = 1
    while(j<len(seq)):
        key = seq[j]
        k = j-1
        while k>=0 and seq[k]>key:
            seq[k+1] = seq[k]
            k = k-1
        seq[k+1] = key
        j+=1

    return seq

def insertTest():
    for i in range (5):
        seq = random_list(0, 100, 10)
        print("Original list:\t", seq)

        insert = insertsort(seq)
        print("Sorted list:\t", insert, end="\t")

        if(sorted(operator.le, insert)):
            print("Sorted!", end="\n\n")
        else:
            print("Not sorted!", end="\n\n")


# AUFGABE 4

def partition(A, low, high):

    avgList=[]

    for x in range(3):
        avgList.append(A[random.randint(0, len(A)-1)])

    pivot = (sum(avgList)//3)

    i = low

    for j in range(low+1, high+1):
        if(A[j] < pivot):
            i=i+1
            A[i], A[j] = A[j], A[i]
    A[i], A[low] = A[low], A[i]
    return i

def quicksort(A, low, high):
    if low<high:
        m = partition(A, low, high)
        quicksort(A, low, m-1)
        quicksort(A, m+1, high)
    return A


'''
Speicheranalyse zu Quick Sort á la Haskell:

Worst case: Die Liste ist bereits sortiert.

In diesem Fall finden n Funktionsaufrufe statt.
In jedem dieser Funktionsaufrufe wird eine neue Liste erzeugt, wobei die Erste die Länge
n-1, die Zweite die Länge n-2 hat etc.
Daher beläuft sich der zusätzliche Speicherplatz auf O(n²).

'''

def quick_insert(A, low, high, k):
    if low<high:
        if len(A[low:high])<k:
            insertsort(A)
        m = partition(A, low, high)
        print(A)
        quick_insert(A, low, m-1, k)
        quick_insert(A, m+1, high, k)
    return A

# AUFGABE 5

def min_diff(A):
    if(len(A)<2):
        print("List needs to have at least 2 elements!")
        return
    else:
        y = 0
        ldiff = abs(A[0] - A[1])
        ret = (0,1)
        while(y < len(A)):
            z = y+1
            while(z < len(A)):
                if(abs(A[y] - A[z]) < ldiff):
                    ldiff = abs(A[y] - A[z])
                    help = (A[y],A[z])
                z=z+1
            y=y+1
        return ret

'''
Speicheranalyse zu min_diff:


'''

# AUFGABE 6

def itmergesort(A):
    z = 0
    y = 0
    res = []
    while z < len(A):
        while y < len(A):
            res = res + merge(A[y:(y+z+1)],A[(y+z+1):(y+2*z+2)])
            y = y+2*z+2
        z = 2*z+1
        A = res
        y = 0
        res = []
    return A


# AUFGABE 7

def mergeandsort(A, B):
    return mergesort(A+B)

def mergesort(A):
    if len(A) < 2:
        return A
    else:
        m = len(A) // 2
        return merge( mergesort(A[:m]), mergesort(A[m:]))

def merge(low, high):
    res = []
    i, j = 0, 0
    while i<len(low) and j<len(high):
        if low[i] <= high[j]:
            res.append(low[i])
            i = i+1
        else:
            res.append(high[j])
            j = j+1
    res = res + low[i:]
    res = res + high[j:]
    return res
