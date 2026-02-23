import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeForces2194d {
  final BufferedReader br;

  CodeForces2194d(InputStream in) {
    br = new BufferedReader(new InputStreamReader(in));
  }

  void solveCase(int n, int m, int[][] table) {
    var colSum = new int[m];
    int num1 = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int v = table[i][j];
        colSum[j] += v;
        num1 += v;
      }
    }
    int half = num1 / 2;
    /*
     *   1 2 3
     * 1 x 0 x
     * 2 x x 0
     * 3 x 0 0
     *
     * The idea is to take half.
     * - Compute number of 1 (x in the pic) in each column.
     * - Binary search half there.
     * - Go right until that column (effectively, taking nothing).
     * - Go down just enough to take up to the half (partly in that column).
     * - Take right (cut that column).
     * - Go all the way down.
     * - Go right taking every column in full.
     */
    var sb = new StringBuilder();
    int fullSum = 0;
    int k = m;
    while (k-- > 0) {
      fullSum += colSum[k];
      if (fullSum >= half) break;
      sb.append('R');
    }
    int c = n;
    while (fullSum > half) {
      c--;
      fullSum -= table[c][k];
      sb.append('D');
    }
    sb.append('R');
    while (c-- > 0) {
      sb.append('D');
    }
    while (k-- > 0) {
      sb.append('R');
    }
    long maxProduct = ((long) half) * (num1 - half);
    System.out.println(maxProduct);
    System.out.println(sb.reverse()); // Path
  }

  void solve() throws Exception {
    int numTests = parseInt(br.readLine());
    for (int t = 0; t < numTests; t++) {
      var st = new StringTokenizer(br.readLine());
      int n = parseInt(st.nextToken());
      int m = parseInt(st.nextToken());
      var table = new int[n][m];
      for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < m; j++) {
          int v = parseInt(st.nextToken());
          table[i][j] = v;
        }
      }
      solveCase(n, m, table);
    }
  }

  public static void main(String[] args) throws Exception {
    new CodeForces2194d(System.in).solve();
  }
}
