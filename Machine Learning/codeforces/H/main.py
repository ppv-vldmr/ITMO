import math

N, M, K = map(int, list(input().split())) #число вершин в графе вычислений, число входных и выходных параметров


class matrix:
    r = 0
    c = 0

    def print(self):
        for i in range(self.r):
            for j in range(self.c):
                print(self.data[i][j], end=" ")
            print()

    def f(self):
        self.calculated_df = matrix(self.r, self.c)
        self.calculated_f = matrix(self.r, self.c, self.data)
        return self.calculated_f

    def df(self):
        pass

    def __init__(self, r, c, data=None):
        self.data = [[]]
        self.next = []
        self.prev = []
        self.r = r
        self.c = c
        if data is None:
            self.data = [[0 for _ in range(c)] for _ in range(r)]
        else:
            self.data = data
        self.calculated_f = None
        self.calculated_df = None

    def __mul__(self, other):
        assert (len(self.data[0]) == len(other.data))
        # if len(self.data[0]) != len(other.data):
        #     while True:
        #         pass

        result = [[0 for _ in range(len(other.data[0]))] for _ in range(self.r)]
        for i in range(len(self.data)):
            for j in range(len(other.data[0])):
                for k in range(len(other.data)):
                    result[i][j] += self.data[i][k] * other.data[k][j]
        return matrix(len(result), len(result[0]), result)

    def __add__(self, other):
        assert (len(self.data) == len(other.data))
        assert (len(self.data[0]) == len(other.data[0]))

        result = [[0 for _ in range(self.c)] for _ in range(self.r)]
        for i in range(self.r):
            for j in range(self.c):
                result[i][j] = self.data[i][j] + other.data[i][j]
        return matrix(len(result), len(result[0]), result)

    def had_f(self, other):
        assert (len(self.data) == len(other.data))
        assert (len(self.data[0]) == len(other.data[0]))
        result = [[1 for _ in range(self.c)] for _ in range(self.r)]
        for i in range(self.r):
            for j in range(self.c):
                result[i][j] = self.data[i][j] * other.data[i][j]
        return matrix(len(result), len(result[0]), result)


class tnh:

    def df(self):
        for i in range(len(self.calculated_df.data)):
            for j in range(len(self.calculated_df.data[0])):
                v = self.calculated_f.data[i][j]
                self.prev[0].calculated_df.data[i][j] += (1 - v * v) * self.calculated_df.data[i][j]

    def tan_h(self, x):
        ex = math.e ** x
        emx = math.e ** (-x)
        return (ex - emx) / (ex + emx)

    def f(self):
        if self.calculated_f is None:
            m = self.prev[0].f()
            res = [[0 for _ in range(m.c)] for _ in range(m.r)]
            r = m.r
            c = m.c

            for i in range(r):
                for j in range(c):
                    res[i][j] = self.tan_h(m.data[i][j])
            self.calculated_f = matrix(r, c, res)
            self.calculated_df = matrix(self.calculated_f.r, self.calculated_f.c)
        return self.calculated_f

    def __init__(self, prev):

        self.next = []
        self.prev = []
        self.prev.append(prev)
        prev.next.append(self)
        self.calculated_f = None
        self.calculated_df = None


class rlu:
    def df(self):
        for i in range(len(self.calculated_df.data)):
            for j in range(len(self.calculated_df.data[0])):
                m = 1
                if self.prev[0].calculated_f.data[i][j] < 0:
                    m = self.alpha
                self.prev[0].calculated_df.data[i][j] += m * self.calculated_df.data[i][j]

    def relu(self, x):
        if x < 0:
            return self.alpha * x
        else:
            return x

    def f(self):
        if self.calculated_f is None:
            m = self.prev[0].f()
            res = [[0 for _ in range(m.c)] for _ in range(m.r)]
            r = m.r
            c = m.c
            for i in range(r):
                for j in range(c):
                    res[i][j] = self.relu(m.data[i][j])
            self.calculated_f = matrix(r, c, res)
            self.calculated_df = matrix(self.calculated_f.r, self.calculated_f.c)
        return self.calculated_f

    def __init__(self, alpha, prev):
        self.next = []
        self.prev = []
        self.alpha = 1 / alpha
        self.prev.append(prev)
        prev.next.append(self)
        self.calculated_f = None
        self.calculated_df = None


