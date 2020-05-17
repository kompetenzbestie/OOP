def auf_ab (a, b, c):
    if a<=b<=c:
        print("Die Zahlen sind aufsteigend.")
    elif a>=b>=c:
        print("Die Zahlen sind absteigend.")
    else:
        print("Die Zahlen sind weder auf- noch absteigend.")

def listenprodukt (liste):

    acc = 1
    
    for x in liste:
        acc = acc*x

    print(acc)

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
