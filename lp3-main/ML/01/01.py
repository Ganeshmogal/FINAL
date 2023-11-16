import numpy as np
def g(Start,gf,mi,lr,b=0.001):
    s1=[Start]
    x=Start
    for i in range(mi):
        d=lr*gf(x)
        if(np.abs(d)<b):
            break
        x=x-d
        s1.append(x)
    return s1,x,lr
    
def gd_f(x):
    return 4*x

list,r,lr=g(2,gd_f,200,0.001)
print("The optimal elemrnt:" , r)
print("Number Elements :", len(list))
print("The list elements are:")
print(list)