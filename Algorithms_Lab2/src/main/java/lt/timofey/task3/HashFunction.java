package lt.timofey.task3;

public class HashFunction{
    public int multOfHashFunctionWithKnutConstant(int key){
        final double c = 0.6180339887;//Knut Constant
        final int m = 13;
        return m*((int)(c*key)%1);
    }
}
