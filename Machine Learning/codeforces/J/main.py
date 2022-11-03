import math
from abc import abstractmethod
from copy import deepcopy
import numpy as np


class Vertex:
    def __init__(self):
        self.matrixUp = []
        self.matrixDown = []

    @abstractmethod
    def up(self):
        pass

    @abstractmethod
    def down(self):
        pass

    def readMatrixDown(self):
        matrix = []
        for i in range(len(self.matrixUp[0])):
            matrix.append(list(map(float, input().split())))
        if (len(self.matrixUp[0]) == 1):
            matrix = np.array(matrix).transpose()
        self.matrixDown = np.array(matrix)

    def buildMatrixDown(self):
        r = len(self.matrixUp)
        c = len(self.matrixUp[0])
        self.matrixDown = np.zeros(shape=(r, c))


class Var(Vertex):
    def __init__(self, r, c):
        super().__init__()
        self.r = r
        self.c = c

    def readMatrixDown(self):
        super().readMatrixDown()

    def buildMatrixDown(self):
        super().buildMatrixDown()

    def readMatrixUp(self):
        matrix = []
        for i in range(self.r):
            matrix.append(list(map(float, input().split())))
        self.matrixUp = np.array(matrix)
        if (self.r == 1):
            self.matrixUp = self.matrixUp.transpose()
        self.buildMatrixDown()

    def up(self):
        self.buildMatrixDown()

    def down(self):
        if len(self.matrixDown) == 0:
            self.buildMatrixDown()
        pass


class Tnh(Vertex):
    def __init__(self, x):
        super().__init__()
        self.x = x

    def readMatrixDown(self):
        super().readMatrixDown()

    def buildMatrixDown(self):
        super().buildMatrixDown()

    def up(self):
        self.matrixUp = np.array(deepcopy(self.x.matrixUp))
        self.matrixUp = np.tanh(self.matrixUp)
        self.buildMatrixDown()

    def down(self):
        for i in range(len(self.matrixDown)):
            for j in range(len(self.matrixDown[0])):
                self.x.matrixDown[i][j] += self.matrixDown[i][j] * (1 - self.matrixUp[i][j] ** 2)


class Mul(Vertex):
    def __init__(self, a, b):
        super().__init__()
        self.a = a
        self.b = b

    def readMatrixDown(self):
        super().readMatrixDown()

    def buildMatrixDown(self):
        super().buildMatrixDown()

    def up(self):
        matrix_a = np.array(deepcopy(self.a.matrixUp))
        matrix_b = np.array(deepcopy(self.b.matrixUp))
        self.matrixUp = deepcopy(np.dot(matrix_a, matrix_b))
        self.buildMatrixDown()

    def down(self):
        for i in range(len(self.a.matrixDown)):
            for j in range(len(self.b.matrixDown)):
                for p in range(len(self.b.matrixDown[0])):
                    self.a.matrixDown[i][j] += self.b.matrixUp[j][p] * self.matrixDown[i][p]

        for i in range(len(self.b.matrixDown)):
            for j in range(len(self.b.matrixDown[0])):
                for p in range(len(self.a.matrixDown)):
                    self.b.matrixDown[i][j] += self.a.matrixUp[p][i] * self.matrixDown[p][j]


class Sum(Vertex):
    def __init__(self, us):
        super().__init__()
        self.us = us

    def readMatrixDown(self):
        super().readMatrixDown()

    def buildMatrixDown(self):
        super().buildMatrixDown()

    def up(self):
        matrix_sum = np.array(deepcopy(self.us[0].matrixUp))
        for i in range(1, len(self.us)):
            matrix_sum = matrix_sum + np.array(deepcopy(self.us[i].matrixUp))
        self.matrixUp = np.array(deepcopy(matrix_sum))
        self.buildMatrixDown()

    def down(self):
        for i in range(len(self.us)):
            for j in range(len(self.matrixDown)):
                for p in range(len(self.matrixDown[0])):
                    self.us[i].matrixDown[j][p] += self.matrixDown[j][p]


class Had(Vertex):
    def __init__(self, us):
        super().__init__()
        self.us = us

    def readMatrixDown(self):
        super().readMatrixDown()

    def buildMatrixDown(self):
        super().buildMatrixDown()

    def up(self):
        matrix = np.array(deepcopy(self.us[0].matrixUp))
        for i in range(1, len(self.us)):
            matrix = matrix * np.array(deepcopy(self.us[i].matrixUp))

        self.matrixUp = deepcopy(matrix)
        self.buildMatrixDown()

    def down(self):
        for i in range(len(self.matrixUp)):
            for j in range(len(self.matrixUp[0])):
                for p in range(len(self.us)):
                    coef = 1.0
                    for d in range(len(self.us)):
                        if p != d:
                            coef *= self.us[d].matrixUp[i][j]
                    self.us[p].matrixDown[i][j] += coef * self.matrixDown[i][j]