class mul:

    def df(self):
        first = self.prev[0]
        second = self.prev[1]

        n = first.calculated_f.r
        m = first.calculated_f.c
        k = self.calculated_f.c

        for x in range(n):
            for y in range(m):
                for z in range(k):
                    first.calculated_df.data[x][y] += self.calculated_df.data[x][z] * second.calculated_f.data[y][z]

        for y in range(m):
            for z in range(k):
                for x in range(n):
                    second.calculated_df.data[y][z] += self.calculated_df.data[x][z] * first.calculated_f.data[x][y]

    def f(self):
        if self.calculated_f is None:
            m1 = self.prev[0].f()
            m2 = self.prev[1].f()
            self.calculated_f = m1 * m2
            self.calculated_df = matrix(self.calculated_f.r, self.calculated_f.c)
        return self.calculated_f

    def __init__(self, prevs):
        self.next = []
        self.prev = []
        for i in prevs:
            self.prev.append(i)
            i.next.append(self)
        self.calculated_f = None
        self.calculated_df = None


class sum:

    def df(self):
        for i in range(len(self.calculated_df.data)):
            for j in range(len(self.calculated_df.data[0])):
                for k in range(len(self.prev)):
                    self.prev[k].calculated_df.data[i][j] += self.calculated_df.data[i][j]

    def f(self):
        if self.calculated_f is None:
            ans = self.prev[0].f()
            for i in range(1, len(self.prev)):
                ans = ans + self.prev[i].f()
            self.calculated_f = ans
            self.calculated_df = matrix(self.calculated_f.r, self.calculated_f.c)
        return self.calculated_f

    def __init__(self, prevs):
        self.next = []
        self.prev = []
        for i in prevs:
            self.prev.append(i)
            i.next.append(self)
        self.calculated_f = None
        self.calculated_df = None


class hadamar:

    def df(self):
        for i in range(len(self.calculated_df.data)):
            for j in range(len(self.calculated_df.data[0])):
                for k in range(len(self.prev)):
                    m = 1
                    for t in range(len(self.prev)):
                        if k != t:
                            m *= self.prev[t].calculated_f.data[i][j]
                    self.prev[k].calculated_df.data[i][j] += m * self.calculated_df.data[i][j]

    def f(self):
        if self.calculated_f is None:
            ans = self.prev[0].f()
            for i in range(1, len(self.prev)):
                ans = ans.had_f(self.prev[i].f())
            self.calculated_f = ans
            self.calculated_df = matrix(self.calculated_f.r, self.calculated_f.c)
        return self.calculated_f

    def __init__(self, prevs):
        self.next = []
        self.prev = []
        for i in prevs:
            self.prev.append(i)
            i.next.append(self)
        self.calculated_f = None
        self.calculated_df = None


blocks = []

in_blocks = []
out_blocks = []

for i in range(N):

    args = input().split()

    type = args[0]
    args = args[1:]

    if type == 'var':
        nums = list(map(int, args))
        m = matrix(nums[0], nums[1])
        blocks.append(m)
    elif type == 'tnh':
        nums = list(map(int, args))
        t = tnh(blocks[nums[0] - 1])
        blocks.append(t)
    elif type == 'rlu':
        nums = list(map(int, args))
        r = rlu(nums[0], blocks[nums[1] - 1])
        blocks.append(r)
    elif type == 'mul':
        nums = list(map(int, args))
        m = mul([blocks[nums[0] - 1], blocks[nums[1] - 1]])
        blocks.append(m)
    elif type == 'sum':
        nums = list(map(int, args))
        l = nums[0]
        arr = []
        for k in range(1, len(nums)):
            arr.append(blocks[nums[k] - 1])
        s = sum(arr)
        blocks.append(s)
    elif type == 'had':
        nums = list(map(int, args))
        l = nums[0]
        arr = []
        for k in range(1, len(nums)):
            arr.append(blocks[nums[k] - 1])
        h = hadamar(arr)
        blocks.append(h)
    else:
        raise Exception("Unsupported block")

for i in range(M): #читаем матрицы на входе
    b = blocks[i]
    for l in range(b.r):
        s = input().split()
        nums = list(map(int, s))
        for k in range(len(nums)):
            b.data[l][k] = nums[k]

calc_res = []
for j in range(K):
    i = N - K + j
    calc_res.append(blocks[i].f())

for i in calc_res:
    i.print()

for i in range(K):
    deriv = [[0 for _ in range(calc_res[i].c)] for _ in range(calc_res[i].r)]
    for j in range(calc_res[i].r):
        s = list(map(int, input().split()))
        deriv[j] = s
    deriv = matrix(len(deriv), len(deriv[0]), deriv)
    j = N - K + i

    blocks[j].calculated_df = deriv

# if N == 6:
# print(calc_res[0].data)
# DERIVATIVE
for i in range(N - 1, -1, -1):
    blocks[i].df()

for i in range(M):
    dff = blocks[i].calculated_df
    if dff is None:
        dff = matrix(blocks[i].r, blocks[i].c)
        dff.print()
    else:
        blocks[i].calculated_df.print()