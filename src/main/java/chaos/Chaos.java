package chaos;

import java.util.*;

public class Chaos {

    public static void main(String[] args) {
        String a = "AAA";
        String b = "aaa";
        System.out.println(a.compareToIgnoreCase(b));

        List<String> strings = new ArrayList<>();
    }

    public static int removeDuplicates(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(10);
        for (int i = 0; i < nums.length; i++) {
            //value不为0
            if(map.get(nums[i]) != null){
                Integer count = map.get(nums[i]);
                map.put(nums[i], count++);
            }else {
                map.put(nums[i],1);
            }
        }
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        return 0;
    }
}
