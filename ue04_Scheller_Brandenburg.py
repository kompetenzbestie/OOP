import random
import time


# AUFGABE 1
#Shellsort wie auf den Vorlesungsfolien
def magicShellSort(A):
    if len(A) < 1:
        return A
    else:
        #aus dem Magic array wird der passende Eintrag mit findMagic() gesucht
        Magic = [1,3,7,21,48,112,336,861,1968,4592,13776,33936,86961,198768,463792,1391376]
        i = findMagic(len(A))
        seg_size = Magic[i]
        while i >= 0:
            for start_i in range(seg_size):
                jumpInsertSort(A, start_i, seg_size)
                i = i - 1
                seg_size = Magic[i]
    return A

def jumpInsertSort(A, start, step):
    for i in range(start+step, len(A), step):
        value = A[i]
        j = i
        while j >= step and A[j-step] > value:
            A[j] = A[j-step]
            j = j - step
        A[j] = value

#der größtmögliche Eintrag aus dem Magicarray, der kleiner als der übergebene Wert ist, wird mit dieser Funktion ermittelt, der Index wird zurückgegeben
def findMagic(length):
    Magic = [1,3,7,21,48,112,336,861,1968,4592,13776,33936,86961,198768,463792,1391376]
    i = 0
    while i <= len(Magic) and length < Magic[i]:
        i = i + 1
    return i

#Wir zählen nur Vergleiche von tatsächlichen Werten der Liste, nicht von irgendwelchen einfachen Variablen. Die Vergleiche werden in comparisons abgespeichert
#und dann am Ende ausgegeben. Der Rest der Funktion ist wie oben.
def shellComparisons(A):
    Magic = [1,3,7,21,48,112,336,861,1968,4592,13776,33936,86961,198768,463792,1391376]
    i = findMagic(len(A))
    comparisons = 0
    seg_size = Magic[i]
    while i >= 0:
        for start_i in range(seg_size):
            comparisons = comparisons + jumpInsertSortCount(A, start_i, seg_size)
        i = i - 1
        seg_size = Magic[i]
    return comparisons

#Hier finden die tatsächlichen Vergleiche statt, die Funktion ist auch wie oben, die count Variable zählt die Vergleiche, und wird immer dann erhöht, wenn wir
#in die While-Schleife gehen.
def jumpInsertSortCount(A, start, step):
    count = 0
    for i in range(start+step, len(A), step):
        value = A[i]
        j = i
        while j >= step and A[j-step] > value:
            A[j] = A[j-step]
            j = j - step
            count = count + 1
        A[j] = value

    return count
#Funktion aus letzter Woche
def random_list(a, b, n):
    returnList = []

    for i in range(n):
        returnList.append(random.randint(a, b))

    return returnList
#Wir erstellen eine Zufällige Liste A, eine Sortierte Liste B und eine falschherum sortierte Liste C und jeweils eine Kopie.
#Danach werden diese jeweils von shell-und Insertsort sortiert und die Anzahl der Vergleiche wird geprintet.
def shellVsInsert(a,b,n):
    A = random_list(a,b,n)
    B = []
    C = []
    for i in range(n):
        B.append(i)
    for j in range(n-1, -1, -1):
        C.append(j)
    A2 = A.copy()
    B2 = B.copy()
    C2 = C.copy()
    print("Shell vs I N S E R T T ... FIGHT!\n")
    print("Art of List         Shell               Insert")
    print("Unsorted:            ",shellComparisons(A),"               ",insertSortCount(A2))
    print("sorted:              ",shellComparisons(B),"                ",insertSortCount(B2))
    print("reverse sorted:      ",shellComparisons(C),"               ",insertSortCount(C2))

#Wie auch schon bei jumpInsertSortCount werden die Vergleiche in count gespeichert und dann am Ende ausgegeben.
def insertSortCount(seq):
    count = 0
    j = 1
    while(j<len(seq)):
        key = seq[j]
        k = j-1
        while k>=0 and seq[k]>key:
            seq[k+1] = seq[k]
            k = k-1
            count = count + 1
        seq[k+1] = key
        j+=1
        count = count + 1
    return count


