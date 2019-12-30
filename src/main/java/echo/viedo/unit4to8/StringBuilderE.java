package echo.viedo.unit4to8;

//  将int类型的数组变为字符串

class StringBuilderE{

    public static StringBuilder arrayToString2(int[] arr) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int i =0; i<arr.length; i++)
            if(i != arr.length-1){
                // str.append(arr[i]).append(",");
                str.append(arr[i] + ","); // 上一行的另一种写法
            }
            else{
                str.append(arr[i]).append("]");
            }
        return str;
    }
    public static String arrayToString1(int[] arr) {
        String str = "[";

        for(int i =0; i<arr.length; i++)
            if(i != arr.length-1){
                str += arr[i] +","; // 每一次都有一个字符串常量值
            }
            else{
                str += arr[i] +"]";
            }
        return str;
    }
}