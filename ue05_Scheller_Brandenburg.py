import random
import time
import operator

#Funktion aus letzter Woche für Testfunktionen
def random_list(a, b, n):
    returnList = []

    for i in range(n):
        returnList.append(random.randint(a, b))

    return returnList

# AUFGABE 5
#Insertsort aus der Vorlesung, Äußere For-Schleife ersetzt
def insertsort(seq):
    j = 1
    while j < len(seq):
        key = seq[j]
        k = j-1
        while k>=0 and seq[k]>key:
            seq[k+1] = seq[k]
            k = k-1
            assert bigger(key, seq[k+1:j])== True
        seq[k+1] = key
        j += 1
        assert (sorted(operator.le, seq[:j])) == True
    return seq

#Hilfsfunktion für INV1
def sorted(op, xs):
    if(len(xs)<2):
        return True
    elif(len(xs)==2):
        return (op(xs[0],xs[1]))
    else:
        return (op(xs[0],xs[1]) and sorted(op,xs[2:]))
#Hilfsfunktion für INV2
def bigger(element, xs):
    if(len(xs)<1):
        return True
    elif(len(xs)==1):
        return (element <= xs[0])
    else:
        return ((element <= xs[0]) and bigger(element,xs[1:]))


'''
INV1: Die ersten j Elemente der Liste sind sortiert
INV2: Die Liste ist vom k+1ten bis zum jten Element sortiert und größer gleich key.
'''


# TESTFUNKTIONEN
def test_5():
    insertsort(random_list(0,100,10))
    #[-83, -53, -46, -41, -36, -20, 59, 63, 72, 88], [14, 23, 32, 73, 79, 81, 83, 85, 85, 87], [9, 38, 39, 46, 53, 54, 64, 67, 85, 93]
