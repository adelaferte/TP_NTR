import matplotlib.pyplot as plt
import numpy as np

def parser(nameoffile):
    s = []
    with open(nameoffile,'r') as f:
        for line in f:
            l = line.split(" ")
            l[-1] = l[-1][:-1]
            s.append(l)
        for i in range(len(s)):
            for j in range(len(s[i])):
                try:
                    s[i][j] = float(s[i][j])
                except:
                    s[i] = s[i][:-1]
    return s

def putgood(l):
    s = []
    for i,e in enumerate(l[0]):
        s.append([e])
    for t in l:
        for i,e in enumerate(t):
            s[i].append(e)
    return s

for i in range(2,11):
    l = parser("simulation(8,"+str(i)+")-100000.data")
    s = putgood(l)
    X = np.linspace(0, len(s[0]), len(s[0]), endpoint=True)

    for j in range(len(s)):
        if j == 0:
            l = 2.5
            ls = "--"
            k = "OLSR"
        elif j == len(s)-1:
            l = 2.5
            ls = "--"
            k = "LSOR infinite"
        else:
            l = 2
            ls = "-"
            k = "LSOR"+str(j)

        plt.plot(X,s[j],linewidth=l,label=k,linestyle=ls)
        plt.xlabel("Time in unit (divided by 10)")
        plt.ylabel("Percent of data sent")
        plt.title("Size of network: "+str(i)+" number of datasend: 100000")
    plt.legend(loc='lower right', frameon=False)
    plt.show()
