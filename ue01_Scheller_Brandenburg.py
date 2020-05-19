import random


# AUFGABE 1
def auf_ab (a, b, c):
    if a<=b<=c:
        print("Die Zahlen sind aufsteigend.")
    elif a>=b>=c:
        print("Die Zahlen sind absteigend.")
    else:
        print("Die Zahlen sind weder auf- noch absteigend.")


# AUFGABE 2
def listenprodukt (liste):

    acc = 1
    
    for x in liste:
        acc = acc*x

    print(acc)


# AUFGABE 3
def weekday (d,m,y):
    
    y_0 = int(y - (14-m)/12)
    x = int(y_0 + y_0/4 - y_0/100 + y_0/400)
    m_0 = int(m + 12*((14-m)/12) - 2)
    
    name = int((d + x + (31*m_0)/12) % 7)

    if(name==0):
        print("Samstag")
    elif(name==1):
        print("Sonntag")
    elif(name==2):
        print("Montag")
    elif(name==3):
        print("Dienstag")
    elif(name==4):
        print("Mittwoch")
    elif(name==5):
        print("Donnerstag")
    elif(name==6):
        print("Freitag")


# AUFGABE 4
def gluecksspieler():

    bargeld = int(input("Bargeld = "))

    eingang = bargeld

    rounds = 0

    while bargeld > 0:

        rounds += 1
        
        print(bargeld, end=": \t")

        counter = bargeld
        while counter > 0:
            print("$", end="")
            counter -= 1

        if bargeld == (eingang*2):
            print("\nYou are a great winner!")
            return rounds

        if random.randint(0,1):
            bargeld -= 1
        else:
            bargeld += 1

        print()

    print("You are a great loser!")

    return rounds


# AUFGABE 5
def teiler(a):

    t = []
    
    for i in range(1, a):
        if a%i==0:
            t.append(i)

    return t
            
def befreundet(n, m):
    
    return (n == sum(teiler(m))) and (m == sum(teiler(n)))


# AUFGABE 6
def beaufortskala(k):
    if k<1:
        return "Windstille"
    elif k<4:
        return "Leiser Zug"
    elif k<7:
        return "Leichte Brise"
    elif k<11:
        return "Schwache Brise"
    elif k<16:
        return "Mäßige Brise"
    elif k<22:
        return "Frische Brise"
    elif k<28:
        return "Starker Wind"
    elif k<34:
        return "Steifer Wind"
    elif k<41:
        return "Stürmischer Wind"
    elif k<48:
        return "Sturm"
    elif k<56:
        return "Schwerer Sturm"
    elif k<65:
        return "Orkanartiger Sturm"
    elif k>64:
        return "Orkan"
    else:
        return "Falscher Input. Bitte eine Zahl eingeben."
