import random
import time
import turtle as t

t.speed(0)

# AUFGABE 1

# Die Zahl -1 (bitweises Gegenteil von 0) wird um n Stellen nach links geshiftet (entspricht einer Multiplikation mit 2**n).
# Das negative Ergebnis wird nun bitweise umgekehrt, so wird aus -2**n 2**n - 1.
def marsenne(n):
    return ~(~False<<n)


# AUFGABE 2

def repeats_list (n,m):

    # Es wird eine Liste mit den Indizes 0 bis n generiert, auf jedem Index wird die bisherige Häufigkeit (0) gespeichert.
    xs = [0 for x in range(n+1)]

    for i in range(m):

        # Der Häufigkeitszähler auf einem zufälligen Index wird um 1 erhöht.
        xs[random.randint(0, n)]+=1

    return xs

def repeats_dict (n,m):

    # Ein leeres Dictionary wird erschaffen
    xs = {}

    # Das Dictionary wird ähnlich wie die Liste gefüllt, jeder Key entspricht einer Zahl und der Inhalt dem jeweiligen Häufigkeitswert.
    for i in range(n+1):
        xs[i] = 0

    # Der Häufigkeitszähler auf einem zufälligen Key wird um 1 erhöht.
    for i in range(m):
        xs[random.randint(0, n)]+=1

    return xs
#TESTPUSH

# AUFGABE 3

# Hier zunächst die Suchalgorithmen aus der Vorlesung

# linear iterativ
def linear_search(key, seq):
    for i in seq:
        if i == key:
            return True
    return False

# binär iterativ
# Anm.: Parameter getauscht
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

# linear rekursiv
# Anm.: Zu lin_search umbenannt
def lin_search(key, seq):
    if len(seq) == 0:
        return False
    elif seq[0] == key:
        return True
    else:
        return lin_search(key, seq[1:])

# binär  recursiv
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

    # Zwei Listen werden erschaffen; Eine enthält alle Suchfunktionen, die Andere die Namen für die Ausgabe.
    funcs = [linear_search,binary_search,lin_search,bin_search]
    func_names = ["Linear iterative","Binary iterative","Linear recursive","Binary recursive"]

    # Eine Liste mit 500 sortierten Zahlen (0 bis 499) wird generiert.
    list = [x for x in range (500)]

    # Es wird für jeden Algorithmus durchiteriert.
    for i in range (4):

        # Der aktuelle Zeitpunkt (Startpunkt der Zeitanalyse) wird gespeichert.
        time_start = time.time()

        for j in range (1000):

            # Ein zu suchender Key wird generiert.
            key = random.randint(0, 500)

            # Die Suche wird mit dem aktuellen Algorithmus durchgeführt.
            funcs[i](key, list)

        # Die benötigte Zeit wird berechnet und ausgegeben.
        print(func_names[i],(time.time()-time_start), "\tseconds.")


# AUFGABE 5

def star(x, y, size):
    t.up()
    t.goto(x,y)

    # Die turtle wird nach Norden ausgerichtet.
    t.seth(90)

    # Die turtle geht die Distanz size/2 zurück, damit der Stern später um die gewählten Koordinaten zentriert ist.
    t.back(size/2)
    t.down()

    # Eine zufällige Farbe wird generiert und als Malfarbe gesetzt.
    t.color(random.random(),random.random(),random.random())

    t.begin_fill()

    # Ein siebenzackiger Stern wird generiert.
    for i in range(7):
        t.forward(size)
        t.right(154.286)

    t.end_fill()


def sky(n):

    # Es wird n-Mal ein Stern an einer zufälligen Position innerhalb eines 600x600-Feldes generiert (dies ist ungefähr die
    # Größe, die ein Turtle-Fenster in der Regel einnimt), wobei die Größe zwischen 10 und 100 Pixeln zufällig gewählt wird
    # (diese Werte schienen mir angemessen, damit alles nicht vollkommen im Chaos versinkt).
    for i in range (n):
        star(random.randint(-300,300),random.randint(-300,300),random.randint(10,100))


def squares(size, depth):

    # Es wird überprüft, ob die Tiefe den Wert 0 erreicht hat (in diesem Fall sollen keine Rekusrionen mehr ausgeführt werden)
    # oder ob die Zeichengröße unter 1 gefallen ist (in diesem Fall kann turtle nicht mehr zeichnen). In diesen Fällen bricht das Programm ab.
    if depth < 1 or size < 1:
        return
    else:

        # Die Zeichengröße wird gerundet abgespeichert (da turtle, wenn es versucht, eine Fließkommazahl von Pixeln zu zeichnen, unschönes Verhalten an den Tag legt).
        size = int(round(size,0))
        # Das Selbe wird mit der halben Zeichengröße gemacht.
        half = int(round(size/2,0))

        # Die Zeichenfarbe wird festgelegt. Ist die akzuelle Tiefe gerade, wird Schwarz verwendet, ansonsten Rot.
        if depth%2==0:
            t.color("black")
        else:
            t.color("red")

        # Die turtle wird an die untere linke Ecke des zu zeichnenden Quadrats bewegt.
        t.up()
        t.bk(half)
        t.right(90)
        t.fd(half)
        t.left(90)
        t.down()

        # Das Quadrat wird gezeichnet.
        for i in range(4):
            t.fd(size)
            t.left(90)

        # Die turtle kehrt wieder in die Mitte zurück.
        t.up()
        t.fd(half)
        t.left(90)
        t.fd(half)
        t.right(90)
        t.down()

        # Rekursiver Aufruf.
        squares(size-10, depth-1)


def fractal_squares(x, y, startsize, depth):

    # Da die Zeichenphasen dieser Funktion recht lange ausfallen können, habe ich hier den tracer deaktiviert.
    t.tracer(0, 0)

    # Die Zeichengröße wird gerundet abgespeichert.
    startsize = int(round(startsize, 0))
    # Das Selbe wird mit der halben Zeichengröße gemacht.
    h_startsize = int(round(startsize/2, 0))

    # Die trtle wird zu der übergebenen Koordinate bewegt.
    t.up()
    t.goto(x,y)
    t.down()

    # Ein rekursives Quadrat wird gezeichnet.
    squares(startsize, 8)

    # Hier wird überprüft, ob der Rekursionszweig sein Ende erreicht hat.
    # Wenn ja, updated der tracer und das Programm bricht ab.
    if depth > 0:

        # Die Distanz zu den zu zeichnenden Fraktalquadraten wird errechnet und gerundet abgespeichert.
        dist = int(round(startsize*0.75,0))

        # Es wird je ein rekursiver Aufruf oben rechts, oben links, unten rechts und unten links ausgeführt.
        fractal_squares(x+dist,y+dist,h_startsize,depth-1)
        fractal_squares(x-dist,y+dist,h_startsize,depth-1)
        fractal_squares(x+dist,y-dist,h_startsize,depth-1)
        fractal_squares(x-dist,y-dist,h_startsize,depth-1)
    else:
        t.update()
        return