class Sigmoid(Vertex):
    def __init__(self, x):
        super().__init__()
        self.x = x

    def readMatrixDown(self):
        super().readMatrixDown()

    def buildMatrixDown(self):
        super().buildMatrixDown()

    def up(self):
        self.matrixUp = np.array(deepcopy(self.x.matrixUp))
        for i in range(len(self.matrixUp)):
            for j in range(len(self.matrixUp[0])):
                self.matrixUp[i][j] = 1 / (1 + math.exp(-self.matrixUp[i][j]))

        self.buildMatrixDown()

    def down(self):
        for i in range(len(self.matrixDown)):
            for j in range(len(self.matrixDown[0])):
                self.x.matrixDown[i][j] += self.matrixUp[i][j] * self.matrixDown[i][j] * (1 - self.matrixUp[i][j])


n = int(input())

wf = Var(n, n)
wf.readMatrixUp()
uf = Var(n, n)
uf.readMatrixUp()
bf = Var(1, n)
bf.readMatrixUp()

wi = Var(n, n)
wi.readMatrixUp()
ui = Var(n, n)
ui.readMatrixUp()
bi = Var(1, n)
bi.readMatrixUp()

wo = Var(n, n)
wo.readMatrixUp()
uo = Var(n, n)
uo.readMatrixUp()
bo = Var(1, n)
bo.readMatrixUp()

wc = Var(n, n)
wc.readMatrixUp()
uc = Var(n, n)
uc.readMatrixUp()
bc = Var(1, n)
bc.readMatrixUp()

m = int(input())

fts = [Var(1, 1)]
its = [Var(1, 1)]
ots = [Var(1, 1)]
ccts = [Var(1, 1)]

down_transforms = []

hts = [Var(1, n)]
cts = [Var(1, n)]
hts[0].readMatrixUp()
cts[0].readMatrixUp()

xts = [Var(1, 1)]
for i in range(1, m + 1):
    xts.append(Var(1, n))
    xts[i].readMatrixUp()

for i in range(1, m + 1): #подсчитываем M векторов выходов сети и векторы памяти
    def calc(w, u, b, ts):
        oper_1 = Mul(w, xts[i])
        oper_1.up()
        oper_2 = Mul(u, hts[i - 1])
        oper_2.up()
        oper_3 = Sum([oper_1, oper_2, b])
        oper_3.up()
        ts.append(Sigmoid(oper_3))
        ts[i].up()
        down_transforms.append(oper_1)
        down_transforms.append(oper_2)
        down_transforms.append(oper_3)
        down_transforms.append(ts[i])


    calc(wf, uf, bf, fts)
    calc(wi, ui, bi, its)
    calc(wo, uo, bo, ots)

    oper_1 = Mul(wc, xts[i])
    oper_1.up()
    oper_2 = Mul(uc, hts[i - 1])
    oper_2.up()
    oper_3 = Sum([oper_1, oper_2, bc])
    oper_3.up()
    ccts.append(Tnh(oper_3))
    ccts[i].up()
    down_transforms.append(oper_1)
    down_transforms.append(oper_2)
    down_transforms.append(oper_3)
    down_transforms.append(ccts[i])

    oper_1 = Had([fts[i], cts[i - 1]])
    oper_1.up()
    oper_2 = Had([its[i], ccts[i]])
    oper_2.up()
    cts.append(Sum([oper_1, oper_2]))
    cts[i].up()
    hts.append(Had([ots[i], cts[i]]))
    hts[i].up()
    down_transforms.append(oper_1)
    down_transforms.append(oper_2)
    down_transforms.append(cts[i])
    down_transforms.append(hts[i])


def print_mtx(mtx):
    for i in range(len(mtx)):
        print(" ".join(map(str, mtx[i])))


for i in range(1, m + 1): #выводим M векторов сети
    print_mtx(ots[i].matrixUp)

print_mtx(hts[-1].matrixUp) #выводим два последних вектора памяти
print_mtx(cts[-1].matrixUp)

hts[-1].readMatrixDown()
cts[-1].readMatrixDown()

for i in range(m, 0, -1): #читаем векторы производных сети
    ots[i].readMatrixDown()

for i in range(len(down_transforms) - 1, -1, -1): #считаем производные
    down_transforms[i].down()

for i in range(m, 0, -1): #выводим M векторов производных
    print_mtx(xts[i].matrixDown)

answer = [hts[0].matrixDown, cts[0].matrixDown, #выводим производные по соответствующим матрицам и векторам параметров
          wf.matrixDown, uf.matrixDown, bf.matrixDown,
          wi.matrixDown, ui.matrixDown, bi.matrixDown,
          wo.matrixDown, uo.matrixDown, bo.matrixDown,
          wc.matrixDown, uc.matrixDown, bc.matrixDown]

for mtx in answer:
    print_mtx(mtx)