# AUFGABE 2
'''
Wir gehen von der unsortierten Liste [4,1,2a,2b,3] aus.
2a und 2b sind hierbei die gleiche Zahl, die Buchstaben werden lediglich dazu verwendet,
die ursprüngliche Reihenfolge im späteren Kontext zu verdeutlichen.

Die Liste wird zunächst in ein Heap umgewandelt, wobei [5,4,3,2a,2b,1] herauskommt.

Anschließend wird Heapsort angewandt:

[5,4,3,2a,2b,1]
[4,3,2a,2b,1]     [4]
[3,2a,2b,1]     [3,4]
[2,2b,1]     [2a,3,4]
[1,1]     [2b,2a,3,4]
        [1,2b,2a,3,4]

In der sortierten Liste, befinden sich 2b und 2a nicht in ihrer ursprünglichen Reihenfolge,
Heapsort ist daher nicht stabil.
'''

def messageGenerator():
    time = 0

    # Eine Endlosschleife wird erzeugt, damit beliebig viele Nachrichten generiert werden können.
    while(True):
        # Eine zufällige Priorität wird ausgewählt.
        prioriry = random.randint(1, 50)
        # Die Nachricht wird zurückgegeben.
        yield((prioriry, time, "Message"))
        # Da eine neue Nachricht angefordert wird, wird der timer erhöht.
        time += 1

# Ein messageGenerator wird erzeugt.
generator = messageGenerator()


def next_message():
    return next(generator)

# Die folgenden Hilfsfunktion sind aus den Vorlesungsfolien übernommen.
def parent(i):
    return i//2

def left(i):
    return i*2

def right(i):
    return i*2+1

def heap_size(H):
    return H[0]

# Die max_heapif-Funktion ist ebenfalls aus den Folien übernommen, jedoch wurde sie modifiziert,
# um die in den Tupeln enthaltenen Prioritäten für Vergleiche zu verwenden.
def max_heapify(H, pos):                            # O(n*log(n))
    left_t = left(pos)
    right_t = right(pos)
    if left_t<=heap_size(H) and (H[left_t])[0]>(H[pos])[0]:
        biggest = left_t
    else:
        biggest = pos
    if right_t<=heap_size(H) and (H[right_t])[0]>(H[biggest])[0]:
        biggest = right_t
    if biggest != pos:
        H[pos], H[biggest] = H[biggest], H[pos]
        max_heapify(H, biggest)


def insert(priority_queue, message):                # 1 + 1 + (n*log(n)) ∈ O(n*log(n))
    # Die Größe der queue wird erhöht.
    priority_queue[0] = priority_queue[0]+1         # O(1)
    # Die message wird am Ende eingefügt.
    priority_queue.append(message)                  # O(1)
    # Die queue wird sortiert, damit sich die Nachricht an der richtigen Stelle befindet.
    sort_messages(priority_queue)                   # O(n*log(n))

def is_empty(priority_queue):                       # O(1)
    return (priority_queue[0]==0)                   # O(1)

def delete(priority_queue):                         # O(1) + O(n) + O(n*log(n)) ∈ O(n*log(n))

    # Es wird geprüft, ob die queue leer ist.
    if priority_queue[0] == 0:                      # 1 * O(1) ∈ O(1)
        return None                                 # O(1)

    # Die Größe der queue wird verkleinert.
    priority_queue[0] = priority_queue[0]-1         # O(1)

    # Im Rest der Funktion wird die queue danach durchsucht, ob es eventuell eine message mit der gleichen Priorität, jedoch einer niedrigen
    # Eintrittszeit als die Erste gibt. Ist dies der Fall, wird die Zeit dieser message zur neuen Vergleichszeit für die späteren Elemente und der Index der message wird vermerkt.
    maxPriority = (priority_queue[1])[0]            # O(1)
    minTime = (priority_queue[1])[1]                # O(1)
    deleteIndex = 1                                 # O(1)

    for i in range (2, len(priority_queue)):        # n-2 * O(1) * O(1) ∈ O(n)
        if ((priority_queue[i])[0] == maxPriority) and ((priority_queue[i])[1] < minTime):
            minTime = (priority_queue[i])[1]        # O(1)
            deleteIndex = i                         # O(1)

    # Von dem Index der zu löschenden message an werden alle Nachrichten einen Platz nach vorne verschoben, wodurch diese Nachricht überschrieben wird.
    for i in range (deleteIndex, len(priority_queue)-1):    # Im schlimmsten Fall ist deleteIndex = 1, also n-1*O(1) ∈ O(n)
        priority_queue[i]=priority_queue[i+1]               # O(1)

    # Die letzte Nachricht (welche jetzt auf die Stelle vor ihr kopiert wurde) wird gelöscht.
    del priority_queue[-1]                          # O(1)

    # Die Nachrichten werden sortiert
    sort_messages(priority_queue)                   # O(n*log(n))

    # Diese Rückgabe ist nur dazu da, damit hier etwas anderes als None zurückgegeben wird, da später unterschieden werden soll, ob die Löschung erfolgreich war oder nicht.
    return True                                     # O(1)

