package chaos;

import java.util.ArrayList;
import java.util.List;

public class Chaos2 {


    public static void main(String[] args) {
        InterfaceFa interfaceFa = new Chaos();
        int a = 4^7;
        System.out.println(a + "");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.stream().forEach(System.out::println);
        //countMatches(new ArrayList<List<String>>(),"","");
    }

    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        for (List<String> item : items) {
            switch (ruleKey){
                case "type":
                    if(ruleValue.equals(item.get(0))){count++;}
                    break;
                case "color":
                    if(ruleValue.equals(item.get(1))){count++;}
                    break;
                case "name":
                    if(ruleValue.equals(item.get(2))){count++;}
                    break;
            }
        }
        return count;
    }

    public static int maximumWealth(int[][] accounts) {
        int returnSum = getSum(accounts[0]);
        for (int i = 1; i < accounts.length; i++) {
            returnSum = getSum(accounts[i]) > returnSum ? getSum(accounts[i]):returnSum;
        }
        return returnSum;
    }

    private static int getSum(int[] account) {
        int sum = 0;
        for (int i = 0; i < account.length; i++) {
            sum += account[i];
        }
        return sum;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        char[] stonesArray = stones.toCharArray();
        char[] jewelsArray = jewels.toCharArray();
        for (int i1 = 0; i1 < jewelsArray.length; i1++) {
            for (int i = 0; i < stonesArray.length; i++) {
                if(jewelsArray[i1] - stonesArray[i] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                if(nums[i1 - 1] < nums[i1]){
                    count ++;
                }
            }
        }
        return count;
    }

}
