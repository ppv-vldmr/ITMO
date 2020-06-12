prepare(X, K, MAX_N) :-
    X =< MAX_N,
    assert(divisor(X, K)),
    assert(composite(X)),
    X1 is X + K,
    prepare(X1, K, MAX_N).

init(N, MAX_N) :-
    prime(N),
    A is N * N,
    prepare(A, N, MAX_N).

init(N, MAX_N) :-
    N1 is N + 1,
    N1 * N1 =< MAX_N,
    init(N1, MAX_N).

init(MAX_N) :- init(2, MAX_N).

prime(1) :- !, fail.
prime(N) :- not(composite(N)).

divisor(A, A) :- prime(A).

prime_divisors(X, []) :- 1 = X, !.
prime_divisors(N, [H | T]) :- number(H), prime_divisors(N, [H | T], H).
prime_divisors(N, [H | T]) :- number(N), divisor(N, H), A is div(N, H), prime_divisors(A, T), !.
prime_divisors(X, [], _) :- 1 = X, !.
prime_divisors(N, [H | T], M) :- H >= M, prime(H), prime_divisors(N1, T, H), N is N1 * H, !.

unique_prime_divisors(N, Divisors):- prime_divisors(N, R), delete_duplicates(0, R,[], Divisors).

delete_duplicates(Pred, [], K, RES):- RES = K.
delete_duplicates(Pred, [H | T], K, RES):- (H > Pred, H1 = [H], append(K, H1, K1), delete_duplicates(H, T,K1, RES)) ; (H = Pred, delete_duplicates(H, T, K, RES)).