# Ähnlich wie in build_max_heap() wird hier max_heapify() für alle Ebenen aufgerufen.
def sort_messages(priority_queue):                      # O(n*log(n))
    for i in range(heap_size(priority_queue)//2,0,-1):  # n//2 * (n*log(n)) ∈ O(n*log(n))
        max_heapify(priority_queue,i)                   # O(n*log(n))

def sim_message_traffic():
    # Eine neue queue wird initialisiert.
    heap = [0]
    while(True):
        # Eine zufällige Zeitspanne zwischen 1 und 5 Sekunden verstreicht.
        time.sleep(random.randint(1,5))
        # Es wird zufällig eines von zwei Szenarien ausgewählt: entweder wird eine neue Nachricht empfangen oder es soll eine beantwortet (gelöscht) werden.
        if random.random()<0.5:
            # Eine neue Nachricht wird "empfangen" und in die queue eingefügt.
            message = next_message()
            insert(heap, message)
            print("New message with priority",message[0])
            print("Current queue:",heap,"\n")
        else:
            # Es wird versucht, eine Nachricht zu "beantworten". Ist dies nicht möglich, wird dies in der Rückgabe klar gemacht.
            if(delete(heap) == None):
                print("Tried answering a message, but the queue is empty.\n")
            else:
                print("Answered most important message!")
                print("Current queue:",heap,"\n")

# AUFGABE 3
#Damit der Algorithmus In-Place funktioniert, muss man nur die Anzahl der jeweiligen Einträge in C abspeichern und dann einfache
#durch C durch iterieren und jeweils so viele Male den Eintrag in A einschreiben, wie er ursprünglich vorgekommen ist.
#Der Algorithmus wird dadurch instabil, denn man kann nicht sicherstellen, welcher von den gleichwertigen Einträgen an welche Stelle kommt.
def CountingSortInPlace(A,k):
    l = 0
    C = [0 for i in range(0, k+1)]
    for i in range(0, len(A)):
        C[A[i]] += 1
    for j in range(k+1):
        for k in range (C[j]):
            A[l] = j
            l += 1
    return (A)

'''
Die eingegebene Liste muss einmal komplett durchgegangen werden (O(n))
Die Hilfsliste C muss k mal durchgegangen werden, und es muss n mal ein Element in A erseztzt (O(n+k))
Die Laufzeit ist daher O(n+k)

Im Gegensatz zur out-of-place Variante wird eine Liste weniger benötigt, wir benötigen also nur eine Liste mit Länge n und eine mit Länge k.
'''

# AUFGABE 4

'''
Um so viele große Zahlen zu sortieren eignet sich ein Sortieralgorithmus, der nicht zu viel Speicherplatz verbraucht
aber auch eine möglichst geringe Laufzeit hat, denn bei so vielen Zahlen werden die Unterschiede zwischen n^2 und n * log n deutlich merkbar.
Nach schnellem Vergleichen der Algorithmen auf https://studyflix.de/informatik/sortieralgorithmen-1337 haben wir uns für Radix Sort entschieden, da
offensichtlich die Laufzeit die beste ist, denn k wird bei 32 Bit Langen Zahlen sehr groß, während l mit 32 immernoch sehr klein bleibt. Falls der benötigte
Speicher damit jedoch zu groß sein sollte, so sollte man sich für Shellsort entscheiden.
'''

#Radix Sort Implentierung nach https://www.lewuathe.com/radix-sort-in-python.html
def radix_sort(arr, max_value):
  num_digits = get_num_difit(max_value)
  for d in range(num_digits):
    arr = radixCountingSort(arr, max_value, lambda a: get_digit(a, d+1))
  return arr

def radixCountingSort(arr, max_value, get_index):
  counts = [0] * max_value
  for a in arr:
    counts[get_index(a)] += 1
  for i, c in enumerate(counts):
    if i == 0:
      continue
    else:
      counts[i] += counts[i-1]
  for i, c in enumerate(counts[:-1]):
    if i == 0:
      counts[i] = 0
    counts[i+1] = c
  ret = [None] * len(arr)
  for a in arr:
    index = counts[get_index(a)]
    ret[index] = a
    counts[get_index(a)] += 1
  return ret


def get_digit(n, d):
  for i in range(d-1):
    n //= 10
  return n % 10

def get_num_difit(n):
  i = 0
  while n > 0:
    n //= 10
    i += 1
  return i
'''
Ein test mit radix_sort(random_list(0,2147483647,1000)2147483647) hat mein Rechner schon nicht mehr geschafft (ziemlich langsames Ding leider) dementsprechend
gehe ich davon aus, dass der Speicherverbrauch/Rechenleistung stark von so einer Sortieranfrage belastet wird. Falls der Rechner jedoch besser ausgestattet ist,
können selbst viele, lange Bitzahlen schnell sortiert werden. Wie sich später beim testen von CountingSortInPlace herausgestellt hat, bekommt diese Funktion
auch Schwierigkeiten mit negativen Inputs.
'''
# TESTFUNKTIONEN
def test_1():
    magicShellSort([])                                                           #[]
    magicShellSort(random_list(-100,100,10))                                     #[-69, -55, -42, -40, -34, -27, 3, 34, 44, 65], [-97, -69, -45, 31, 38, 70, 72, 93, 97, 99]

def test_2():
    sim_message_traffic()
'''
New message with priority 27
Current queue: [1, (27, 10, 'Message')]

Answered most important message!
Current queue: [0]

Tried answering a message, but the queue is empty.

New message with priority 27
Current queue: [1, (27, 11, 'Message')]

New message with priority 6
Current queue: [2, (27, 11, 'Message'), (6, 12, 'Message')]

Answered most important message!
Current queue: [1, (6, 12, 'Message')]

New message with priority 18
Current queue: [2, (18, 13, 'Message'), (6, 12, 'Message')]

New message with priority 22
Current queue: [3, (22, 14, 'Message'), (6, 12, 'Message'), (18, 13, 'Message')]

Answered most important message!
Current queue: [2, (18, 13, 'Message'), (6, 12, 'Message')]

New message with priority 34
Current queue: [3, (34, 15, 'Message'), (6, 12, 'Message'), (18, 13, 'Message')]

New message with priority 5
Current queue: [4, (34, 15, 'Message'), (6, 12, 'Message'), (18, 13, 'Message'), (5, 16, 'Message')]
'''
def test_3():
    CountingSortInPlace([],0)                                                   #[]
    CountingSortInPlace(random_list(-100,100,10),100)                           #[6, 12, 26, 38, 39, 44, 56, 64, 76, 97], [4, 7, 17, 52, 55, 77, 78, 87, 88, 92]
    #Negative Inputs funktionieren also nicht.
    CountingSortInPlace(random_list(0,100,10),100)                              #[6, 31, 33, 51, 60, 65, 66, 83, 96, 99], [1, 9, 19, 28, 48, 48, 61, 68, 73, 82]

def test_4():
    #radix_sort(random_list(0,2147483647,1000),2147483647)                        #Nach ca. 5 Minuten "Ur Process is still running do you want to kill it?"
    #auskommentiert, um lag zu vermeiden :)
    radix_sort(random_list(0,100,10),100)                                       #[1, 12, 22, 31, 47, 57, 65, 69, 83, 95], [12, 12, 43, 43, 52, 66, 73, 76, 90, 95]
    radix_sort([],0)                                                              #[]
