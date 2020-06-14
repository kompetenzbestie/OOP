import random
import time

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
