import random
import operator

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
        
'''def partition(A, low, high):
    pivot = A[low]
    i = low
    
    for j in range(low+1, high+1):
        if(A[j] < pivot):
            i=i+1
            A[i], A[j] = A[j], A[i]
    A[i], A[low] = A[low], A[i]
    return i
'''

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


