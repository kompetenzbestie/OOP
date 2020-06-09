import random
import operator

# AUFGABE 1

#Nachdem eine hilfsliste initialisiert wurde, wird n mal eine zufalls Zahl zwischen a und b an die Liste angehangen
def random_list(a, b, n):
    returnList = []

    for i in range(n):
        returnList.append(random.randint(a, b))

    return returnList

#solange die Liste mehr als 1 Element hat, dann wird die Funktion rekursiv aufgerufen. Der Anker ist in diesem Fall erreicht, wenn die Liste nur genau 2 Elemente hat.
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
'''
zu 3c):
Bei kleineren Listen ist Insertion Sort effizienter als Quicksort, jedoch wird bei größeren Listen sollte man Quicksort verwenden.
Wenn man sich die Wachstumsfunktionen anguckt (siehe Vorlesungsfolien) erkennt man dies auch: O(n²) (Insertionsort) ist bei
kleinen n kleiner als O(n log n) (Quicksort).
'''

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
zu 4b):
[5, 7, 7* , 4] -> [4, 5, 7* 7]

zu 4c):
Wenn man die Position der Elemente auch mit einbezieht, kann man durch eine Fallunterscheidung beim gleichen Elementen
Quicksort auch Stabil implementieren.

zu 4d):
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
#Falls die Liste mindestens 2 Elemente hat, so wird jedes Element genau einmal mit allen anderen Verglichen, in ldiff wird
#die kleinste Differenz gespeichert, in ret das dazugehörige Paar Elemente der Liste.
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
                    ret = (A[y],A[z])
                z=z+1
            y=y+1
        return ret

'''
Speicheranalyse zu min_diff:
Wegen n Schleifendurchläufen, mit jeweils n, n-1, n-2... 1 Vergleiche(n) hat min_diff eine Laufzeit von (n*(n+1))/2 -> O(n²)
'''

# AUFGABE 6
#Beim Iterativen Mergesort wird die Liste in Teillisten mit der Länge z unterteilt, diese sind durch die merge Funktion aus der Vorlesung
#sortiert, und in dem Hilfsarray res abgelegt. Nach einem Schleifendurchlauf wird das Ursprungliche Array = dem Hilfsarray gesetzt und besteht
#dann aus sortierten Teillisten. Durch die Erhöhung von z werden größere, sortierte Teillisten erstellt, bis die gesamte Liste sortiert ist.
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
#mergeandsort ruft einfach mergesort mit der konkatenierten Liste A+B auf, mergesort und merge sind aus der Vorlesung.
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


# TESTFUNKTIONEN

def test_1():
    random_list(1,100,5)                                            #[98, 95, 17, 25, 94]
    random_list(-10,10,10)                                          #[10, -7, -6, 1, -5, 2, -10, -7, 0, 10]


def test_3():
    insertsort([1,23,6,4,9,0,1,3,4,6])                              #[0, 1, 1, 3, 4, 4, 6, 6, 9, 23]
    insertsort([-3,5,6,10039,3,-6,-7,-7,3,0])                       #[-7, -7, -6, -3, 0, 3, 3, 5, 6, 10039]
    insertTest()
'''
Original list:	 [26, 2, 28, 11, 12, 29, 60, 44, 99, 28]
Sorted list:	 [2, 11, 12, 26, 28, 28, 29, 44, 60, 99]	Sorted!
Sorted list:	 [14, 19, 40, 42, 52, 55, 61, 61, 67, 79]	Sorted!
Sorted list:	 [1, 3, 15, 24, 30, 33, 48, 79, 79, 99]	Sorted!
Original list:	 [18, 63, 80, 100, 24, 18, 89, 13, 17, 99]
Sorted list:	 [13, 17, 18, 18, 24, 63, 80, 89, 99, 100]	Sorted!
Original list:	 [7, 87, 7, 36, 46, 87, 83, 2, 48, 38]
Sorted list:	 [2, 7, 7, 36, 38, 46, 48, 83, 87, 87]	Sorted!
'''
#def test_4():
def test_5():
    min_diff ([3, 10, 6, 9, 5, 1, 2, 7, 6, 8])                      #(6, 6)
    min_diff(random_list(-100,100,10))                              #(-33, -29), (-42, -41), (-96, -96),(76, 68)
def test_6():
    itmergesort(random_list(-100,100,10))                           #[-98, -96, -88, -81, -40, -36, -2, 2, 8, 70], [-68, -59, -40, 10, 22, 23, 30, 32, 39, 100]
    itmergesort([3, 10, 6, 9, 5, 1, 2, 7, 6, 8])                    #[1, 2, 3, 5, 6, 6, 7, 8, 9, 10]
    itmergesort([0,0,0,0,0,1,1,1,1,1,1,1,1,-1,-1,-1])               #[-1, -1, -1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1]

def test_7():
    mergeandsort(random_list(-100,100,5),random_list(-100,100,5))   #[-92, -82, -64, -51, -51, -45, 26, 59, 62, 94], [-52, -51, -42, -41, -7, 10, 54, 62, 72, 89]
    mergeandsort([1,23,6,4,9,0,1,3,4,6],[-3,5,6,10039,3,-6,-7,-7,3,0])#[-7, -7, -6, -3, 0, 0, 1, 1, 3, 3, 3, 4, 4, 5, 6, 6, 6, 9, 23, 10039]
    mergeandsort([1,23,6,4,9,0,1,3,4,6],[])                         #[0, 1, 1, 3, 4, 4, 6, 6, 9, 23]
