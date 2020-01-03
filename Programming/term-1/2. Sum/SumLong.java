public class SumLong {
    public static void main(String args[]) {
        long sum = 0;
        for ( int i = 0; i < args.length; i++ ) {
            int sl = -1;
            String st = " " + args[i] + " ";
	    //System.out.println("+" + st + "+");
            for ( int j = 0; j < st.length() - 1; j++ ) {
                if ( Character.isWhitespace( st.charAt( j ) ) && !Character.isWhitespace( st.charAt( j+1 ) ) ) {
		    sl = j + 2;
		}
		if ( !Character.isWhitespace( st.charAt( j ) ) && Character.isWhitespace( st.charAt( j+1 ) ) ) {
		    sum += Long.parseLong( st.substring( sl, j+1 ) );
		    //System.out.println(sl + " " + st.substring(sl,j+1));
		}
            }
	    //System.out.println(sum);
        }
        System.out.println(sum);
    }
}