/*
enum ValueType { INT, DOUBLE, FLOAT, BOOLEAN, BYTE, SHORT, LONG, CHAR};

static abstract class ParsedValue<T>
{
    private final T data;
    private final ValueType type;

    public ParsedValue(T val, ValueType t)
    {
        data = val;
        type = t;
    }

    public ValueType getType()
    {
        return type;
    }

    public T getValue()
    {
        return data;
    }
}

static class IntParsedValue extends ParsedValue<Integer>
{
    public IntParsedValue(Integer val)
    {
        super(val, ValueType.INT);
    }
}

static class DoubleParsedValue extends ParsedValue<Double>
{
    public DoubleParsedValue(Double val)
    {
        super(val, ValueType.DOUBLE);
    }
}

static class FloatParsedValue extends ParsedValue<Float>
{
    public FloatParsedValue(Float val)
    {
        super(val, ValueType.FLOAT);
    }
}

static class BooleanParsedValue extends ParsedValue<Boolean>
{
    public BooleanParsedValue(Boolean val)
    {
        super(val, ValueType.BOOLEAN);
    }
}

static class ByteParsedValue extends ParsedValue<Byte>
{
    public ByteParsedValue(Byte val)
    {
        super(val, ValueType.BYTE);
    }
}

static class ShortParsedValue extends ParsedValue<Short>
{
    public ShortParsedValue(Short val)
    {
        super(val, ValueType.SHORT);
    }
}

static class LongParsedValue extends ParsedValue<Long>
{
    public LongParsedValue(Long val)
    {
        super(val, ValueType.LONG);
    }
}

static class CharParsedValue extends ParsedValue<Char>
{
    public CharParsedValue(Char val)
    {
        super(val, ValueType.CHAR);
    }
}
*/