package 回溯;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    /*
        组合
     */
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        combineHelper(res, path, n, k, 1);
        return res;
    }

    private void combineHelper(List<List<Integer>> res, List<Integer> path, int n, int k, int startIndex) {
        //终止条件
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineHelper(res, path, n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /*
        组合总和III
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, path, n, k, 1, 0);
        return res;
    }

    private void backTracking(List<List<Integer>> res, List<Integer> path, int target, int k, int startIndex, int sum) {
        if (sum > target) {
            return;
        }
        if (path.size() == k && sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            backTracking(res, path, target, k, i + 1, sum + i);
            path.remove(path.size() - 1);
        }
    }

    /*
        电话号码的字母组合
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backTracking(res, digits, 0);
        return res;
    }

    private String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    StringBuilder sb = new StringBuilder();

    private void backTracking(List<String> res, String digits, int index) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String str = numString[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            backTracking(res, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
        组合总和
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtracking(res, path, candidates, target, 0, 0);
        return res;
    }

    public void backtracking(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int startIndex) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtracking(res, path, candidates, target, sum + candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    /*
        组合总和II
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtracking2(res, path, candidates, target, 0, 0);
        return res;
    }

    public void backtracking2(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int startIndex) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (i > startIndex && candidates[i - 1] == candidates[i]) {
                continue;
            }
            path.add(candidates[i]);
            backtracking(res, path, candidates, target, sum + candidates[i], i + 1);
            path.remove(path.size() - 1);
        }
    }

    /*
        分割回文串
     */
    public List<List<String>> partition(String s) {
        List<String> path = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        backTracking(res, path, s, 0);
        return res;
    }

    private void backTracking(List<List<String>> res, List<String> path, String s, int startIndex) {
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            backTracking(res, path, s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    //判断是否是回文串
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /*
        复原IP地址
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backTrack(res, s, 0, 0);
        return res;
    }

    private void backTrack(List<String> res, String s, int startIndex, int pointNum) {
        if (pointNum == 3 && isValid(s, startIndex, s.length() - 1)) {
            res.add(s);
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                backTrack(res, s, i + 2, pointNum + 1);
                s = s.substring(0, i + 1) + s.substring(i + 2);
            } else {
                break;
            }
        }
    }

    //判断左闭右闭截取的字符串是否合法
    private Boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    /*
        子集
        子集是收集树形结构中树的所有节点的结果。而组合问题、分割问题是收集树形结构中叶子节点的结果
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        subsetsHelper(res, path, nums, 0);
        return res;
    }

    private void subsetsHelper(List<List<Integer>> res, List<Integer> path, int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsHelper(res, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /*
        子集II
        理解“树层去重”和“树枝去重”非常重要
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        subsetsWithDupHelper(res, path, nums, 0, used);
        return res;
    }

    private void subsetsWithDupHelper(List<List<Integer>> res, List<Integer> path, int[] nums, int startIndex, boolean[] used) {
        res.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) { //树层去重
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            subsetsWithDupHelper(res, path, nums, i + 1, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    /*
        递增子序列
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(res, path, nums, 0);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> path, int[] nums, int startIndex) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1)) {
                continue;
            }
            if (map.getOrDefault(nums[i], 0) >= 1) {
                continue;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            path.add(nums[i]);
            backtracking(res, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /*
        全排列
        遍历树的所有叶子结点
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteHelper(res, path, nums, used);
        return res;
    }

    private void permuteHelper(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteHelper(res, path, nums, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int[] res = new int[201];
        for (int i = 0; i < nums1.length; i++) {
            res[nums1[i][0]] = nums1[i][1];
        }
        for (int i = 0; i < nums2.length; i++) {
            res[nums2[i][0]] += nums2[i][1];
        }
        int count = 0;
        for (int i = 0; i < res.length; i++) {
            if (res[i] != 0) {
                count++;
            }
        }
        int[][] arr = new int[count][2];
        int m = 0;
        for (int i = 0; i < res.length; i++) {
            if (res[i] != 0) {
                arr[m][0] = i;
                arr[m][1] = res[i];
                m++;
            }
        }
        return arr;
    }

    /*
        全排列II
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUniqueHelper(res, path, nums, used);
        return res;
    }

    private void permuteUniqueHelper(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //树层去重
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            //树枝去重
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteUniqueHelper(res, path, nums, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

}
