def clavevalor():
    dic={1:'a',2:'b',3:'c',4:[5,6,7]}
    for i in dic.keys():
        print(i, dic[i])
    print("Otra forma")
    for i in dic.items():
        print(i[0], i[1], type(i))

print("Empezamos")

clavevalor()

print("Fin")