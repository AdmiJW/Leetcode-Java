package Easy;


public class $1572_Matrix_Diagonal_Sum {

    public int diagonalSum(int[][] mat) {
        final int len = mat.length;
        int sum = 0;

        for (int i = 0; i < len; ++i) sum += mat[i][i] + mat[i][len - i - 1];

        return (len % 2 == 1)?
            sum - mat[len / 2][len / 2]:
            sum;
    }

}
