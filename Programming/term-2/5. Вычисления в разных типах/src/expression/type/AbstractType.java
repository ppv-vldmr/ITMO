package expression.type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class AbstractType<T extends Number> implements Type<T> {
    protected T value;

    public AbstractType(T value) {
        this.value = value;
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public Type<T> square() {
        return this.multiply(this);
    }

    @Override
    public Type<T> add(Type<T> v) {
        return valueOf(addImpl(v.value()));
    }

    @Override
    public Type<T> subtract(Type<T> v) {
        return valueOf(subtractImpl(v.value()));
    }

    @Override
    public Type<T> multiply(Type<T> v) {
        return valueOf(multiplyImpl(v.value()));
    }

    @Override
    public Type<T> divide(Type<T> v) {
        return valueOf(divideImpl(v.value()));
    }

    @Override
    public Type<T> mod(Type<T> v) {
        return valueOf(modImpl(v.value()));
    }

    @Override
    public Type<T> negate() {
        return valueOf(negateImpl());
    }

    @Override
    public Type<T> abs() {
        return valueOf(absImpl());
    }

    @Override
    public Type<T> count() {
        return valueOf(countImpl());
    }

    @Override
    public Type<T> minimum(Type<T> v) {
        return valueOf(minimumImpl(v.value()));
    }

    @Override
    public Type<T> maximum(Type<T> v) {
        return valueOf(maximumImpl(v.value()));
    }

    protected abstract T maximumImpl(T value);

    protected abstract T minimumImpl(T value);

    protected abstract T countImpl();

    protected abstract T negateImpl();

    protected abstract T absImpl();

    protected abstract T addImpl(T value);

    protected abstract T subtractImpl(T value);

    protected abstract T multiplyImpl(T value);

    protected abstract T divideImpl(T value);

    protected abstract T modImpl(T value);

    protected abstract Type<T> valueOf(T value);
}
