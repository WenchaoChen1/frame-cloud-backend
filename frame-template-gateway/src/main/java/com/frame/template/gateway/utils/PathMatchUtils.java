package com.frame.template.gateway.utils;

/**
 * 使用动态规划算法进行路径匹配
 */
public class PathMatchUtils {

  /**
   * @param s 传入的路径
   * @param p 规则路径
   * @return
   * @desription: 在给定的模式 pp 中，只会有三种类型的字符出现：
   * <p>
   * 小写字母 a-za−z，可以匹配对应的一个小写字母；
   * <p>
   * 问号 ?，可以匹配任意一个小写字母；
   * <p>
   * 星号 *，可以匹配任意字符串，可以为空，也就是匹配零或任意多个小写字母。
   * <p>
   * 其中「小写字母」和「问号」的匹配是确定的，而「星号」的匹配是不确定的，因此我们需要枚举所有的匹配情况。为了减少重复枚举，我们可以使用动态规划来解决本题。
   */
  public static boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int i = 1; i <= n; ++i) {
      if (p.charAt(i - 1) == '*') {
        dp[0][i] = true;
      } else {
        break;
      }
    }
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (p.charAt(j - 1) == '*') {
          dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
        } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }
    return dp[m][n];
  }

}
